package com.appli.springjwt.controllers;

import com.appli.springjwt.service.ProgrammeService;
import com.appli.springjwt.dto.ProgrammeEnseignementDto;
import com.appli.springjwt.dto.ProgrammeGetDto;
import com.appli.springjwt.repository.ElementconstitutifRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/programme-enseignement")
public class ProgrammeController {
    @Autowired
    ProgrammeService programmeService;
    @Autowired
    private ElementconstitutifRepository elementconstitutifRepository;

    @PostMapping("/dp/{id}")
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('RESPONSABLE_MENTION') or hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void post(@PathVariable("id") Integer id, @RequestBody ProgrammeEnseignementDto programmeDtos){
        programmeService.save(programmeDtos, id);
    }

    @GetMapping("/dp/{id}")
    @PreAuthorize("hasAuthority('DIRECTION') or hasAuthority('ENSEIGNANT') or hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('RESPONSABLE_MENTION') or hasRole('ADMIN')")
    public List<ProgrammeGetDto> list(@PathVariable("id") Integer id) {
        return programmeService.getByIdDp(id);
    }
/*
    @PutMapping
    @PreAuthorize("hasAuthority('DIRECTION') or hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('RESPONSABLE_MENTION') or hasRole('ADMIN')")
    public void modifier(@RequestBody ArrayList<ProgrammeDto> programmeDtos){
        programmeService.update(programmeDtos);
    }

 */
    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('RESPONSABLE_MENTION') or hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void supprimer(@PathVariable("id") Integer id){
        programmeService.delete(id);
    }

    @DeleteMapping("/code-ec/{code}")
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('RESPONSABLE_MENTION') or hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void supprimerByCodeEc(@PathVariable("code") String id){
        programmeService.deleteByCodeEc(id);
    }

}
