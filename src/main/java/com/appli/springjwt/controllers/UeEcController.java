package com.appli.springjwt.controllers;

import com.appli.springjwt.dto.UeEcDto;
import com.appli.springjwt.service.UeEcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/associer-ue-ec")
public class UeEcController {
    @Autowired
    UeEcService ueEcService;

    @PostMapping
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('RESPONSABLE_MENTION') or hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void post(@RequestBody ArrayList<UeEcDto> ueEcDtos){
        ueEcService.save(ueEcDtos);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('DIRECTION') or hasAuthority('ENSEIGNANT') or hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('RESPONSABLE_MENTION') or hasRole('ADMIN')")
    public ArrayList<UeEcDto> list() {
         return ueEcService.get();
    }

    @PutMapping
    @PreAuthorize("hasAuthority('DIRECTION') or hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('RESPONSABLE_MENTION') or hasRole('ADMIN')")
    public void modifier(@RequestBody ArrayList<UeEcDto> ueEcDtos){
        ueEcService.update(ueEcDtos);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('RESPONSABLE_MENTION') or hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void supprimer(@PathVariable("id") Integer id){
        ueEcService.delete(id);
    }

}
