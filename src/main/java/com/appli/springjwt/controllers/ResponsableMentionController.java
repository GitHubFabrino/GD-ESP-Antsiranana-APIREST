package com.appli.springjwt.controllers;


import com.appli.springjwt.dto.DefinitionMentionDto;
import com.appli.springjwt.service.ResponsableMentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/responsable-mention")
public class ResponsableMentionController {
    @Autowired
    ResponsableMentionService responsableMentionService;

    @PostMapping
    @PreAuthorize("hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void post(@RequestBody DefinitionMentionDto definitionMentionDto){
        Integer id_mention = definitionMentionDto.getIdMention();
        if (!ResponsableMentionService.isid_mention_AlreadyExists(id_mention)){
            responsableMentionService.save(definitionMentionDto);
        }else{
            throw new ResponseStatusException(HttpStatus.CONFLICT, "existe déjà");
        }

    }
/*
    @PutMapping
    @PreAuthorize("hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void put(@RequestBody ArrayList<DefinitionMentionDto> definitionMentionDto){
        responsableMentionService.update(definitionMentionDto);
    }

 */
    @GetMapping("/annee/{id}")
    @PreAuthorize("hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public ArrayList<DefinitionMentionDto> list(@PathVariable("id") Integer idAU) {
        return responsableMentionService.get(idAU);
    }

/*
    @GetMapping("/personne/{id}")
    @PreAuthorize("hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public DirectionGestionDto getById(@PathVariable("id") Integer id) {
        return responsableMentionService.getById(id);
    }

 */
@DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void deleteCandidat(@PathVariable("id") Integer numero){
        responsableMentionService.delete(numero);
    }

}
