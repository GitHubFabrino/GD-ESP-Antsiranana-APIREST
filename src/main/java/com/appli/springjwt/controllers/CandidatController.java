package com.appli.springjwt.controllers;

import com.appli.springjwt.dto.CandidatConcoursDto;
import com.appli.springjwt.dto.CandidatDto;
import com.appli.springjwt.models.Candidatconcourstci;
import com.appli.springjwt.service.CandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/candidat")

public class CandidatController {

    @Autowired
    CandidatService candidatService;

    @PutMapping
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasAuthority('PRESIDENT_JURY') or hasRole('ADMIN')")
    public List<Candidatconcourstci> putCandidat(@RequestBody CandidatDto candidat){
        System.out.println("CandidatController : putCandidat" );
        candidatService.creerCandidat(candidat);
        return null;
    }

    @GetMapping("/centre/{id}")
   @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasAuthority('PRESIDENT_JURY') or hasRole('ADMIN') or hasRole('PRESIDENT_JURY')")
    public ArrayList<CandidatConcoursDto> listCandidat(@PathVariable("id") Integer numero) {
        System.out.println("CandidatController : putCandidat" );

        return candidatService.getCandidatList(numero);
    }

    @GetMapping("/concours/{idConcours}")
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasAuthority('PRESIDENT_JURY') or hasRole('ADMIN')")
    public ArrayList<CandidatConcoursDto> listCandidatConcours(@PathVariable("idConcours") Integer idConcours) {
        return candidatService.getCandidatConcoursList(idConcours);
    }

    @GetMapping("/concours/{idConcours}/centre")
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasAuthority('PRESIDENT_JURY') or hasRole('ADMIN')")
    public ArrayList<CandidatConcoursDto> listCandidatConcoursn(@PathVariable("idConcours") Integer idConcours) {
        System.out.println("CandidatController : listCandidatConcours" );
        return candidatService.getCandidatConcoursLista(idConcours);
    }
    @GetMapping("/concours/{idConcours}/centre/{idCentre}")
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasAuthority('PRESIDENT_JURY') or hasRole('ADMIN')")
    public ArrayList<CandidatConcoursDto> listCandidatConcoursn(@PathVariable("idConcours") Integer idConcours , @PathVariable("idCentre") Integer idCentre) {
        System.out.println("CandidatController : listCandidatConcours" );

        return candidatService.getCandidatConcoursListResultat(idConcours , idCentre);
    }

    @PutMapping("/concours/{idConcours}/centre/{idCentre}")
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasAuthority('PRESIDENT_JURY') or hasRole('ADMIN')")
    public List<Candidatconcourstci> putCandidatConcours(@RequestBody ArrayList<CandidatConcoursDto> candidat){
        System.out.println("CandidatController : putCandidatConcours" );
        candidatService.creerCandidatConcours(candidat);
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteCandidat(@PathVariable("id") Integer numero){
        System.out.println("CandidatController : deleteCandidat" );
        candidatService.deleteCandidat(numero);
    }


}
