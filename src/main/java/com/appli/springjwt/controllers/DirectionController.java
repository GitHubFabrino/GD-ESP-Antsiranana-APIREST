package com.appli.springjwt.controllers;

import com.appli.springjwt.dto.DirectionGestionDto;
import com.appli.springjwt.service.DirectionGestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/direction")
public class DirectionController {
    @Autowired
    DirectionGestionService directionGestionService;

    @PostMapping
    @PreAuthorize("hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void post(@RequestBody DirectionGestionDto directionGestionDtos){
        System.out.println("DirectionController : post" );
        directionGestionService.save(directionGestionDtos);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void put(@RequestBody ArrayList<DirectionGestionDto> directionGestionDtos){
        System.out.println("DirectionController : put" );
        directionGestionService.update(directionGestionDtos);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public ArrayList<DirectionGestionDto> list() {
        System.out.println("DirectionController : list" );
        return directionGestionService.get();
    }

    @GetMapping("/directeur")
    public DirectionGestionDto getDirecteur() {
        System.out.println("DirectionController : getDirecteur" );
        return directionGestionService.getDirecteur();
    }

    @GetMapping("/personne/{id}")
    @PreAuthorize("hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public DirectionGestionDto getById(@PathVariable("id") Integer id) {
        System.out.println("DirectionController : getById" );
        return directionGestionService.getById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void deleteCandidat(@PathVariable("id") Integer numero){
        System.out.println("DirectionController : deleteCandidat" );
        directionGestionService.delete(numero);
    }

}
