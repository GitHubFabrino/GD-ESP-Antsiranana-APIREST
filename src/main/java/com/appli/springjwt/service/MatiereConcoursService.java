package com.appli.springjwt.service;


import com.appli.springjwt.models.MatiereConcours;
import com.appli.springjwt.repository.MatiereConcoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class MatiereConcoursService {
    @Autowired
    private MatiereConcoursRepository matiereConcoursRepository;
    public List<MatiereConcours> getAllMatiereConcours() {
        return matiereConcoursRepository.findAll();
    }
}
