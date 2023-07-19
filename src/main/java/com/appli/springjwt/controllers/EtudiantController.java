package com.appli.springjwt.controllers;


import com.appli.springjwt.dto.*;
import com.appli.springjwt.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/etudiant")
public class EtudiantController {
    @Autowired
    EtudiantService etudiantService;

    @PutMapping("/deliberation/dp/{id1}/{id2}")
    @PreAuthorize("hasAuthority('RESPONSABLE_PARCOURS')")
    public void postReleveNote(@PathVariable("id1") Integer id1, @PathVariable("id2") Integer id2, @RequestBody DeliberationAUDto deliberationAUDto){
        etudiantService.creerResultat(id2, deliberationAUDto,id1);
    }

    @GetMapping("/personne/{id}")
    @PreAuthorize("hasAuthority('DIRECTION') or hasAuthority('SCOLARITE') or hasAuthority('ENSEIGNANT') or hasAuthority('ETUDIANT') or hasRole('ADMIN')")
    public EtudiantDto getById(@PathVariable("id") Integer id) {
        return etudiantService.getById(id);
    }

    @GetMapping("/dp/{id}")
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('ENSEIGNANT') or hasAuthority('DIRECTION') or hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('RESPONSABLE_MENTION') or hasRole('ADMIN')")
    public ArrayList<CursusDto> get(@PathVariable("id") Integer id){
        return  etudiantService.get(id);
    }

    @GetMapping("/inscription-pedagogique/dp/{id}")
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('DIRECTION') or hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('RESPONSABLE_MENTION') or hasRole('ADMIN')")
    public ArrayList<CursusDto> getIP(@PathVariable("id") Integer id){
        return  etudiantService.getByIdDP(id);
    }

    @GetMapping("/resultat/dp/{id}")
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('DIRECTION') or hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('RESPONSABLE_MENTION') or hasRole('ENSEIGNANT')")
    public ArrayList<ResultatAUDto> getResultat(@PathVariable("id") Integer id){
        return  etudiantService.getResultatByIdDP(id);
    }

    @GetMapping("/attestation/{idPersonne}/{idDp}")
    @PreAuthorize("hasAuthority('DIRECTION') or hasAuthority('SCOLARITE') or hasAuthority('ENSEIGNANT') or hasAuthority('ETUDIANT')")
    public AttestationDto getAttestattion(@PathVariable("idPersonne") Integer idPersonne, @PathVariable("idDp") Integer idDp) {
        return etudiantService.getAttestation(idPersonne,idDp);
    }
}
