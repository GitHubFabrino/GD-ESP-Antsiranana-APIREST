package com.appli.springjwt.controllers;

import com.appli.springjwt.dto.HeureenseignementDto;
import com.appli.springjwt.service.HeureEnseignementService;
import com.appli.springjwt.message.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/heure-enseignement")

public class HeureEnseignementController {
    @Autowired
    HeureEnseignementService heureEnseignementService;

    @PostMapping
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('RESPONSABLE_MENTION') or hasRole('ADMIN')")
    public ResponseEntity<?> post(@RequestBody ArrayList<HeureenseignementDto> heureenseignementDtos){
        System.out.println("HeureEnseignementController : post");
        String message = "";
        try {
            heureEnseignementService.save(heureenseignementDtos);
            message = "Success ";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        }catch (Exception e){
            message = "Error: Data is already exist!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }

    }

    @PutMapping
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('RESPONSABLE_MENTION') or hasRole('ADMIN')")
    public void put(@RequestBody ArrayList<HeureenseignementDto> heureenseignementDtos){
        heureEnseignementService.update(heureenseignementDtos);
    }

    @GetMapping("/annee/{idAnnee}/dp/{idDp}")
    @PreAuthorize("hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('ENSEIGNANT') or hasAuthority('RESPONSABLE_MENTION') or hasRole('ADMIN')")
    public ArrayList<HeureenseignementDto> list(@PathVariable("idAnnee") Integer idAnnee, @PathVariable("idDp") Integer idDp ) {
        System.out.println("HeureEnseignementController : list");
        return heureEnseignementService.get(idAnnee,idDp);
    }

    @DeleteMapping("/annee/{idAnnee}/enseignant/{idEnseignant}/pe/{idPe}")
    @PreAuthorize("hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('RESPONSABLE_MENTION') or hasRole('ADMIN')")
    public void supprimer(@PathVariable("idAnnee") Integer idAnnee, @PathVariable("idEnseignant") Integer idEnseignant, @PathVariable("idPe") Integer idPe){
        System.out.println("HeureEnseignementController : supprimer");
        heureEnseignementService.delete(idAnnee,idEnseignant,idPe);
    }

}


