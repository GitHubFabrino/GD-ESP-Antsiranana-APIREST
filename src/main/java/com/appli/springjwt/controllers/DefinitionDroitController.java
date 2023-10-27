package com.appli.springjwt.controllers;


import com.appli.springjwt.dto.DefinitionDroitDto;
import com.appli.springjwt.service.DefinitionDroitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/definition-droit")
public class DefinitionDroitController {
    @Autowired
    DefinitionDroitService definitionDroitService;

    @PostMapping
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('PRESIDENT_JURY') or hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void post(@RequestBody ArrayList<DefinitionDroitDto> definitionDroitDto){
        System.out.println("DefinitionDroitController : post");
        definitionDroitService.save(definitionDroitDto);
    }

    @GetMapping("/annee/{id}")
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('PRESIDENT_JURY') or hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public ArrayList<DefinitionDroitDto> list(@PathVariable("id") Integer id) {
        System.out.println("DefinitionDroitController : list");
        return definitionDroitService.getDefinitionDroit(id);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('PRESIDENT_JURY') or hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void put(@RequestBody ArrayList<DefinitionDroitDto> definitionDroitDto){
        System.out.println("DefinitionDroitController : put");
        definitionDroitService.update(definitionDroitDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCandidat(@PathVariable("id") Integer numero){
        System.out.println("DefinitionDroitController : deleteCandidat");
        definitionDroitService.delete(numero);
    }

}
