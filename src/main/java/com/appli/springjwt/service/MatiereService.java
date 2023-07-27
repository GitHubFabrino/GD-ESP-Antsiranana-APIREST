package com.appli.springjwt.service;

import com.appli.springjwt.repository.CalendrierconcourstciRepository;
import com.appli.springjwt.repository.ConcourstciRepository;
import com.appli.springjwt.repository.MatiereconcourstciRepository;
import com.appli.springjwt.dto.MatiereDto;
import com.appli.springjwt.models.Calendrierconcourstci;
import com.appli.springjwt.models.Concourstci;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Transactional
@Service
public class MatiereService {

    @Autowired
    private ConcourstciRepository concourstciRepository;
    @Autowired
    private CalendrierconcourstciRepository calendrierconcourstciRepository;

    @Autowired
    private MatiereconcourstciRepository matiereconcourstciRepository;

    public ArrayList<MatiereDto> getMatiereConcoursList(Integer numero) {
        Concourstci concourstci =  concourstciRepository.findById(numero).orElseThrow();

        ArrayList<MatiereDto> matiereDtos = new ArrayList<>();

        ArrayList<Calendrierconcourstci> calendrierconcourstci = calendrierconcourstciRepository.findAllByIdCTCI(concourstci);

        for (Calendrierconcourstci calendrier: calendrierconcourstci ){
            Integer i = 0;
            matiereDtos.add(i,new MatiereDto(
                    calendrier.getIdMCTCI().getId(),
                    calendrier.getIdMCTCI().getNomMCTCI(),
                    calendrier.getIdMCTCI().getCreditMCTCI()
            ));
            System.out.println("matiereDtos : " + matiereDtos);
            i+=1;
        }
        Collections.reverse(matiereDtos);
        return matiereDtos;
    }
    public List<Object[]> getAll() {
        return matiereconcourstciRepository.findAllDistinctData();
    }
    public void deleteMatiereConcours(Integer id, Integer idMatiere) {

        calendrierconcourstciRepository.deleteByIdCTCIAndIdMCTCI(
                concourstciRepository.findById(id).orElseThrow(),
                matiereconcourstciRepository.findById(idMatiere).orElseThrow()
        );
    }


}
