package com.appli.springjwt.controllers;


import com.appli.springjwt.models.Niveau;
import com.appli.springjwt.repository.NiveauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/niveau")

public class NiveauController {
    @Autowired
    NiveauRepository niveauRepository;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasAuthority('ENSEIGNANT') or hasAuthority('DIRECTION') or hasAuthority('RESPONSABLE_MENTION') or hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('PRESIDENT_JURY') or hasAuthority('SCOLARITE') or hasAuthority('ETUDIANT') or hasRole('ADMIN')")
    public List<Niveau> listNiveau() {
        System.out.println("NiveauController : listNiveau");
        return niveauRepository.findAll();
    }

}
