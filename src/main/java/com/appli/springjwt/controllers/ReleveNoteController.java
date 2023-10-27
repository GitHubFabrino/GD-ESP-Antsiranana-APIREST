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
    @PreAuthorize("hasAuthority('ENSEIGNANT') or hasAuthority('SCOLARITE') or hasAuthority('PRESIDENT_JURY') or hasRole('ADMIN')")
    public void postReleveNote(@PathVariable("idUEEC") Integer idUEEC, @RequestBody ArrayList<MoyenneEtudiantDto> moyenneEtudiantDtos){
        System.out.println("ReleveNoteController : postReleveNote");
        releveNoteService.creer(idUEEC, moyenneEtudiantDtos);
    }
    @PutMapping("/ueec/etudiant/{idUEEC}")
    @PreAuthorize("hasAuthority('ENSEIGNANT') or hasAuthority('SCOLARITE') or hasAuthority('PRESIDENT_JURY') or hasRole('ADMIN')")
    public void postReleveNoteByEtudiant(@PathVariable("idUEEC") Integer idUEEC, @RequestBody MoyenneEtudiantDto moyenneEtudiantDtos){
        System.out.println("ReleveNoteController : postReleveNoteByEtudiant");
        releveNoteService.updateByEtudiant(idUEEC, moyenneEtudiantDtos);
    }

    @GetMapping("/ueec/{idUEEC}/dp/{idDp}")
    @PreAuthorize("hasAuthority('ENSEIGNANT') or hasAuthority('SCOLARITE') or hasAuthority('PRESIDENT_JURY') or hasRole('ADMIN')")
    public ArrayList<MoyenneEtudiantDto> getNote(@PathVariable("idUEEC") Integer idUEEC, @PathVariable("idDp") Integer idDp){
        System.out.println("ReleveNoteController : getNote");
        return releveNoteService.getNote(idUEEC,idDp);
    }

    @GetMapping("/dp/{idDp1}/{idDp2}")
    @PreAuthorize("hasAuthority('ENSEIGNANT') or hasAuthority('SCOLARITE') or hasAuthority('PRESIDENT_JURY') or hasRole('ADMIN')")
    public ArrayList<MoyenneGeneraleDto> getMoyenneSemestre(@PathVariable("idDp1") Integer idDp1, @PathVariable("idDp2") Integer idDp2){
        System.out.println("ReleveNoteController : getMoyenneSemestre");
        return releveNoteService.getNoteDP(idDp1,idDp2);
    }

    @PutMapping("/etudiant/{idEtudiant}/dp/{idDp1}/{idDp2}")
    @PreAuthorize("hasAuthority('ENSEIGNANT') or hasAuthority('SCOLARITE') or hasAuthority('PRESIDENT_JURY') or hasRole('ADMIN')")
    public Byte modifierCodeDeRedoublement(@PathVariable("idEtudiant") Integer idEtudiant, @PathVariable("idDp1") Integer idDp1, @PathVariable("idDp2") Integer idDp2, @RequestBody MoyenneEtudiantDto moyenneEtudiantDtos){
        System.out.println("ReleveNoteController : modifierCodeDeRedoublement");
        releveNoteService.modifierCodeRedoubleme(idEtudiant,idDp1,idDp2,moyenneEtudiantDtos);
        return null;
    }

    @GetMapping("/etudiant/{idEtudiant}/dp/{idDp1}/{idDp2}")
    @PreAuthorize("hasAuthority('ENSEIGNANT') or hasAuthority('SCOLARITE') or hasAuthority('PRESIDENT_JURY') or hasRole('ADMIN')")
    public ArrayList<MoyenneGeneraleDto> getMoyenneEtudiant(@PathVariable("idEtudiant") Integer idEtudiant,@PathVariable("idDp1") Integer idDp1,@PathVariable("idDp2") Integer idDp2){
        System.out.println("ReleveNoteController : getMoyenneEtudiant");
        return releveNoteService.getNoteDPEtudiant(idEtudiant,idDp1,idDp2);
    }

    @GetMapping("/etudiant/{idEtudiant}/dp/{idDp1}")
    @PreAuthorize("hasAuthority('ENSEIGNANT') or hasAuthority('SCOLARITE') or hasAuthority('PRESIDENT_JURY') or hasRole('ADMIN')")
    public ArrayList<ReleveNoteDto> getReleveEtudiant(@PathVariable("idEtudiant") Integer idEtudiant, @PathVariable("idDp1") Integer idDp1){
        System.out.println("ReleveNoteController : getReleveEtudiant");
        return releveNoteService.getReleveEtudiant(idEtudiant,idDp1);
    }
}
