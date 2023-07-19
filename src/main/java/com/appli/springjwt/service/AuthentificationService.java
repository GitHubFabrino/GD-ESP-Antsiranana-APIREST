package com.appli.springjwt.service;

import com.appli.springjwt.repository.AuthentificationRepository;
import com.appli.springjwt.models.Authentification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationService {
@Autowired
    private AuthentificationRepository authentificationRepository;

    public Authentification getAuthentificationById(Long id){
        return authentificationRepository.findById(id).orElseThrow();
    }
}
