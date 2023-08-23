package com.appli.springjwt.controllers;

import com.appli.springjwt.dto.DefinitionMentionDto;
import com.appli.springjwt.models.Mention;
import com.appli.springjwt.repository.AnneeunivRepository;
import com.appli.springjwt.repository.MentionRepository;
import com.appli.springjwt.service.DefinitionMentionService;
import com.appli.springjwt.models.Definitionmention;
import com.appli.springjwt.repository.DefinitionmentionRepository;
import com.appli.springjwt.repository.EnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/definition-mention")
public class DefinitionMentionController {
    @Autowired
    MentionRepository mentionRepository;
    @Autowired
    DefinitionmentionRepository definitionmentionRepository;
    @Autowired
    AnneeunivRepository anneeunivRepository;
    @Autowired
    DefinitionMentionService definitionMentionService;
    @Autowired
    private EnseignantRepository enseignantRepository;

    @PostMapping("/annee/{id}")
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void post(@PathVariable("id") Integer idAU, @RequestBody ArrayList<DefinitionMentionDto> mentiondto){
        System.out.println("DefinitionMentionController : post");
        for(DefinitionMentionDto definitionMentionDto: mentiondto){
            Definitionmention definitionmention = new Definitionmention();
            Mention mention = new Mention();

            if(mentionRepository.existsByMention(definitionMentionDto.getMention())){
                mention = mentionRepository.findByMention(definitionMentionDto.getMention()).orElseThrow();
            }else {
                mention.setMention(definitionMentionDto.getMention());
                mention.setAcronymeMention(definitionMentionDto.getAcronymeMention());
            }
            definitionmention.setIdMention(mention);
            //definitionmention.setIdAU(anneeunivRepository.findById(definitionMentionDto.getIdAU()).orElseThrow());
            definitionmention.setIdAu(anneeunivRepository.findById(idAU).orElseThrow());
            try {
                definitionmention.setIdEnseignant(enseignantRepository.findById(definitionMentionDto.getIdEnseignant()).orElseThrow());
            }catch (Exception e) {
                definitionmention.setIdEnseignant(null);
            }
            mentionRepository.save(mention);
            definitionmentionRepository.save(definitionmention);
        }
    }

    @PutMapping
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public void put(@RequestBody ArrayList<DefinitionMentionDto> dto){
        System.out.println("DefinitionMentionController : put");
        for(DefinitionMentionDto mentionDto: dto){
            Definitionmention mention = definitionmentionRepository.findById(mentionDto.getId()).orElseThrow();

            mention.getIdMention().setMention(mentionDto.getMention());
            mention.getIdMention().setAcronymeMention(mentionDto.getAcronymeMention());

            definitionmentionRepository.save(mention);
        }
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('DIRECTION') or hasRole('ADMIN')")
    public Definitionmention listbyId(@PathVariable("id") Integer id) {
        System.out.println("DefinitionMentionController : listbyId");
        return definitionmentionRepository.findById(id).orElseThrow();
    }

    @GetMapping("/annee/{id}")
    @PreAuthorize("hasAuthority('SCOLARITE') or hasAuthority('DIRECTION') or hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('RESPONSABLE_MENTION') or hasAuthority('ETUDIANT') or hasRole('ADMIN')")
    public ArrayList<DefinitionMentionDto> list(@PathVariable("id") Integer idAU){
        System.out.println("DefinitionMentionController : list");
        return definitionMentionService.get(idAU);
    }

    @DeleteMapping("/{id}")
    public void deleteCandidat(@PathVariable("id") Integer id){
        System.out.println("DefinitionMentionController : deleteCandidat");
        definitionmentionRepository.deleteById(id);
    }
}
