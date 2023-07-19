package com.appli.springjwt.controllers;

import com.appli.springjwt.dto.CalendrierConcoursTCIDto;
import com.appli.springjwt.dto.CentreConcoursTCIDto;
import com.appli.springjwt.dto.ConcoursDto;
import com.appli.springjwt.dto.MatiereDto;
import com.appli.springjwt.models.Authentification;
import com.appli.springjwt.service.CalendrierService;
import com.appli.springjwt.service.CentreConcourstciService;
import com.appli.springjwt.service.ConcourstciService;
import com.appli.springjwt.service.MatiereService;
import com.appli.springjwt.view.ConcourstciInfo;
import com.appli.springjwt.view.ConcourstciView;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Authentification> postConcours(@RequestBody ConcoursDto concours){
        /*concourstciService.creerPersonne(personne);
        return authentificationRepository.findAll();
         */
        concourstciService.creerConcours(concours);
        return null;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public List<ConcourstciView> listConcours() {
        return concourstciService.getConcoursList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public ConcourstciInfo get(@PathVariable("id") Integer numero){
        System.out.println("La methode get a été invoqué");

        return concourstciService.getConcoursById(numero);
    }

    @GetMapping("/{id}/centre")
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public ArrayList<CentreConcoursTCIDto> getCentreConcours(@PathVariable("id") Integer numero){
        System.out.println("La methode get a été invoqué");
         return centreConcourstciService.getCentreConcoursList(numero);

        //return null;
    }

    @DeleteMapping("/{id}/centre/{idCentre}")
    public void deleteCentreConcours(@PathVariable("id") Integer id,@PathVariable("idCentre") Integer idCentre){
        centreConcourstciService.deleteCentreConcours(id, idCentre);

    }

    @GetMapping("/{id}/matiere")
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public ArrayList<MatiereDto> getMatiereConcours(@PathVariable("id") Integer numero){
        System.out.println("La methode get a été invoqué");
        return matiereService.getMatiereConcoursList(numero);

        //return null;
    }

    @DeleteMapping("/{id}/matiere/{idMatiere}")
    public void deleteMatiereConcours(@PathVariable("id") Integer id, @PathVariable("idMatiere") Integer idMatiere){
        matiereService.deleteMatiereConcours(id,idMatiere);
    }


    @GetMapping("/{id}/calendrier")
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public ArrayList<CalendrierConcoursTCIDto> getCalendrierConcours(@PathVariable("id") Integer numero){
        System.out.println("La methode get a été invoqué");

        return calendrierService.getCalendrierConcoursList(numero);


    }

    @GetMapping("/{id}/{idMatiere}/calendrier")
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public ArrayList<CalendrierService> getCalendrierTest(@PathVariable("id") Integer numero, @PathVariable("idMatiere") Integer[] idMatiere){
        System.out.println(idMatiere);

        return null;
    }


    /*
    @PutMapping("/{id}")
    public ResponseEntity<PersonneDto> putPersonne(@PathVariable("id") Integer numero, @RequestBody PersonneDto personne){

        concourstciService.modifierPersonne(numero,personne);
        return ResponseEntity.ok(concourstciService.getPersonneById(numero));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public PersonneDto get(@PathVariable("id") Integer numero){
        System.out.println("La methode get a été invoqué");

        return concourstciService.getPersonneById(numero);
    }

    @DeleteMapping("/{id}")
    public void deletePersonne(@PathVariable("id") Integer numero){
        concourstciService.deletePersonne(numero);
    }

     */
}
