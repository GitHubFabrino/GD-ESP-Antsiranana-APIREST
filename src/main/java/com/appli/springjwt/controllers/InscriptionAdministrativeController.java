package com.appli.springjwt.controllers;


import com.appli.springjwt.dto.InscriptionAdministrativeDto;
import com.appli.springjwt.service.InscriptionAdministrativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/inscription-administrative")
public class InscriptionAdministrativeController {
    @Autowired
    InscriptionAdministrativeService inscriptionAdministrativeService;

    @PostMapping
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('ETUDIANT') or hasRole('ADMIN')")
    public void post(@RequestBody ArrayList<InscriptionAdministrativeDto> inscriptionAdministrativeDtos){
        System.out.println(" InscriptionAdministrativeController : post" );
        inscriptionAdministrativeService.save(inscriptionAdministrativeDtos);
    }

    @GetMapping("/annee/{idAnnee}/niveau/{idNiveau}")
    @PreAuthorize("hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public  ArrayList<InscriptionAdministrativeDto> get(@PathVariable("idNiveau") Integer idNiveau, @PathVariable("idAnnee") Integer idAnnee){
        System.out.println(" InscriptionAdministrativeController : get" );
        return  inscriptionAdministrativeService.getByIdAU(idNiveau, idAnnee);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('ETUDIANT') or hasRole('ADMIN')")
    public void put(@PathVariable("id") Integer id, @RequestBody InscriptionAdministrativeDto inscriptionAdministrativeDto){
        System.out.println(" InscriptionAdministrativeController : put" );
        inscriptionAdministrativeService.update(id, inscriptionAdministrativeDto);
    }

}
