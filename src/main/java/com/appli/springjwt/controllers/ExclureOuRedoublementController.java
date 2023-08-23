package com.appli.springjwt.controllers;


import com.appli.springjwt.dto.ExclureOuRedoubleeDto;
import com.appli.springjwt.models.Definitionparcour;
import com.appli.springjwt.models.Resultatfinau;
import com.appli.springjwt.models.Semestre;
import com.appli.springjwt.repository.DefinitionparcourRepository;
import com.appli.springjwt.repository.Resultat;
import com.appli.springjwt.repository.ResultatfinauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/redoublement")
public class ExclureOuRedoublementController {

    @PersistenceContext
    private EntityManager entityManager;

    private  ExclureOuRedoubleeDto exclureOuRedoubleeDto;

    @Autowired
    private  DefinitionparcourRepository definitionparcourRepository;

    @Autowired
    private ResultatfinauRepository resultatfinauRepository;

    @Autowired
    private Resultat resultat;

    @PutMapping("/{idDP1}/{idDP2}/{id_etudiant}")
    @PreAuthorize("hasAuthority('RESPONSABLE_PARCOURS')or hasRole('ADMIN')")
    public ExclureOuRedoubleeDto coderedoublement(@PathVariable("idDP1") Integer idDP1,@PathVariable("idDP2") Integer idDP2,@PathVariable("id_etudiant") Integer id_etudiant, @RequestBody ExclureOuRedoubleeDto exclureOuRedoubleeDto){
        System.out.println("ExclureOuRedoublementController : coderedoublement");
        Optional<Definitionparcour> parcour1 = definitionparcourRepository.findById(idDP1);
        Optional<Definitionparcour> parcour2 = definitionparcourRepository.findById(idDP2);
        if ( parcour1 != null && parcour2 != null){

            Semestre p1 = parcour1.get().getIdSemestre();
            Semestre p2 = parcour2.get().getIdSemestre();

            Integer pp1 = p1.getId();
            Integer pp2 = p2.getId();
          Long pdefinitive ;
           if ( pp1 > pp2){
                pdefinitive = Long.valueOf(idDP1);
            }else {
                pdefinitive = Long.valueOf(idDP2);
            }
            System.out.println("pdefinitive " + pdefinitive);

            String sql = "SELECT * FROM resultatfinau WHERE id_DP = :pdefinitive AND id_etudiant = :id_etudiant";
            Query query = entityManager.createNativeQuery(sql, Resultatfinau.class);
            query.setParameter("pdefinitive", pdefinitive);
            query.setParameter("id_etudiant", id_etudiant);

            Resultatfinau etudiantcible = null;
            try {
                etudiantcible = (Resultatfinau) query.getSingleResult();
                System.out.println("Données de l'étudiant cible : " + etudiantcible);
            } catch (NoResultException e) {
                System.out.println("Aucun résultat trouvé pour les paramètres fournis.");
            } catch (NonUniqueResultException e) {
                System.out.println("Plusieurs résultats trouvés pour les paramètres fournis.");
            }

            System.out.println(etudiantcible.getCodeRedoublement());
            etudiantcible.setCodeRedoublement(exclureOuRedoubleeDto.getCode_redoublement());
            resultatfinauRepository.save(etudiantcible);
        }

        return null;
    }
}
