package com.appli.springjwt.controllers;


import com.appli.springjwt.dto.PersonneDto;
import com.appli.springjwt.models.Personne;
import com.appli.springjwt.repository.AuthentificationRepository;
import com.appli.springjwt.repository.PersonneRepository;
import com.appli.springjwt.service.AuthentificationService;
import com.appli.springjwt.models.Authentification;
import com.appli.springjwt.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/personne")
public class PersonneController {
    @Autowired
    private PersonneService personneService;
    @Autowired
    private AuthentificationService authentificationService;
    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    AuthentificationRepository authentificationRepository;


    @PostMapping
    @PreAuthorize("hasRole('USER') or hasAuthority('DIRECTION')or hasAuthority('PRESIDENT_JURY') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public List<Authentification> postPersonne(@RequestBody Personne personne){
        System.out.println("La methode post a été invoqué");
        personneService.creerPersonne(personne);
        return authentificationRepository.findAll();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasAuthority('PRESIDENT_JURY') or hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('RESPONSABLE_MENTION') or hasAuthority('DIRECTION') or hasAuthority('ETUDIANT') or hasRole('ADMIN')")
    public void putPersonne(@PathVariable("id") Integer numero, @RequestBody Personne personne){
        System.out.println("La methode put a été invoqué " );
        personneService.modifierPersonne(numero, personne);

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasAuthority('PRESIDENT_JURY') or hasAuthority('DIRECTION') or hasAuthority('ENSEIGNANT') or hasAuthority('RESPONSABLE_MENTION') or hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('ETUDIANT') or hasRole('ADMIN')")
    public PersonneDto get(@PathVariable("id") Integer numero){
        System.out.println("La methode get a été invoqué");

        return personneService.getPersonneById(numero);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(" hasAuthority('DIRECTION') or hasAuthority('SCOLARITE') or hasAuthority('PRESIDENT_JURY') or hasRole('ADMIN')")
    public void deletePersonne(@PathVariable("id") Integer numero){
        personneService.deletePersonne(numero);
    }

}
