package com.appli.springjwt.controllers;

import com.appli.springjwt.models.CentreConcours;
import com.appli.springjwt.models.Centreconcourstci;
import com.appli.springjwt.repository.CentreConcoursRepository;
import com.appli.springjwt.service.CentreConcoursService;
import com.appli.springjwt.service.CentreConcourstciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/centre")
public class CentreConcourstciController {
    @Autowired
    CentreConcourstciService concourstciService;

    @Autowired
    CentreConcoursService centreConcoursService;

    @Autowired
    CentreConcoursRepository centreConcoursRepository;


    @GetMapping
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasAuthority('PRESIDENT_JURY') or hasRole('ADMIN')")
    public List<Centreconcourstci> listCentre() {
        System.out.println("CentreConcourstciController : listCentre");
        return concourstciService.getConcoursList();
    }


    @GetMapping(path = "/concours")
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasAuthority('PRESIDENT_JURY') or hasRole('ADMIN')")
    public List<CentreConcours> getMatiereconcou(){
        System.out.println("CentreConcourstciController : getMatiereconcou");
        return centreConcoursService.getAllCentreConcours();
    }


    @PostMapping
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('PRESIDENT_JURY') or hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void postCentreConcours(@RequestBody CentreConcours CentreConcours){
        System.out.println("CentreConcourstciController : postCentreConcours");
        centreConcoursRepository.save(CentreConcours);
    }

}
