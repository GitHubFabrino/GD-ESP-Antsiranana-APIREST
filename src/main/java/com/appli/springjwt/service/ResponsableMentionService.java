package com.appli.springjwt.service;

import com.appli.springjwt.dto.DefinitionMentionDto;
import com.appli.springjwt.models.*;
import com.appli.springjwt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class ResponsableMentionService {
    @Autowired
    EnseignantRepository enseignantRepository;
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    static
    DefinitionmentionRepository definitionmentionRepository;
    @Autowired
    AuthentificationRepository authentificationRepository;
    @Autowired
    FonctionRepository fonctionRepository;
    @Autowired
    AnneeunivRepository anneeunivRepository;

    public void save(DefinitionMentionDto definitionMentionDto) {
        System.out.println(definitionMentionDto.getId());
        System.out.println(definitionMentionDto.getIdEnseignant());

        Definitionmention definitionmention = definitionmentionRepository.findById(definitionMentionDto.getId()).orElseThrow();
        Enseignant enseignant = enseignantRepository.findById(definitionMentionDto.getIdEnseignant()).orElseThrow();

        definitionmention.setIdEnseignant(enseignant);

        try {
        Status status = new Status();
        status.setIdAuthentification(authentificationRepository.findById(enseignant.getIdPersonne().getAuthentification().getId()).orElseThrow());
        status.setIdFonction(fonctionRepository.findByName(ERole.RESPONSABLE_MENTION).orElseThrow());

        statusRepository.save(status);
        } catch (Exception e){}
        definitionmentionRepository.save(definitionmention);
    }

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
                        definitionmention.getIdEnseignant().getId(),
                        definitionmention.getIdEnseignant().getIdPersonne().getNom(),
                        definitionmention.getIdEnseignant().getIdPersonne().getPrenoms()
                ));
            } catch(Exception e){

            }
            i+=1;
            System.out.println(definitionmention.getIdMention().getAcronymeMention());
            System.out.println(definitionmention.getIdMention().getMention());
        }
        // System.out.println(definitionMentionDtos.get(0).getAcronymeMention());
        Collections.reverse(definitionMentionDtos);
        return definitionMentionDtos;

    }

    public void delete(Integer id) {
        Definitionmention definitionmention = definitionmentionRepository.findById(id).orElseThrow();
        Personne personne= definitionmention.getIdEnseignant().getIdPersonne();
        Fonction fonction = fonctionRepository.findByName(ERole.RESPONSABLE_MENTION).orElseThrow();
        definitionmention.setIdEnseignant(null);

        Status status = statusRepository.findByIdAuthentificationAndIdFonction(personne.getAuthentification(),fonction).orElseThrow();

        statusRepository.delete(status);


    }
    @Autowired

    public ResponsableMentionService(DefinitionmentionRepository definitionmentionRepository) {
        this.definitionmentionRepository = definitionmentionRepository;
    }

    public static boolean isid_mention_AlreadyExists(Integer id_mention) {
        // Vérifie si une Anneeuniv avec le même nomAU existe déjà
        return definitionmentionRepository.existsByIdMention(id_mention);
    }
}
