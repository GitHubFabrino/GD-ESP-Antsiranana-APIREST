package com.appli.springjwt.service;

import com.appli.springjwt.models.UeEc;
import com.appli.springjwt.repository.ElementconstitutifRepository;
import com.appli.springjwt.repository.UeEcRepository;
import com.appli.springjwt.repository.UniteenseignementRepository;
import com.appli.springjwt.dto.UeEcDto;

import com.appli.springjwt.repository.EnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UeEcService {
    @Autowired
    UeEcRepository ueEcRepository;
    @Autowired
    ElementconstitutifRepository elementconstitutifRepository;
    @Autowired
    UniteenseignementRepository uniteenseignementRepository;
    @Autowired
    EnseignantRepository enseignantRepository;

    public void save(ArrayList<UeEcDto> ueEcDtos) {
        for (UeEcDto ueEc: ueEcDtos){

            UeEc associerUeEc = new UeEc();

            associerUeEc.setIdEnseignant(enseignantRepository.findById(ueEc.getIdEnseignant()).orElseThrow());
            associerUeEc.setIdEc(elementconstitutifRepository.findById(ueEc.getIdEC()).orElseThrow());
            associerUeEc.setIdUe(uniteenseignementRepository.findById(ueEc.getIdUE()).orElseThrow());
            associerUeEc.setCreditEc(ueEc.getCreditEC());
            associerUeEc.setCoefficientEc(ueEc.getCoefficientEC());
            associerUeEc.setVolumeHoraireEd(ueEc.getVolumeHoraireED());
            associerUeEc.setVolumeHoraireEt(ueEc.getVolumeHoraireET());
            associerUeEc.setVolumeHoraireTp(ueEc.getVolumeHoraireTP());
            associerUeEc.setVolumeHoraireAutre(ueEc.getVolumeHoraireAutre());
            associerUeEc.setVolumeHoraireTotal(ueEc.getVolumeHoraireTotal());

            ueEcRepository.save(associerUeEc);
        }
    }
    public ArrayList<UeEcDto> get() {
        List<UeEc> associerUeEc = ueEcRepository.findAll();
        ArrayList<UeEcDto> ueEcDtos = new ArrayList<>();

        for(UeEc ueEc: associerUeEc){
            Integer i = 0 ;
            ueEcDtos.add(i, new UeEcDto(
                    ueEc.getId(),
                    ueEc.getIdEnseignant().getId(),
                    ueEc.getIdUe().getId(),
                    ueEc.getIdEc().getId(),
                    ueEc.getCreditEc(),
                    ueEc.getCoefficientEc(),
                    ueEc.getVolumeHoraireEt(),
                    ueEc.getVolumeHoraireEd(),
                    ueEc.getVolumeHoraireTp(),
                    ueEc.getVolumeHoraireAutre(),
                    ueEc.getVolumeHoraireTotal()
            ));
            i+=0;
        }
        return ueEcDtos;
    }
    public void update(ArrayList<UeEcDto> ueEcDtos) {

        for(UeEcDto ueEc :ueEcDtos){
            UeEc associerUeEc = ueEcRepository.findById(ueEc.getId()).orElseThrow();

            associerUeEc.setCreditEc(ueEc.getCreditEC());
            associerUeEc.setCoefficientEc(ueEc.getCoefficientEC());
            associerUeEc.setVolumeHoraireEt(ueEc.getVolumeHoraireET());
            associerUeEc.setVolumeHoraireEd(ueEc.getVolumeHoraireED());
            associerUeEc.setVolumeHoraireTp(ueEc.getVolumeHoraireTP());
            associerUeEc.setVolumeHoraireAutre(ueEc.getVolumeHoraireAutre());
            associerUeEc.setVolumeHoraireTotal(ueEc.getVolumeHoraireTotal());
            associerUeEc.setIdEnseignant(enseignantRepository.findById(ueEc.getIdEnseignant()).orElseThrow());

            ueEcRepository.save(associerUeEc);
        }
    }
    public void delete(Integer id) {
        UeEc ueEc = ueEcRepository.findById(id).orElseThrow();
        ueEcRepository.delete(ueEc);
    }

}
