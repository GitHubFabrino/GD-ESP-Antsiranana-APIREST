package com.appli.springjwt.controllers;


import com.appli.springjwt.models.Mention;
import com.appli.springjwt.repository.MentionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/mention")
public class MentionController {
    @Autowired
    MentionRepository mentionRepository;

    @PostMapping
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void post(@RequestBody ArrayList<Mention> mentiondto){
        System.out.println("MentionController : post");
        for(Mention mention: mentiondto){
            mentionRepository.save(mention);
        }
    }
    @PutMapping
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void put(@RequestBody ArrayList<Mention> dto){
        System.out.println("MentionController : put");
        for(Mention mentionDto: dto){
            Mention mention = mentionRepository.findById(mentionDto.getId()).orElseThrow();
            mention.setMention(mentionDto.getMention());
            mention.setAcronymeMention(mentionDto.getAcronymeMention());
            mentionRepository.save(mention);
        }

    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('DIRECTION') or hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('RESPONSABLE_MENTION') or hasAuthority('ETUDIANT') or hasRole('ADMIN')")
    public Mention listbyId(@PathVariable("id") Integer id) {
        System.out.println(" MentionController : listbyId");
        return mentionRepository.findById(id).orElseThrow();
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('SCOLARITE') or  hasAuthority('DIRECTION') or hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('RESPONSABLE_MENTION') or hasAuthority('ETUDIANT') or hasRole('ADMIN')")
    public List<Mention> list(){
        System.out.println(" MentionController : list");
        return mentionRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteCandidat(@PathVariable("id") Integer id){
        System.out.println(" MentionController : deleteCandidat");
        mentionRepository.deleteById(id);
    }
}
