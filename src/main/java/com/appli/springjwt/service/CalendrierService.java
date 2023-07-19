package com.appli.springjwt.service;

import com.appli.springjwt.repository.CalendrierconcourstciRepository;
import com.appli.springjwt.repository.ConcourstciRepository;
import com.appli.springjwt.repository.MatiereconcourstciRepository;
import com.appli.springjwt.dto.CalendrierConcoursTCIDto;
import com.appli.springjwt.models.Calendrierconcourstci;
import com.appli.springjwt.models.Concourstci;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class CalendrierService {

    @Autowired
    private ConcourstciRepository concourstciRepository;
    @Autowired
    private CalendrierconcourstciRepository calendrierconcourstciRepository;
    @Autowired
    private MatiereconcourstciRepository matiereconcourstciRepository;


    public ArrayList<CalendrierConcoursTCIDto> getCalendrierConcoursList(Integer numero) {
        Concourstci concourstci = concourstciRepository.findById(numero).orElseThrow();

        ArrayList<CalendrierConcoursTCIDto> calendrierConcoursTCIDtos = new ArrayList<>();

        ArrayList<Calendrierconcourstci> calendrierconcourstci = calendrierconcourstciRepository.findAllByIdCTCI(concourstci);

        for (Calendrierconcourstci calendrier : calendrierconcourstci) {
            Integer i = 0;
            calendrierConcoursTCIDtos.add(i, new CalendrierConcoursTCIDto(
                    calendrier.getIdCTCI().getId(),
                    calendrier.getIdMCTCI().getId(),
                    calendrier.getIdMCTCI().getNomMCTCI(),
                    calendrier.getDateCalendrierCTCI(),
                    calendrier.getDebutHeureCalendrierCTCI(),
                    calendrier.getFinHeureCalendrierCTCI()
            ));
            i+=1;
        }
            return calendrierConcoursTCIDtos;
    }

}
