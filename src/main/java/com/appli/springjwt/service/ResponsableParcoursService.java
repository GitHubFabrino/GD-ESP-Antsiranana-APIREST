package com.appli.springjwt.service;

import com.appli.springjwt.models.*;
import com.appli.springjwt.repository.*;
import com.appli.springjwt.dto.DefinitionParcoursDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class ResponsableParcoursService {
    @Autowired
    EnseignantRepository enseignantRepository;
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    DefinitionparcourRepository definitionparcourRepository;
    @Autowired
    AuthentificationRepository authentificationRepository;
    @Autowired
    FonctionRepository fonctionRepository;
    @Autowired
    ParcourRepository parcourRepository;
    @Autowired
    AnneeunivRepository anneeunivRepository;

    public void save(ArrayList<DefinitionParcoursDto> dto) {

        for(DefinitionParcoursDto definitionParcoursDto: dto) {
            //Definitionparcour definitionparcour = new Definitionparcour();
            Definitionparcour definitionparcour = definitionparcourRepository.findById(definitionParcoursDto.getId()).orElseThrow();
            Enseignant enseignant = enseignantRepository.findById(definitionParcoursDto.getIdEnseignant()).orElseThrow();

            definitionparcour.setIdEnseignant(enseignant);
            /*
            try {

            }catch (Exception e){
                definitionparcour.setIdEnseignant(null);
            }

             */

            //definitionparcour.setIdParcours(parcourRepository.findById(definitionParcoursDto.getIdParcours()).orElseThrow());

            try {
                Status status = new Status();
                status.setIdAuthentification(authentificationRepository.findById(enseignant.getIdPersonne().getAuthentification().getId()).orElseThrow());
                status.setIdFonction(fonctionRepository.findByName(ERole.RESPONSABLE_PARCOURS).orElseThrow());

                statusRepository.save(status);
            } catch (Exception e){}
            definitionparcourRepository.save(definitionparcour);


        }
    }

    public ArrayList<DefinitionParcoursDto> get(Integer idAU) {
        ArrayList<Definitionparcour> ObjDefinition = definitionparcourRepository.findAllByIdAu(anneeunivRepository.findById(idAU).orElseThrow());
        ArrayList<DefinitionParcoursDto> definitionParcoursDtos = new ArrayList<>();

        for(Definitionparcour definitionparcour :ObjDefinition){
            Integer i = 0;
            try {
                definitionParcoursDtos.add(i, new DefinitionParcoursDto(
                        definitionparcour.getId(),
                        definitionparcour.getIdEnseignant().getId(),
                        definitionparcour.getIdAu().getId(),
                        definitionparcour.getIdSemestre().getId(),
                        definitionparcour.getIdParcours().getId(),
                        definitionparcour.getIdParcours().getParcours(),
                        definitionparcour.getIdParcours().getAcronymeParcours(),
                        definitionparcour.getIdEnseignant().getIdPersonne().getNom(),
                        definitionparcour.getIdEnseignant().getIdPersonne().getPrenoms()
                ));
            } catch(Exception e){  }
            i+=1;
        }
        // System.out.println(definitionMentionDtos.get(0).getAcronymeMention());
        Collections.reverse(definitionParcoursDtos);
        return definitionParcoursDtos;

    }

        public void delete(Integer id) {
            Definitionparcour definitionparcour = definitionparcourRepository.findById(id).orElseThrow();
            Personne personne= definitionparcour.getIdEnseignant().getIdPersonne();
            Fonction fonction = fonctionRepository.findByName(ERole.RESPONSABLE_PARCOURS).orElseThrow();
            definitionparcour.setIdEnseignant(null);

            Status status = statusRepository.findByIdAuthentificationAndIdFonction(personne.getAuthentification(),fonction).orElse(null);
            if(status==null){

            }else {
                statusRepository.delete(status);
            }

            definitionparcourRepository.deleteById(definitionparcour.getId());
    }
}
