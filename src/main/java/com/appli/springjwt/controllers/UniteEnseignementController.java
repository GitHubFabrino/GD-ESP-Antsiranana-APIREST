package com.appli.springjwt.controllers;

import com.appli.springjwt.dto.UniteEnseignementDto;
import com.appli.springjwt.service.UniteEnseignementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/unite-enseignement")
public class UniteEnseignementController {
    @Autowired
    UniteEnseignementService uniteEnseignementService;

    @PostMapping
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('RESPONSABLE_MENTION') or hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void post(@RequestBody ArrayList<UniteEnseignementDto> uniteEnseignementDtos){
        uniteEnseignementService.save(uniteEnseignementDtos);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('DIRECTION') or hasAuthority('ENSEIGNANT') or hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('RESPONSABLE_MENTION') or hasRole('ADMIN')")
    public ArrayList<UniteEnseignementDto> listUE() {
       return uniteEnseignementService.get();
    }

    @PutMapping
    @PreAuthorize("hasAuthority('DIRECTION') or hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('RESPONSABLE_MENTION') or hasRole('ADMIN')")
    public void modifier(@RequestBody ArrayList<UniteEnseignementDto> uniteEnseignementDtos){
        uniteEnseignementService.update(uniteEnseignementDtos);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('RESPONSABLE_MENTION') or hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void supprimer(@PathVariable("id") Integer id){
        uniteEnseignementService.delete(id);

    }

}
