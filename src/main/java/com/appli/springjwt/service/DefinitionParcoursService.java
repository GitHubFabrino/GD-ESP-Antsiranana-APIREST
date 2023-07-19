package com.appli.springjwt.service;

import com.appli.springjwt.dto.DefinitionParcoursDto;
import com.appli.springjwt.models.*;
import com.appli.springjwt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
@Service
public class DefinitionParcoursService {
    @Autowired
    AnneeunivRepository anneeunivRepository;
    @Autowired
    SemestreRepository semestreRepository;
    @Autowired
    MentionRepository mentionRepository;
    @Autowired
    NiveauRepository niveauRepository;
    @Autowired
    ParcourRepository parcourRepository;
    @Autowired
    DefinitionmentionRepository definitionmentionRepository;
    @Autowired
    DefinitionparcourRepository definitionparcourRepository;
    @Autowired
    EnseignantRepository enseignantRepository;

    public void save(ArrayList<DefinitionParcoursDto> dto) {

        for(DefinitionParcoursDto offreFormationDto: dto){

            Enseignant enseignant = null;
            Definitionparcour offreformation = new Definitionparcour();
            Definitionmention definitionmention = definitionmentionRepository.findById(offreFormationDto.getIdDM()).orElseThrow();
            Semestre semestre = semestreRepository.findById(offreFormationDto.getIdSemestre()).orElseThrow();
            //Mention mention = mentionRepository.findById(offreFormationDto.getIdDM()).orElseThrow();
            Niveau niveau = niveauRepository.findById(offreFormationDto.getIdNiveau()).orElseThrow();
            Anneeuniv anneeuniv = anneeunivRepository.findById(offreFormationDto.getIdAU()).orElseThrow();
            Parcour parcour = new Parcour();
            if(offreFormationDto.getIdEnseignant()==null){

            }else {
                enseignant = enseignantRepository.findById(offreFormationDto.getIdEnseignant()).orElseThrow();
            }
            if(parcourRepository.existsByParcours(offreFormationDto.getParcours())){
                parcour = parcourRepository.findByParcours(offreFormationDto.getParcours()).orElseThrow();
            } else {
                parcour.setAcronymeParcours(offreFormationDto.getAcronymeParcours());
                parcour.setParcours(offreFormationDto.getParcours());
            }
            offreformation.setIdAu(anneeuniv);
            offreformation.setIdDm(definitionmention);
            offreformation.setIdSemestre(semestre);
           // offreformation.getIdDm().setIdMention(mention);
            offreformation.setIdNiveau(niveau);
            offreformation.setIdParcours(parcour);
            //try {
            offreformation.setIdEnseignant(enseignant);
            /*
            }catch (Exception e) {
                enseignant = null;
                offreformation.setIdEnseignant(enseignant);
            }

             */
            parcourRepository.save(parcour);

            if(definitionparcourRepository.existsByIdAuAndIdNiveauAndIdDmAndIdSemestreAndIdParcoursAndIdEnseignant(
                    anneeuniv, niveau, definitionmention, semestre, parcour, enseignant
            )){ }else {
                definitionparcourRepository.save(offreformation);
            }
        }
    }

    public ArrayList<DefinitionParcoursDto> get(Integer id) {

        ArrayList<Definitionparcour> Objoffre = definitionparcourRepository.findAllByIdAu(anneeunivRepository.findById(id).orElseThrow());
        ArrayList<DefinitionParcoursDto> offreFormationDtos = new ArrayList<>();

        for(Definitionparcour offreformation :Objoffre){
            Integer i = 0;

            if (definitionparcourRepository.existsByIdAu(anneeunivRepository.findById(id).orElseThrow())){
                try {
                    offreFormationDtos.add(i, new DefinitionParcoursDto(
                            offreformation.getId(),
                            offreformation.getIdEnseignant().getId(),
                            offreformation.getIdAu().getId(),
                            offreformation.getIdNiveau().getId(),
                            offreformation.getIdDm().getId(),
                            offreformation.getIdSemestre().getId(),
                            offreformation.getIdParcours().getId(),
                            offreformation.getIdParcours().getAcronymeParcours(),
                            offreformation.getIdParcours().getParcours()
                    ));
                }catch (Exception e){
                    offreFormationDtos.add(i, new DefinitionParcoursDto(
                            offreformation.getId(),
                            null,
                            offreformation.getIdAu().getId(),
                            offreformation.getIdNiveau().getId(),
                            offreformation.getIdDm().getId(),
                            offreformation.getIdSemestre().getId(),
                            offreformation.getIdParcours().getId(),
                            offreformation.getIdParcours().getAcronymeParcours(),
                            offreformation.getIdParcours().getParcours()
                    ));
            }

        }else{ }
            i+=1;
        }
        Collections.reverse(offreFormationDtos);
        return offreFormationDtos;
    }

    public void update(ArrayList<DefinitionParcoursDto> dto) {

        for(DefinitionParcoursDto offreFormationDto: dto){
            Definitionparcour offreformation =  definitionparcourRepository.findById(offreFormationDto.getId()).orElseThrow();

            offreformation.getIdParcours().setParcours(offreFormationDto.getParcours());
            offreformation.getIdParcours().setAcronymeParcours(offreFormationDto.getAcronymeParcours());

            definitionparcourRepository.save(offreformation);
        }
    }
    public void delete(Integer id) {
        definitionparcourRepository.deleteById(id);
    }

}
