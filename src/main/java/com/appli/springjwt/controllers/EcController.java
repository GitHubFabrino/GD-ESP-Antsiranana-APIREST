package com.appli.springjwt.controllers;

import com.appli.springjwt.dto.ElementConstitutifDto;
import com.appli.springjwt.models.Elementconstitutif;
import com.appli.springjwt.repository.ElementconstitutifRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/element-constitutif")
public class EcController {
    @Autowired
    ElementconstitutifRepository elementconstitutifRepository;

    @PostMapping
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('RESPONSABLE_MENTION') or hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void post(@RequestBody ArrayList<ElementConstitutifDto> elementConstitutifDtos){

        System.out.println("EcController : post");
        for (ElementConstitutifDto ec: elementConstitutifDtos){

            Elementconstitutif elementconstitutif = new Elementconstitutif();
            elementconstitutif.setNomEc(ec.getNomEC());
            elementconstitutif.setCodeEc(ec.getCodeEC());

            elementconstitutifRepository.save(elementconstitutif);
        }
    }

    @GetMapping
    @PreAuthorize("hasAuthority('DIRECTION') or hasAuthority('ENSEIGNANT') or hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('RESPONSABLE_MENTION') or hasRole('ADMIN')")
    public ArrayList<ElementConstitutifDto> listEC() {
        System.out.println("EcController : listEC");
        List<Elementconstitutif> elementconstitutifs = elementconstitutifRepository.findAll();
        ArrayList<ElementConstitutifDto> elementConstitutifDtos = new ArrayList<>();

        for(Elementconstitutif ec: elementconstitutifs){
            Integer i = 0;
            elementConstitutifDtos.add(i,new ElementConstitutifDto(
                ec.getId(),
                ec.getCodeEc(),
                    ec.getNomEc()
            ));
        }
        Collections.reverse(elementConstitutifDtos);
        return elementConstitutifDtos;
    }

    @PutMapping
    @PreAuthorize("hasAuthority('DIRECTION') or hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('RESPONSABLE_MENTION') or hasRole('ADMIN')")
    public void modifier(@RequestBody ArrayList<ElementConstitutifDto> ElementConstitutifDto){
        System.out.println("EcController : modifier");
        for(ElementConstitutifDto dto: ElementConstitutifDto){
            Elementconstitutif elementconstitutif = elementconstitutifRepository.findById(dto.getId()).orElseThrow();

            elementconstitutif.setNomEc(dto.getNomEC());
            elementconstitutif.setCodeEc(dto.getCodeEC());
            elementconstitutifRepository.save(elementconstitutif);
        }
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('RESPONSABLE_MENTION') or hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void supprimer(@PathVariable("id") Integer id){
        System.out.println("EcController : supprimer");
        Elementconstitutif elementconstitutif = elementconstitutifRepository.findById(id).orElseThrow();
        elementconstitutifRepository.delete(elementconstitutif);
    }

}
