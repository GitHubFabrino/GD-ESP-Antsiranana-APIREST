package com.appli.springjwt.controllers;

import com.appli.springjwt.dto.ScolariteDto;
import com.appli.springjwt.repository.UserRepository;
import com.appli.springjwt.service.ScolariteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/scolarite")
public class ScolariteController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ScolariteService scolariteService;

    @PostMapping
    @PreAuthorize("hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void post(@RequestBody ScolariteDto scolariteDtos){
/*
        if (userRepository.existsByUsername(scolariteDtos.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }else {
        }
 */
        scolariteService.save(scolariteDtos);
    }

    @PutMapping("/tache")
    @PreAuthorize("hasAuthority('DIRECTION') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public void putTache(@RequestBody ArrayList<ScolariteDto> scolariteDtos){
        scolariteService.updateTache(scolariteDtos);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void put(@RequestBody ArrayList<ScolariteDto> scolariteDtos){
        scolariteService.update(scolariteDtos);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('DIRECTION') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public ArrayList<ScolariteDto> list() {
        return scolariteService.get();
    }

    @GetMapping("/personne/{id}")
    @PreAuthorize("hasAuthority('DIRECTION') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public ScolariteDto getById(@PathVariable("id") Integer id) {
        return scolariteService.getById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void deleteCandidat(@PathVariable("id") Integer numero){
        scolariteService.delete(numero);
    }

}
