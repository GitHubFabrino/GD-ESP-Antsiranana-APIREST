package com.appli.springjwt.controllers;

import com.appli.springjwt.dto.MoyenneGeneraleDto;
import com.appli.springjwt.dto.ReleveNoteDto;
import com.appli.springjwt.dto.MoyenneEtudiantDto;
import com.appli.springjwt.service.ReleveNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/releve-note")
public class ReleveNoteController {
    @Autowired
    ReleveNoteService releveNoteService;

    @PutMapping("/ueec/{idUEEC}")
    @PreAuthorize("hasAuthority('ENSEIGNANT') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public void postReleveNote(@PathVariable("idUEEC") Integer idUEEC, @RequestBody ArrayList<MoyenneEtudiantDto> moyenneEtudiantDtos){
        System.out.println("methode appelée avec idUEEC = "+ idUEEC);
        releveNoteService.creer(idUEEC, moyenneEtudiantDtos);
    }

    @GetMapping("/ueec/{idUEEC}/dp/{idDp}")
    @PreAuthorize("hasAuthority('ENSEIGNANT') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public ArrayList<MoyenneEtudiantDto> getNote(@PathVariable("idUEEC") Integer idUEEC, @PathVariable("idDp") Integer idDp){
            return releveNoteService.getNote(idUEEC,idDp);
    }

    @GetMapping("/dp/{idDp1}/{idDp2}")
    @PreAuthorize("hasAuthority('ENSEIGNANT') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public ArrayList<MoyenneGeneraleDto> getMoyenneSemestre(@PathVariable("idDp1") Integer idDp1, @PathVariable("idDp2") Integer idDp2){
        return releveNoteService.getNoteDP(idDp1,idDp2);
    }

    @GetMapping("/etudiant/{idEtudiant}/dp/{idDp1}/{idDp2}")
    @PreAuthorize("hasAuthority('ENSEIGNANT') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public ArrayList<MoyenneGeneraleDto> getMoyenneEtudiant(@PathVariable("idEtudiant") Integer idEtudiant,@PathVariable("idDp1") Integer idDp1,@PathVariable("idDp2") Integer idDp2){
        return releveNoteService.getNoteDPEtudiant(idEtudiant,idDp1,idDp2);
    }

    @GetMapping("/etudiant/{idEtudiant}/dp/{idDp1}")
    @PreAuthorize("hasAuthority('ENSEIGNANT') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public ArrayList<ReleveNoteDto> getReleveEtudiant(@PathVariable("idEtudiant") Integer idEtudiant, @PathVariable("idDp1") Integer idDp1){
        return releveNoteService.getReleveEtudiant(idEtudiant,idDp1);
    }

/*
    @GetMapping("/dp/{id1}/{id2}")
    @PreAuthorize("hasAuthority('ENSEIGNANT') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public ArrayList<MoyenneGeneraleDto> getMoyenneGenerale(@PathVariable("id1") Integer id1,@PathVariable("id1") Integer id2){
        return releveNoteService.getMoyenneGenerale(id1,id2);
    }

 */

}
