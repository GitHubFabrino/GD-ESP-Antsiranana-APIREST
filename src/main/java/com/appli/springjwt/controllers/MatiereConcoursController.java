package com.appli.springjwt.controllers;


import com.appli.springjwt.models.MatiereConcours;
import com.appli.springjwt.repository.MatiereConcoursRepository;
import com.appli.springjwt.service.MatiereConcoursService;
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


    @Autowired
    MatiereConcoursService matiereConcoursService;

    @Autowired
    MatiereConcoursRepository matiereConcoursRepository;


    @GetMapping
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public List<Object[]> getMatiereConcours(){
        return matiereService.getAll();

        //return null;
    }

    @GetMapping(path = "/concours")
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public List<MatiereConcours> getMatiereconcou(){
        System.out.println("Donnée getée : ");
        System.out.println(matiereConcoursService.getAllMatiereConcours());
        return matiereConcoursService.getAllMatiereConcours();
    }


    @PostMapping
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void postMatiereConcours(@RequestBody MatiereConcours matiereConcours){
        System.out.println("Donnée recus : ");
        System.out.println(matiereConcours);
        matiereConcoursRepository.save(matiereConcours);
        System.out.println("Matiere ajoutée");
    }

}
