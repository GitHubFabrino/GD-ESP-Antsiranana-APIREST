package com.appli.springjwt.controllers;

import com.appli.springjwt.dto.EnseignantDto;
import com.appli.springjwt.service.EnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/enseignant")
public class EnseignantController {
    @Autowired
    EnseignantService enseignantService;

    @PostMapping
    @PreAuthorize("hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void post(@RequestBody EnseignantDto enseignantDto){
    /*
        if (userRepository.existsByUsername(scolariteDtos.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }else {
        }
 */
        enseignantService.save(enseignantDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void put(@PathVariable("id") Integer id,@RequestBody ArrayList<EnseignantDto> enseignantDto){
        enseignantService.update(id, enseignantDto);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('DIRECTION') or hasRole('ADMIN') or hasAuthority('RESPONSABLE_MENTION')")
    public ArrayList<EnseignantDto> list() {
         return enseignantService.get();
    }

    @GetMapping("/personne/{id}")
    @PreAuthorize("hasAuthority('DIRECTION') or hasAuthority('SCOLARITE') or hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('RESPONSABLE_MENTION') or hasAuthority('ENSEIGNANT') or hasRole('ADMIN')")
    public EnseignantDto getById(@PathVariable("id") Integer id) {
         return enseignantService.getById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void deleteEnseignant(@PathVariable("id") Integer numero){
        enseignantService.delete(numero);
    }

}
