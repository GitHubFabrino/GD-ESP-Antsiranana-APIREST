package com.appli.springjwt.controllers;

import com.appli.springjwt.models.Anneeuniv;
import com.appli.springjwt.repository.AnneeunivRepository;
import com.appli.springjwt.service.AnneeService;
import com.appli.springjwt.service.InscriptionAdministrativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/annee")

public class AnneController {
    @Autowired
    AnneeService anneeService;
    @Autowired
    AnneeunivRepository anneeunivRepository;

    @PostMapping
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('DIRECTION') or hasRole('ADMIN') or hasAuthority('PRESIDENT_JURY')")
    public void postAnnee(@RequestBody Anneeuniv anneeuniv){

        System.out.println("AnneController : postAnnee");
        String nomAU = anneeuniv.getNomAU();

        if (!InscriptionAdministrativeService.isNomAUAlreadyExists(nomAU)) {
            // Le nomAU n'existe pas encore, nous pouvons l'ajouter
            anneeunivRepository.save(anneeuniv);
        }else{
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Le nomAU existe déjà");
        }

    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('ENSEIGNANT') or hasAuthority('DIRECTION') or hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('RESPONSABLE_MENTION') or hasAuthority('ETUDIANT') or hasRole('ADMIN') or hasAuthority('PRESIDENT_JURY')")
    public List<Anneeuniv> listAnnee() {
        System.out.println("AnneController : listAnnee");
        List<Anneeuniv> annee = anneeunivRepository.findAll();
        Collections.reverse(annee);
        return annee;
    }
    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('DIRECTION') or hasAuthority('PRESIDENT_JURY') or hasAuthority('ETUDIANT') or hasRole('ADMIN')")
    public Anneeuniv listAnnee(@PathVariable("id") Integer id) {
        System.out.println("AnneController : listAnnee");
        return anneeunivRepository.findById(id).orElseThrow();
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('PRESIDENT_JURY') or hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void modifierAnnee(@PathVariable("id") Integer id, @RequestBody Anneeuniv anneeuniv){
        System.out.println("AnneController : modifierAnnee");
        Anneeuniv annee = anneeunivRepository.findById(id).orElseThrow();
        annee.setNomAU(anneeuniv.getNomAU());
        annee.setDebutAU(anneeuniv.getDebutAU());
        annee.setFinAU(anneeuniv.getFinAU());

        anneeunivRepository.save(annee);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('PRESIDENT_JURY') or hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void supprimerAnnee(@PathVariable("id") Integer id){
        System.out.println("AnneController : supprimerAnnee");
        Anneeuniv annee = anneeunivRepository.findById(id).orElseThrow();
        anneeunivRepository.delete(annee);
    }

}
