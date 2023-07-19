package com.appli.springjwt.controllers;

import com.appli.springjwt.dto.OffreFormationDto;
import com.appli.springjwt.service.OffreFormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/offre-formation")
public class OffreFormationController {
    @Autowired
    OffreFormationService offreFormationService;

    @PostMapping
    @PreAuthorize("hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public void post(@RequestBody ArrayList<OffreFormationDto> offreFormationDtos){
        offreFormationService.save(offreFormationDtos);
    }

    @GetMapping("/annee/{id}")
    @PreAuthorize("hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public ArrayList<OffreFormationDto> list(@PathVariable("id") Integer id) {
        return offreFormationService.get(id);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public void put(@RequestBody ArrayList<OffreFormationDto> offreFormationDtos){
        offreFormationService.update(offreFormationDtos);
    }

    @DeleteMapping("/{id}")
    public void deleteCandidat(@PathVariable("id") Integer numero){
        offreFormationService.delete(numero);
    }




}
