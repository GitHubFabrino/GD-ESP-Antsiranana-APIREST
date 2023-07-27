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

      System.out.println("La méthode postConcours");
        System.out.println("Données reçues :");
        System.out.println("ConcoursTCI : " + concours.getConcoursTCI());
        System.out.println("CentreConcoursTCI : " + concours.getCentreConcoursTCI());
        System.out.println("MatiereConcoursTCI : " + concours.getMatiereConcoursTCI());
        System.out.println("CalendrierConcoursTCI : " + concours.getCalendrierConcoursTCI());
        System.out.println("ato enregistrena");
        concourstciService.creerConcours(concours);
        //System.out.println("-------------------------------------------------");
        //System.out.println("La methode postConcours concours : " + concours);
    }
    @GetMapping
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public List<ConcourstciView> listConcours() {
        List<ConcourstciView> concoursList = concourstciService.getConcoursList();
        System.out.println("---------------------------------------------------------");
        System.out.println("methode get listConcours Concours List : " );
        // Parcourez la liste pour afficher les détails de chaque concours
        for (ConcourstciView concours : concoursList) {
            Integer id  = concours.getId();
            String session = concours.getSessionCTCI();
            System.out.println("Concours id=" + id + ", Session=" + session );
        }
        System.out.println("---------------------------------------------------------");
        return concourstciService.getConcoursList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public ConcourstciInfo get(@PathVariable("id") Integer numero){
        System.out.println("---------------------------------------------------------");
        System.out.println(" id : " + numero);
        System.out.println("La methode get par id : "+ numero+" ConcourstciInfo : " + concourstciService.getConcoursById(numero));
        return concourstciService.getConcoursById(numero);
    }

   /* @GetMapping("/{id}/centre")
   // @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public ArrayList<CentreConcoursTCIDto> getCentreConcours(@PathVariable("id") Integer numero){
        System.out.println("---------------------------------------------------------");
        System.out.println("La methode get par id : "+numero+"  getCentreConcours");
        List<CentreConcoursTCIDto> centreConcoursTCIDtoList = centreConcourstciService.getCentreConcoursList(numero);
        for (CentreConcoursTCIDto centreConcours : centreConcoursTCIDtoList){
            Integer id_centreCTCI = centreConcours.getId_centreCTCI();
            String nomCentreCTCI = centreConcours.getNomCentreCTCI();
            Integer codePostale = centreConcours.getCodePostale();
            String nom = centreConcours.getNom();
            String prenoms = centreConcours.getPrenoms();
            String telephone = centreConcours.getTelephone();
            Personne idPersonne= centreConcours.getIdPersonne();
            Concourstci idCTCI = centreConcours.getIdCTCI();
            System.out.println("=================================================================================");
            System.out.println( id_centreCTCI + " " + nomCentreCTCI + " " + codePostale + " " + nom + " " + prenoms + " " +telephone + " " +idPersonne + " " +idCTCI );
        }
         return (ArrayList<CentreConcoursTCIDto>) centreConcourstciService.getCentreConcoursList(numero);
    }*/
    /*
   @GetMapping("/{id}/centre")
   // @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
   public ResponseEntity<List<CentreConcoursTCIDto>> getCentreConcours(@PathVariable("id") Integer numero){
       List<CentreConcoursTCIDto> centreConcoursTCIDtoList = centreConcourstciService.getCentreConcoursList(numero);
       System.out.println("---------------------------------------------------------");
       System.out.println("La methode get par id : "+numero+"  getCentreConcours");
      // List<CentreConcoursTCIDto> centreConcoursTCIDtoList = centreConcourstciService.getCentreConcoursList(numero);
       for (CentreConcoursTCIDto centreConcours : centreConcoursTCIDtoList){
           Integer id_centreCTCI = centreConcours.getId_centreCTCI();
           String nomCentreCTCI = centreConcours.getNomCentreCTCI();
           Integer codePostale = centreConcours.getCodePostale();
           String nom = centreConcours.getNom();
           String prenoms = centreConcours.getPrenoms();
           String telephone = centreConcours.getTelephone();
           Personne idPersonne= centreConcours.getIdPersonne();
           Concourstci idCTCI = centreConcours.getIdCTCI();
           System.out.println("=================================================================================");
           System.out.println( id_centreCTCI + " " + nomCentreCTCI + " " + codePostale + " " + nom + " " + prenoms + " " +telephone + " " +idPersonne + " " +idCTCI );
       }
       //return (ArrayList<CentreConcoursTCIDto>) centreConcourstciService.getCentreConcoursList(numero);
   return ResponseEntity.ok(centreConcoursTCIDtoList);
   }*/
   @GetMapping("/{id}/centre")
   @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
   public ArrayList<CentreConcoursTCIDto> getCentreConcours(@PathVariable("id") Integer numero){
       System.out.println("---------------------------------------------------------");
       System.out.println(" id : " + numero);
       List<CentreConcoursTCIDto> centreConcoursTCIDtoList = centreConcourstciService.getCentreConcoursList(numero);
       for (CentreConcoursTCIDto centreConcours : centreConcoursTCIDtoList){
           Integer id_centreCTCI = centreConcours.getId_centreCTCI();
           String nomCentreCTCI = centreConcours.getNomCentreCTCI();
           Integer codePostale = centreConcours.getCodePostale();
           String nom = centreConcours.getNom();
           String prenoms = centreConcours.getPrenoms();
           String telephone = centreConcours.getTelephone();
           Personne idPersonne= centreConcours.getIdPersonne();
           Concourstci idCTCI = centreConcours.getIdCTCI();
       }
       System.out.println("La methode get par id : "+numero+"  getCentreConcours");
       System.out.println("/////////////"+centreConcourstciService.getCentreConcoursList(numero));
       return (ArrayList<CentreConcoursTCIDto>) centreConcourstciService.getCentreConcoursList(numero);

   }

    @DeleteMapping("/{id}/centre/{idCentre}")
    public void deleteCentreConcours(@PathVariable("id") Integer id,@PathVariable("idCentre") Integer idCentre){
        System.out.println("---------------------------------------------------------");
        System.out.println("methode delete de idCTCI : " + id +"et idCentre :" + idCentre+" deleteCentreConcours");
        centreConcourstciService.deleteCentreConcours(id, idCentre);
    }

    @GetMapping("/{id}/matiere")
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public ArrayList<MatiereDto> getMatiereConcours(@PathVariable("id") Integer numero){
        System.out.println("---------------------------------------------------------");
        System.out.println("La methode get avec id : " + numero + "getMatiereConcours" + matiereService.getMatiereConcoursList(numero));
        return matiereService.getMatiereConcoursList(numero);
    }

    @DeleteMapping("/{id}/matiere/{idMatiere}")
    public void deleteMatiereConcours(@PathVariable("id") Integer id, @PathVariable("idMatiere") Integer idMatiere){
        System.out.println("---------------------------------------------------------");
        System.out.println("methode delete avec id : "+ id + " et " + idMatiere + " deleteMatiereConcours");
        matiereService.deleteMatiereConcours(id,idMatiere);
    }

    @GetMapping("/{id}/calendrier")
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public ArrayList<CalendrierConcoursTCIDto> getCalendrierConcours(@PathVariable("id") Integer numero){
        System.out.println("-------------------------------------------------");
        System.out.println("La methode get avec id : " + numero + " getCalendrierConcours");
        return calendrierService.getCalendrierConcoursList(numero);
    }

    @GetMapping("/{id}/{idMatiere}/calendrier")
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public ArrayList<CalendrierService> getCalendrierTest(@PathVariable("id") Integer numero, @PathVariable("idMatiere") Integer[] idMatiere){
        System.out.println(idMatiere + "methode getCalendrierTest");
        return null;
    }
}
