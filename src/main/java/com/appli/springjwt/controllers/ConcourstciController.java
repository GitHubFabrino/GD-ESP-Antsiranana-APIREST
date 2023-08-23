package com.appli.springjwt.controllers;

import com.appli.springjwt.dto.*;
import com.appli.springjwt.models.Concourstci;
import com.appli.springjwt.models.Personne;
import com.appli.springjwt.service.CalendrierService;
import com.appli.springjwt.service.CentreConcourstciService;
import com.appli.springjwt.service.ConcourstciService;
import com.appli.springjwt.service.MatiereService;
import com.appli.springjwt.view.ConcourstciInfo;
import com.appli.springjwt.view.ConcourstciView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/concours")

public class ConcourstciController {
    @Autowired
    ConcourstciService concourstciService;
    @Autowired
    CentreConcourstciService centreConcourstciService;
    @Autowired
    MatiereService matiereService;
    @Autowired
    CalendrierService calendrierService;

    @PutMapping
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public void postConcours(@RequestBody ConcoursDto concours){
        System.out.println("ConcourstciController : postConcours");
        concourstciService.creerConcours(concours);
    }
    @GetMapping
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public List<ConcourstciView> listConcours() {
        System.out.println("ConcourstciController : listConcours");
        return concourstciService.getConcoursList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public ConcourstciInfo get(@PathVariable("id") Integer numero){
        System.out.println("ConcourstciController : get");
        return concourstciService.getConcoursById(numero);
    }

   @GetMapping("/{id}/centre")
   @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
   public ArrayList<CentreConcoursTCIDto> getCentreConcours(@PathVariable("id") Integer numero){
       System.out.println("ConcourstciController : getCentreConcours");
       return (ArrayList<CentreConcoursTCIDto>) centreConcourstciService.getCentreConcoursList(numero);

   }

    @DeleteMapping("/{id}/centre/{idCentre}")
    public void deleteCentreConcours(@PathVariable("id") Integer id,@PathVariable("idCentre") Integer idCentre){
        System.out.println("ConcourstciController : deleteCentreConcours");
        centreConcourstciService.deleteCentreConcours(id, idCentre);
    }

    @GetMapping("/{id}/matiere")
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public ArrayList<MatiereDto> getMatiereConcours(@PathVariable("id") Integer numero){
        System.out.println("ConcourstciController : deleteCentreConcours");
        return matiereService.getMatiereConcoursList(numero);
    }

    @DeleteMapping("/{id}/matiere/{idMatiere}")
    public void deleteMatiereConcours(@PathVariable("id") Integer id, @PathVariable("idMatiere") Integer idMatiere){
        System.out.println("ConcourstciController : deleteMatiereConcours");
        matiereService.deleteMatiereConcours(id,idMatiere);
    }

    @GetMapping("/{id}/calendrier")
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public ArrayList<CalendrierConcoursTCIDto> getCalendrierConcours(@PathVariable("id") Integer numero){
        System.out.println("ConcourstciController : getCalendrierConcours");
        return calendrierService.getCalendrierConcoursList(numero);
    }

    @GetMapping("/{id}/{idMatiere}/calendrier")
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public ArrayList<CalendrierService> getCalendrierTest(@PathVariable("id") Integer numero, @PathVariable("idMatiere") Integer[] idMatiere){
        System.out.println("ConcourstciController : getCalendrierTest");
        return null;
    }
}
