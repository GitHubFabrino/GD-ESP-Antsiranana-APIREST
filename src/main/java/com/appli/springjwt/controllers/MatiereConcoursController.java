package com.appli.springjwt.controllers;


import com.appli.springjwt.service.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/matiere")
public class MatiereConcoursController {
    @Autowired
    MatiereService matiereService;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public List<Object[]> getMatiereConcours(){
        return matiereService.getAll();

        //return null;
    }

}
