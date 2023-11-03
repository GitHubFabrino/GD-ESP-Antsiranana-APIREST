package com.appli.springjwt.service;

import com.appli.springjwt.models.Uniteenseignement;
import com.appli.springjwt.repository.UniteenseignementRepository;
import com.appli.springjwt.dto.UniteEnseignementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UniteEnseignementService {
    @Autowired
    UniteenseignementRepository uniteenseignementRepository;

    public void save(ArrayList<UniteEnseignementDto> uniteEnseignementDtos) {
        for (UniteEnseignementDto ue: uniteEnseignementDtos){

            Uniteenseignement uniteenseignement = new Uniteenseignement();
            uniteenseignement.setCodeUe(ue.getCodeUE());
            uniteenseignement.setNomUe(ue.getNomUE());
            uniteenseignement.setCreditUe(ue.getCreditUE());

            uniteenseignementRepository.save(uniteenseignement);
        }
    }
    public void update(ArrayList<UniteEnseignementDto> uniteEnseignementDtos) {
        for(UniteEnseignementDto dto: uniteEnseignementDtos){
            Uniteenseignement uniteenseignement = uniteenseignementRepository.findById(dto.getId()).orElseThrow();

            uniteenseignement.setCodeUe(dto.getCodeUE());
            uniteenseignement.setCreditUe(dto.getCreditUE());
            uniteenseignement.setNomUe(dto.getNomUE());

            uniteenseignementRepository.save(uniteenseignement);
        }
    }
    public ArrayList<UniteEnseignementDto> get() {
        List<Uniteenseignement> elementconstitutifs = uniteenseignementRepository.findAll();
        ArrayList<UniteEnseignementDto> uniteEnseignementDtos = new ArrayList<>();

        for(Uniteenseignement ue: elementconstitutifs){
            Integer i = 0;
            uniteEnseignementDtos.add(i,new UniteEnseignementDto(
                    ue.getId(),
                    ue.getCodeUe(),
                    ue.getNomUe(),
                    ue.getCreditUe()
            ));
        }
        Collections.reverse(uniteEnseignementDtos);
        return uniteEnseignementDtos;
    }
    public void delete(Integer id) {
        Uniteenseignement uniteenseignement = uniteenseignementRepository.findById(id).orElseThrow();
        uniteenseignementRepository.delete(uniteenseignement);
    }
}
