package com.appli.springjwt.controllers;


import com.appli.springjwt.service.ResponsableParcoursService;
import com.appli.springjwt.dto.DefinitionParcoursDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/responsable-parcours")
public class ResponsableParcoursController {
    @Autowired
    ResponsableParcoursService responsableParcoursService;

    @PostMapping
    @PreAuthorize("hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void post(@RequestBody ArrayList<DefinitionParcoursDto> definitionParcoursDto){
        responsableParcoursService.save(definitionParcoursDto);
    }

    @GetMapping("/annee/{id}")
    @PreAuthorize("hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public ArrayList<DefinitionParcoursDto> list(@PathVariable("id") Integer idAU) {
        return responsableParcoursService.get(idAU);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void deleteCandidat(@PathVariable("id") Integer numero){
        responsableParcoursService.delete(numero);
    }

}
