package com.appli.springjwt.controllers;

import com.appli.springjwt.dto.CandidatConcoursDto;
import com.appli.springjwt.dto.CentreConcoursTCIDto;
import com.appli.springjwt.service.CandidatService;
import com.appli.springjwt.service.CentreConcourstciService;
import com.appli.springjwt.service.ConcourstciService;
import com.appli.springjwt.view.ConcourstciView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/resultatConcours")

public class ResultatConcoursTCI {

    @Autowired
    CandidatService candidatService;

    @Autowired
    ConcourstciService concourstciService;

    @Autowired
    CentreConcourstciService centreConcourstciService;

    @GetMapping("/concours/{idConcours}/centre/{idCentre}")
    public ArrayList<CandidatConcoursDto> listCandidatConcours(@PathVariable("idConcours") Integer idConcours, @PathVariable("idCentre") Integer idCentre) {
        System.out.println("CandidatController : listCandidatConcours" );
        return candidatService.getCandidatConcoursListResultat(idConcours,idCentre);
    }

    @GetMapping("/listConcours")
    public List<ConcourstciView> listConcours() {
        System.out.println("ConcourstciController : listConcours");
        return concourstciService.getConcoursList();
    }

    @GetMapping("/{idConcours}/centre")
    public ArrayList<CentreConcoursTCIDto> getCentreConcours(@PathVariable("idConcours") Integer numero){
        System.out.println("ConcourstciController : getCentreConcours");
        return (ArrayList<CentreConcoursTCIDto>) centreConcourstciService.getCentreConcoursList(numero);
    }
}
