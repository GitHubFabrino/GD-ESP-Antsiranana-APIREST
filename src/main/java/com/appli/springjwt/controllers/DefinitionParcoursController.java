package com.appli.springjwt.controllers;

import com.appli.springjwt.dto.DefinitionParcoursDto;
import com.appli.springjwt.service.DefinitionParcoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/definition-parcours")
public class DefinitionParcoursController {
    @Autowired
    DefinitionParcoursService definitionParcoursService;

    @PostMapping
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void post(@RequestBody ArrayList<DefinitionParcoursDto> definitionParcoursDtos){
        System.out.println("DefinitionParcoursController : post");
        definitionParcoursService.save(definitionParcoursDtos);
    }

    @GetMapping("/annee/{id}")
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('ENSEIGNANT') or hasAuthority('DIRECTION') or hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('RESPONSABLE_MENTION') or hasAuthority('ETUDIANT') or hasRole('ADMIN')")
    public ArrayList<DefinitionParcoursDto> list(@PathVariable("id") Integer id) {
        System.out.println("DefinitionParcoursController : list");
        return definitionParcoursService.get(id);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void put(@RequestBody ArrayList<DefinitionParcoursDto> definitionParcoursDtos){
        System.out.println("DefinitionParcoursController : put");
        definitionParcoursService.update(definitionParcoursDtos);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void deleteCandidat(@PathVariable("id") Integer numero){
        System.out.println("DefinitionParcoursController : deleteCandidat");
        definitionParcoursService.delete(numero);
    }

}
