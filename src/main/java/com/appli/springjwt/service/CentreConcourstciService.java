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
        System.out.println(concourstciRepository.findById(numero).orElseThrow());
        // return centreconcourstciRepository.findbyIdCtci(concourstci);
        //return centreconcourstciRepository.findAll();
        //return concourstciRepository.findById(numero).orElseThrow();

        // Mande filtrage par nom centre
        // return centreconcourstciRepository.findByNomCentrectci("Antananarivo");

        // Mande filtrage par code postale centre
        return centreconcourstciRepository.findByIdCTCI(concourstci);
        //return null;
    }


    public ArrayList<CentreConcoursTCIDto> getCentreConcoursList(Integer numero) {
        Concourstci concourstci =  concourstciRepository.findById(numero).orElseThrow();

        ArrayList<CentreConcoursTCIDto> centreConcoursTCIDtos= new ArrayList<>();
        ArrayList<Centreconcourstci> ObjCentreConcoursTCI = centreconcourstciRepository.findAllByIdCTCI(concourstci);

        for (Centreconcourstci centre :ObjCentreConcoursTCI ){
            Integer i = 0;
            centreConcoursTCIDtos.add(i, new CentreConcoursTCIDto(
                    centre.getId(),
                    centre.getNomCentreCTCI(),
                    centre.getCodePostale(),
                    centre.getIdPersonne().getNom(),
                    centre.getIdPersonne().getPrenoms(),
                    centre.getIdPersonne().getTelephone()
            ));
            i+=1;
        }
        return centreConcoursTCIDtos;
    }
    public void deleteCentreConcours(Integer id, Integer idCentre) {
        centreconcourstciRepository.deleteByIdAndIdCTCI(
                idCentre,
                concourstciRepository.findById(id).orElseThrow());
    }


}
