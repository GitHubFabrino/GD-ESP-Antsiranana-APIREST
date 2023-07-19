package com.appli.springjwt.controllers;

import com.appli.springjwt.models.Mention;
import com.appli.springjwt.repository.ParcourRepository;
import com.appli.springjwt.models.Parcour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/parcours")
public class ParcoursController {
@Autowired
ParcourRepository parcourRepository;
    @PostMapping
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void post(@RequestBody ArrayList<Parcour> parcoursdto){
        for(Parcour parcour: parcoursdto){

            parcourRepository.save(parcour);
        }
    }
    @PutMapping
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void put(@RequestBody ArrayList<Mention> dto){
        for(Mention mentionDto: dto){
            Parcour parcour = parcourRepository.findById(mentionDto.getId()).orElseThrow();
            parcour.setParcours(mentionDto.getMention());
            parcour.setAcronymeParcours(mentionDto.getAcronymeMention());

            parcourRepository.save(parcour);
        }
    }
    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('SCOLARITE') or  hasAuthority('DIRECTION') or hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('RESPONSABLE_MENTION') or hasAuthority('ETUDIANT') or hasRole('ADMIN')")
    public Parcour listbyId(@PathVariable("id") Integer id) {
        return parcourRepository.findById(id).orElseThrow();
    }
    @GetMapping()
    @PreAuthorize("hasAuthority('SCOLARITE') or  hasAuthority('DIRECTION') or hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('RESPONSABLE_MENTION') or hasAuthority('ETUDIANT') or hasRole('ADMIN')")
    public List<Parcour> list(){
        return parcourRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteCandidat(@PathVariable("id") Integer id){
        parcourRepository.deleteById(id);
    }
}
