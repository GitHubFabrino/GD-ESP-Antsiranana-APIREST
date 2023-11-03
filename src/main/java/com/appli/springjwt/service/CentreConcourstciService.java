package com.appli.springjwt.service;

import com.appli.springjwt.repository.CentreconcourstciRepository;
import com.appli.springjwt.repository.ConcourstciRepository;
import com.appli.springjwt.dto.CentreConcoursTCIDto;
import com.appli.springjwt.models.Centreconcourstci;
import com.appli.springjwt.models.Concourstci;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Transactional
@Service
public class CentreConcourstciService {

    @Autowired
    CentreconcourstciRepository centreconcourstciRepository;
    @Autowired
    ConcourstciRepository concourstciRepository;

    public List<Centreconcourstci> getConcoursList() {
        return centreconcourstciRepository.findAll();
    }


    public List<Centreconcourstci> getCentreByConcoursId(Integer numero) {
        Concourstci concourstci =  concourstciRepository.findById(numero).orElseThrow();
        return centreconcourstciRepository.findByIdCTCI(concourstci);

    }

    public List<CentreConcoursTCIDto> getCentreConcoursList(Integer numero) {
        Concourstci concourstci = concourstciRepository.findById(numero).orElseThrow();

        List<CentreConcoursTCIDto> centreConcoursTCIDtos = new ArrayList<>();
        List<Centreconcourstci> objCentreConcoursTCI = centreconcourstciRepository.findAllByIdCTCI(concourstci);

        for (Centreconcourstci centre : objCentreConcoursTCI) {
            CentreConcoursTCIDto centreDto = new CentreConcoursTCIDto(
                    centre.getId(),
                    centre.getNomCentreCTCI(),
                    centre.getCodePostale(),
                    centre.getIdPersonne().getNom(),
                    centre.getIdPersonne().getPrenoms(),
                    centre.getIdPersonne().getTelephone(),
                    centre.getIdPersonneAdjoit().getNom(),
                    centre.getIdPersonneAdjoit().getPrenoms(),
                    centre.getIdCTCI()
            );
            centreConcoursTCIDtos.add(centreDto);
        }

        Collections.reverse(centreConcoursTCIDtos);
        return centreConcoursTCIDtos;
    }

    public void deleteCentreConcours(Integer id, Integer idCTCI) {
        centreconcourstciRepository.deleteByIdAndIdCTCI(
                idCTCI,
                concourstciRepository.findById(id).orElseThrow());
    }

}
