package com.appli.springjwt.service;

import com.appli.springjwt.dto.HeureenseignementDto;
import com.appli.springjwt.models.*;
import com.appli.springjwt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class HeureEnseignementService {
    @Autowired
    HeureenseignementRepository heureenseignementRepository;
    @Autowired
    AnneeunivRepository anneeunivRepository;
    @Autowired
    EnseignantRepository enseignantRepository;
    @Autowired
    ProgrammeenseignementRepository programmeenseignementRepository;
    @Autowired
    DefinitionparcourRepository definitionparcourRepository;

    public void save(ArrayList<HeureenseignementDto> heureenseignementDtos) {
        for(HeureenseignementDto dto:heureenseignementDtos){

            Heureenseignement heureenseignement = new Heureenseignement();
            heureenseignement.setIdAu(anneeunivRepository.findById(dto.getIdAU()).orElseThrow());
            heureenseignement.setIdEnseignant(enseignantRepository.findById(dto.getIdEnseignant()).orElseThrow());
            heureenseignement.setIdPe(programmeenseignementRepository.findById(dto.getIdPE()).orElseThrow());

            heureenseignementRepository.save(heureenseignement);
        }
    }


    public void update(ArrayList<HeureenseignementDto> heureenseignementDtos) {

        for(HeureenseignementDto dto: heureenseignementDtos){
            Anneeuniv anneeuniv = anneeunivRepository.findById(dto.getIdAU()).orElseThrow();
            Enseignant enseignant = enseignantRepository.findById(dto.getIdEnseignant()).orElseThrow();
            Programmeenseignement programme = programmeenseignementRepository.findById(dto.getIdPE()).orElseThrow();
            Heureenseignement heureenseignement =  heureenseignementRepository.findByIdAuAndIdEnseignantAndIdPe(
                    anneeuniv,enseignant,programme).orElseThrow();

            heureenseignement.setComptageHeureEt(dto.getComptageHeureET());
            heureenseignement.setComptageHeureEd(dto.getComptageHeureED());
            heureenseignement.setComptageHeureTp(dto.getComptageHeureTP());
            heureenseignement.setComptageHeureAutre(dto.getComptageHeureAutre());
            heureenseignement.setComptageHeureTotal(dto.getComptageHeureTotal());

            heureenseignementRepository.save(heureenseignement);
        }
    }

    public void delete(ArrayList<HeureenseignementDto> heureenseignementDtos) {
        for(HeureenseignementDto dto: heureenseignementDtos){
            Anneeuniv anneeuniv = anneeunivRepository.findById(dto.getIdAU()).orElseThrow();
            Enseignant enseignant = enseignantRepository.findById(dto.getIdEnseignant()).orElseThrow();
            Programmeenseignement programme = programmeenseignementRepository.findById(dto.getIdPE()).orElseThrow();
            Heureenseignement heureenseignement =  heureenseignementRepository.findByIdAuAndIdEnseignantAndIdPe(
                    anneeuniv,enseignant,programme).orElseThrow();
            heureenseignementRepository.save(heureenseignement);

        }
    }

    public ArrayList<HeureenseignementDto> get(Integer idAnnee, Integer idDp) {

            Anneeuniv anneeuniv = anneeunivRepository.findById(idAnnee).orElseThrow();
            Definitionparcour definitionparcour = definitionparcourRepository.findById(idDp).orElseThrow();

            Set<Programmeenseignement> pE = definitionparcour.getProgrammeenseignements();

            ArrayList<HeureenseignementDto> heureenseignementDto = new ArrayList<>();

            for(Programmeenseignement programme :pE)  {
                Integer i= 0 ;
                List<Heureenseignement> heureenseignement = heureenseignementRepository.findAllByIdAuAndIdPe(anneeuniv, programme);
                for(Heureenseignement h : heureenseignement ){
                    Integer j = 0;
                    heureenseignementDto.add(i,new HeureenseignementDto(
                            h.getIdAu().getId(),
                            h.getIdEnseignant().getId(),
                            h.getIdPe().getId(),
                            h.getComptageHeureEt(),
                            h.getComptageHeureEd(),
                            h.getComptageHeureTp(),
                            h.getComptageHeureAutre(),
                            h.getComptageHeureTotal()
                    ));
                    j+=1;
                }
                i+=1;
            }

        return heureenseignementDto;
    }

    @Transactional
    public void delete(Integer idAnnee, Integer idEnseignant, Integer idPe) {

        Anneeuniv anneeuniv = anneeunivRepository.findById(idAnnee).orElseThrow();
        Enseignant enseignant = enseignantRepository.findById(idEnseignant).orElseThrow();
        Programmeenseignement programme = programmeenseignementRepository.findById(idPe).orElseThrow();

        heureenseignementRepository.deleteByIdAuAndIdEnseignantAndIdPe(anneeuniv,enseignant,programme);
    }
}




