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
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasAuthority('PRESIDENT_JURY') or hasRole('ADMIN')")
    public List<Object[]> getMatiereConcours(){
        System.out.println(" MatiereConcoursController : getMatiereConcours" );
        return matiereService.getAll();
    }

    @GetMapping(path = "/concours")
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasAuthority('PRESIDENT_JURY') or hasRole('ADMIN')")
    public List<MatiereConcours> getMatiereconcou(){
        System.out.println(" MatiereConcoursController : getMatiereconcou" );
        return matiereConcoursService.getAllMatiereConcours();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('PRESIDENT_JURY') or hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void postMatiereConcours(@RequestBody MatiereConcours matiereConcours){
        System.out.println(" MatiereConcoursController : postMatiereConcours" );
        matiereConcoursRepository.save(matiereConcours);
    }

}
