package com.appli.springjwt.service;

import com.appli.springjwt.dto.DefinitionPresidentJuryDto;
import com.appli.springjwt.dto.PresidentJuryDto;
import com.appli.springjwt.models.*;
import com.appli.springjwt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PresidentJuryService {

    @Autowired
    PresidentJuryRepository presidentJuryRepository;

    @Autowired
    EnseignantRepository enseignantRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    AuthentificationRepository authentificationRepository;
    @Autowired
    FonctionRepository fonctionRepository;

    @Autowired
    AnneeunivRepository anneeunivRepository;

    @Autowired
    ConcourstciRepository concourstciRepository;

/*
    public static boolean isid_presidentJury_AlreadyExists(int id_concours) {

        return presidentJuryRepository.existsByIdCTCI(id_concours);
    }*/

    public PresidentJuryModel findOne(int idConcour) {
        Optional<PresidentJuryModel> concourExist = presidentJuryRepository.findById(idConcour);
        if (concourExist.isPresent()){
            return  concourExist.get();
        }
        return null;
    }

    public void save(DefinitionPresidentJuryDto presidentJury) {


        Enseignant enseignant = enseignantRepository.findById(presidentJury.getIdEnseignant()).orElseThrow();
        Anneeuniv au = anneeunivRepository.findById(presidentJury.getIdAU()).orElseThrow();
        Concourstci concour = concourstciRepository.findById(presidentJury.getIdConcour()).orElseThrow();
        PresidentJuryModel Jyry = new PresidentJuryModel();
        Jyry.setIdEnseignant(enseignant);
        Jyry.setId_CTCI(concour);
        Jyry.setIdAu(au);

        System.out.println("avant");
        System.out.println(Jyry.getId_CTCI());
        System.out.println(Jyry.getIdEnseignant());
        System.out.println(Jyry.getIdAu());

        try {
            Status status = new Status();
            status.setIdAuthentification(authentificationRepository.findById(enseignant.getIdPersonne().getAuthentification().getId()).orElseThrow());
            status.setIdFonction(fonctionRepository.findByName(ERole.PRESIDENT_JURY).orElseThrow());

            statusRepository.save(status);
        } catch (Exception e){}

        this.presidentJuryRepository.save(Jyry);
/*

        presidentJury.setIdEnseignant(presidentJury.getIdEnseignant());
       /* presidentJury.setIdAu(presidentJury.getIdAu());
        presidentJury.setId_CTCI(presidentJury.getId_CTCI());


        System.out.println(presidentJury.getId_CTCI());
        System.out.println(presidentJury.getIdEnseignant());
        System.out.println(presidentJury.getIdAu());*/
/*
        try {
            Status status = new Status();
            status.setIdAuthentification(authentificationRepository.findById(enseignant.getIdPersonne().getAuthentification().getId()).orElseThrow());
            status.setIdFonction(fonctionRepository.findByName(ERole.PRESIDENT_JURY).orElseThrow());

            statusRepository.save(status);
        } catch (Exception e){}
        this.presidentJuryRepository.save(presidentJury);*/
    }

    public ArrayList<PresidentJuryDto> getList() {
        List<PresidentJuryModel> listPDJ = presidentJuryRepository.findAll();
        ArrayList<PresidentJuryDto> PresidentJuryDTOS = new ArrayList<>();


        for (PresidentJuryModel pdj: listPDJ){
            Integer i = 0;
            try{

                PresidentJuryDTOS.add(i, new PresidentJuryDto(
                        pdj.getId(),
                        pdj.getId_CTCI().getId(),
                        pdj.getId_CTCI().getSessionCTCI(),
                        pdj.getIdEnseignant().getId(),
                        pdj.getIdEnseignant().getIdPersonne().getNom(),
                        pdj.getIdEnseignant().getIdPersonne().getPrenoms()


                ));
            } catch (Exception e){}
            i+=1;
        }
        return PresidentJuryDTOS;
    }

    public void delete(Integer id) {
        PresidentJuryModel presidentJury = presidentJuryRepository.findById(id).orElseThrow();
        Personne personne= presidentJury.getIdEnseignant().getIdPersonne();
        Fonction fonction = fonctionRepository.findByName(ERole.PRESIDENT_JURY).orElseThrow();
       /* presidentJury.setIdEnseignant(null);*/

        Status status = statusRepository.findByIdAuthentificationAndIdFonction(personne.getAuthentification(),fonction).orElseThrow();

        statusRepository.delete(status);
        presidentJuryRepository.delete(presidentJury);

    }
}
