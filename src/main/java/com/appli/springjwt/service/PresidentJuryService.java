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
        Jyry.setId_CTCI(concour.getId());
        Jyry.setIdAu(au);

        try {
            Status status = new Status();
            status.setIdAuthentification(authentificationRepository.findById(enseignant.getIdPersonne().getAuthentification().getId()).orElseThrow());
            status.setIdFonction(fonctionRepository.findByName(ERole.PRESIDENT_JURY).orElseThrow());

            statusRepository.save(status);
        } catch (Exception e){}

        this.presidentJuryRepository.save(Jyry);

    }

    public ArrayList<PresidentJuryDto> getList() {
        List<PresidentJuryModel> listPDJ = presidentJuryRepository.findAll();
        ArrayList<PresidentJuryDto> PresidentJuryDTOS = new ArrayList<>();


        for (PresidentJuryModel pdj: listPDJ){
            Integer i = 0;
            try{
                Optional<Concourstci> concour = concourstciRepository.findById(pdj.getId_CTCI());
                PresidentJuryDTOS.add(i, new PresidentJuryDto(
                        pdj.getId(),
                        pdj.getId_CTCI(),
                        concour.get().getSessionCTCI(),
                        pdj.getIdEnseignant().getId(),
                        pdj.getIdEnseignant().getIdPersonne().getNom(),
                        pdj.getIdEnseignant().getIdPersonne().getPrenoms()

                ));
            } catch (Exception e){}
            i+=1;
        }
        return PresidentJuryDTOS;
    }

    public Object delete(Integer id) {
        PresidentJuryModel presidentJury = presidentJuryRepository.findById(id).orElseThrow();
        Personne personne= presidentJury.getIdEnseignant().getIdPersonne();
        Enseignant personneIdEnseignant = presidentJury.getIdEnseignant();
        presidentJuryRepository.delete(presidentJury);

        Fonction fonction = fonctionRepository.findByName(ERole.PRESIDENT_JURY).orElseThrow();


        List<PresidentJuryModel> pdjExist = this.presidentJuryRepository.findByIdEnseignant(personneIdEnseignant);
        if (pdjExist.size() == 0){
            Status status = statusRepository.findByIdAuthentificationAndIdFonction(personne.getAuthentification(),fonction).orElseThrow();
            statusRepository.delete(status);
        }
        return null;
    }
}
