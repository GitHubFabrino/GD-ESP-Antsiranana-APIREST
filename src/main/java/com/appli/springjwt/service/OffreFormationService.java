package com.appli.springjwt.service;

import com.appli.springjwt.models.*;
import com.appli.springjwt.repository.*;
import com.appli.springjwt.dto.OffreFormationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class OffreFormationService {
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
    OffreformationRepository offreformationRepository;

    public void save(ArrayList<OffreFormationDto> dto) {

        for(OffreFormationDto offreFormationDto:dto){

            Offreformation offreformation = new Offreformation();
            Anneeuniv annee = anneeunivRepository.findById(offreFormationDto.getIdAU()).orElseThrow();
            Semestre semestre = semestreRepository.findById(offreFormationDto.getIdSemestre()).orElseThrow();
            Mention mention = mentionRepository.findById(offreFormationDto.getIdMention()).orElseThrow();
            Niveau niveau = niveauRepository.findById(offreFormationDto.getIdNiveau()).orElseThrow();

            Parcour parcour = new Parcour();

            if(parcourRepository.existsByParcours(offreFormationDto.getParcours())){
                parcour = parcourRepository.findByParcours(offreFormationDto.getParcours()).orElseThrow();
            } else {
                parcour.setAcronymeParcours(offreFormationDto.getAcronymeParcours());
                parcour.setParcours(offreFormationDto.getParcours());
            }

            offreformation.setIdAu(annee);
            offreformation.setIdSemestre(semestre);
            offreformation.setIdMention(mention);
            offreformation.setIdNiveau(niveau);
            offreformation.setIdParcours(parcour);

            parcourRepository.save(parcour);
            offreformationRepository.save(offreformation);

        }
    }

    public ArrayList<OffreFormationDto> get(Integer id) {
        ArrayList<Offreformation> Objoffre = offreformationRepository.findAllByIdAu(anneeunivRepository.findById(id).orElseThrow());
        ArrayList<OffreFormationDto> offreFormationDtos = new ArrayList<>();

        for(Offreformation offreformation :Objoffre){
            Integer i = 0;
            offreFormationDtos.add(i,new OffreFormationDto(
                    offreformation.getId(),
                    offreformation.getIdAu().getId(),
                    offreformation.getIdSemestre().getId(),
                    offreformation.getIdMention().getId(),
                    offreformation.getIdNiveau().getId(),
                    offreformation.getIdParcours().getId(),
                    offreformation.getIdParcours().getAcronymeParcours(),
                    offreformation.getIdParcours().getParcours()
            ));
            i+=1;
        }
        Collections.reverse(offreFormationDtos);
         return offreFormationDtos;
    }

    public void update(ArrayList<OffreFormationDto> dto) {

        for(OffreFormationDto offreFormationDto: dto){
            Offreformation offreformation =  offreformationRepository.findById(offreFormationDto.getId()).orElseThrow();

            offreformation.getIdParcours().setParcours(offreFormationDto.getParcours());
            offreformation.getIdParcours().setAcronymeParcours(offreFormationDto.getAcronymeParcours());

            offreformationRepository.save(offreformation);
        }
    }

    public void delete(Integer id) {
        offreformationRepository.deleteById(id);
    }



}
