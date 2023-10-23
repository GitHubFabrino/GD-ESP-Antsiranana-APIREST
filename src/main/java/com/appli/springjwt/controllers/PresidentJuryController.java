package com.appli.springjwt.controllers;

import com.appli.springjwt.dto.DefinitionParcoursDto;
import com.appli.springjwt.dto.DefinitionPresidentJuryDto;
import com.appli.springjwt.dto.PresidentJuryDto;
import com.appli.springjwt.models.PresidentJuryModel;
import com.appli.springjwt.repository.PresidentJuryRepository;
import com.appli.springjwt.service.PresidentJuryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test/presidentjury")
public class PresidentJuryController {
    @Autowired
    PresidentJuryService presidentJuryService;

    @Autowired
    PresidentJuryRepository presidentJuryRepository;




    @PostMapping
    @PreAuthorize("hasAuthority('DIRECTION') or hasRole('ADMIN') or hasAuthority('SCOLARITE')")
    public void post(@RequestBody DefinitionPresidentJuryDto presidentJury){
        System.out.println("ok");
        System.out.println(presidentJury.getIdAU());
        System.out.println(presidentJury.getIdEnseignant());
        System.out.println(presidentJury.getIdConcour());

        Optional<PresidentJuryModel> concourExist = presidentJuryRepository.findById(presidentJury.getIdConcour());
        if (concourExist.isPresent()){
            System.out.println("Efa miexist");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "existe déjà");
        }else {
            this.presidentJuryService.save(presidentJury);
        }

    }

    @GetMapping("/list")
   /* @PreAuthorize("hasAuthority('DIRECTION') or hasRole('ADMIN')")*/
    public ArrayList<PresidentJuryDto> list() {
        System.out.println("ato zay");
        return presidentJuryService.getList();
    }


    @DeleteMapping("/delete/{id_pdj}")
   /* @PreAuthorize("hasAuthority('DIRECTION') or hasRole('ADMIN')")*/
    public void deleteCandidat(@PathVariable("id_pdj") Integer numero){
        presidentJuryService.delete(numero);
    }

}
