package com.appli.springjwt.service;

import com.appli.springjwt.dto.ScolariteDto;
import com.appli.springjwt.models.*;
import com.appli.springjwt.repository.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ScolariteService {
    @Autowired
    PersonneRepository personneRepository;
    @Autowired
    ScolariteRepository scolariteRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AuthentificationRepository authentificationRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    EmailService emailService;
    @Autowired
    FonctionRepository fonctionRepository;

    public void save(ScolariteDto scolariteDto) {

            String password = generateRandomPassword();

            // Create new user's account
            Authentification authentification = new Authentification(scolariteDto.getEmail(),
                    encoder.encode(password));

            Set<Fonction> fonctions = new HashSet<>();

            Scolarite scolarite= new Scolarite();
            Personne personne = new Personne();

            personne.setNom(scolariteDto.getNom());
            personne.setPrenoms(scolariteDto.getPrenoms());
            personne.setEmail(scolariteDto.getEmail());

            scolarite.setGestionConcoursTci(scolariteDto.getGestionConcoursTCI());
            scolarite.setGestionInscription(scolariteDto.getGestionInscription());
            scolarite.setStatusScolarite(scolariteDto.getStatus());
            scolarite.setGestionAccesTache(scolariteDto.getGestionAccesTache());

            Fonction scolariteFonction = roleRepository.findByName(ERole.SCOLARITE)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            fonctions.add(scolariteFonction);

            authentification.setRoles(fonctions);
            authentification.setIdPersonne(personne);

            emailService.sendSimpleMessage(scolariteDto.getEmail(), "LOGIN ESP Antsiranana  ", " Votre compte a été créé avec le mot de passe suivant." + password);

            scolarite.setIdPersonne(personne);

            personneRepository.save(personne);
            authentificationRepository.save(authentification);

            scolariteRepository.save(scolarite);

    }
    public String generateRandomPassword() {
        String password = RandomStringUtils.randomAlphanumeric(8);
        return password;
    }
    public void update(ArrayList<ScolariteDto> dto) {
        for(ScolariteDto scolariteDto: dto){
            Scolarite scolarite = scolariteRepository.findById(scolariteDto.getId()).orElseThrow();

            scolarite.setGestionConcoursTci(scolariteDto.getGestionConcoursTCI());
            scolarite.setGestionInscription(scolariteDto.getGestionInscription());
            scolarite.setGestionAccesTache(scolariteDto.getGestionAccesTache());
            scolarite.setStatusScolarite(scolariteDto.getStatus());
            scolarite.getIdPersonne().setNom(scolariteDto.getNom());
            scolarite.getIdPersonne().setPrenoms(scolariteDto.getPrenoms());

            scolariteRepository.save(scolarite);
        }
    }
    public ArrayList<ScolariteDto> get() {
        List<Scolarite> listscolarite = scolariteRepository.findAll();
        ArrayList<ScolariteDto> scolariteDtos = new ArrayList<>();

        for(Scolarite scolarite : listscolarite){
            Integer i = 0;
            scolariteDtos.add(i, new ScolariteDto(
                    scolarite.getId(),
                    scolarite.getIdPersonne().getNom(),
                    scolarite.getIdPersonne().getPrenoms(),
                    scolarite.getGestionConcoursTci(),
                    scolarite.getGestionInscription(),
                    scolarite.getGestionAccesTache(),
                    scolarite.getStatusScolarite(),
                    scolarite.getIdPersonne().getEmail()
            ));
            i+=1;
        }
        return scolariteDtos;
    }
    public ScolariteDto getById(Integer id) {
        Personne personne = personneRepository.findById(id).orElseThrow();
        Scolarite scolarite = personne.getScolarite();

        ScolariteDto scolariteDto = new ScolariteDto();

        scolariteDto.setId(scolarite.getId());
        scolariteDto.setNom(scolarite.getIdPersonne().getNom());
        scolariteDto.setPrenoms(scolarite.getIdPersonne().getPrenoms());
        scolariteDto.setGestionConcoursTCI(scolarite.getGestionConcoursTci());
        scolariteDto.setGestionInscription(scolarite.getGestionInscription());
        scolariteDto.setGestionAccesTache(scolarite.getGestionAccesTache());
        scolariteDto.setStatus(scolarite.getStatusScolarite());
        scolariteDto.setEmail(scolarite.getIdPersonne().getEmail());

        return scolariteDto;

    }
    public void delete(Integer id) {
        Scolarite scolarite = scolariteRepository.findById(id).orElseThrow();
        Personne personne= scolarite.getIdPersonne();
        Fonction fonction = fonctionRepository.findByName(ERole.SCOLARITE).orElseThrow();

        Status status = statusRepository.findByIdAuthentificationAndIdFonction(personne.getAuthentification(),fonction).orElseThrow();

        statusRepository.delete(status);
        scolariteRepository.deleteById(id);
    }
    public void updateTache(ArrayList<ScolariteDto> dto) {
        for(ScolariteDto scolariteDto: dto){
            Scolarite scolarite = scolariteRepository.findById(scolariteDto.getId()).orElseThrow();

            scolarite.setGestionConcoursTci(scolariteDto.getGestionConcoursTCI());
            scolarite.setGestionInscription(scolariteDto.getGestionInscription());
            scolarite.setGestionAccesTache(scolariteDto.getGestionAccesTache());

            scolariteRepository.save(scolarite);
        }

    }
}
