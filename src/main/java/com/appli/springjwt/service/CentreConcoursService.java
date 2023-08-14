package com.appli.springjwt.service;

import com.appli.springjwt.models.CentreConcours;
import com.appli.springjwt.repository.CentreConcoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class CentreConcoursService {
    @Autowired
    private CentreConcoursRepository centreConcoursRepository;
    public List<CentreConcours> getAllCentreConcours() {
        return centreConcoursRepository.findAll();
    }
}
