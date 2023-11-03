package com.appli.springjwt.service;

import com.appli.springjwt.dto.DefinitionMentionDto;
import com.appli.springjwt.repository.AnneeunivRepository;
import com.appli.springjwt.models.Definitionmention;
import com.appli.springjwt.repository.DefinitionmentionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class DefinitionMentionService {
    @Autowired
    DefinitionmentionRepository definitionmentionRepository;
    @Autowired
    AnneeunivRepository anneeunivRepository;

    public ArrayList<DefinitionMentionDto> get(Integer idAU) {
        ArrayList<Definitionmention> ObjDefinition = definitionmentionRepository.findAllByIdAu(anneeunivRepository.findById(idAU).orElseThrow());

        ArrayList<DefinitionMentionDto> definitionMentionDtos = new ArrayList<>();

        for(Definitionmention definitionmention :ObjDefinition){
            Integer i = 0;
            try {
                definitionMentionDtos.add(i, new DefinitionMentionDto(
                        definitionmention.getId(),
                        definitionmention.getIdAu().getId(),
                        definitionmention.getIdMention().getId(),
                        definitionmention.getIdMention().getMention(),
                        definitionmention.getIdMention().getAcronymeMention(),
                        definitionmention.getIdEnseignant().getId()
                ));
            } catch(Exception e){
                definitionMentionDtos.add(i, new DefinitionMentionDto(
                        definitionmention.getId(),
                        definitionmention.getIdAu().getId(),
                        definitionmention.getIdMention().getId(),
                        definitionmention.getIdMention().getMention(),
                        definitionmention.getIdMention().getAcronymeMention(),
                        null
                ));
            }
            i+=1;
        }
        Collections.reverse(definitionMentionDtos);
        return definitionMentionDtos;
    }

}
