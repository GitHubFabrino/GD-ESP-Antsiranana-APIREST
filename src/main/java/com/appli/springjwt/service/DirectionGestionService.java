package com.appli.springjwt.service;

import com.appli.springjwt.models.*;
import com.appli.springjwt.repository.*;
import com.appli.springjwt.dto.DirectionGestionDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.appli.springjwt.utils.EmailUtils.getEmailMessage;

@Transactional
@Service
public class DirectionGestionService {

    public static final String LOGIN_ESP_ANTSIRANANA = "LOGIN ESP Antsiranana ";
    @Autowired
    PersonneRepository personneRepository;
    @Autowired
    DirectionRepository directionRepository;
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
    @Autowired
    EnseignantRepository enseignantRepository;


    public void save(DirectionGestionDto directionGestionDto) {

            String password = generateRandomPassword();

            Authentification authentification = new Authentification(directionGestionDto.getEmail(),
                    encoder.encode(password));

            Set<Fonction> fonctions = new HashSet<>();

            Direction direction= new Direction();
            Personne personne = new Personne();

            personne.setNom(directionGestionDto.getNom());
            personne.setPrenoms(directionGestionDto.getPrenoms());
            personne.setEmail(directionGestionDto.getEmail());

            direction.setGestionUtilisateur(directionGestionDto.getGestionUtilisateur());
            direction.setGestionSeminairePedagogiques(directionGestionDto.getGestionSeminairePedagogiques());
            direction.setStatusDirection(directionGestionDto.getStatus());

            Fonction directionFonction = roleRepository.findByName(ERole.DIRECTION)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            fonctions.add(directionFonction);

            authentification.setRoles(fonctions);
            authentification.setIdPersonne(personne);

            //TODO Ici envoie email
            emailService.sendSimpleMessage(
                    directionGestionDto.getEmail(),
                    LOGIN_ESP_ANTSIRANANA,
                    getEmailMessage(
                            directionGestionDto.getNom(),
                            directionGestionDto.getPrenoms(),
                            password ,
                            directionGestionDto.getEmail()));

            direction.setIdPersonne(personne);

            personneRepository.save(personne);

            authentificationRepository.save(authentification);
            directionRepository.save(direction);

    }
    public String generateRandomPassword() {
        String password = RandomStringUtils.randomAlphanumeric(8);
        return password;
    }
    public void update(ArrayList<DirectionGestionDto> dto) {

        for(DirectionGestionDto directionGestionDto: dto){
            Direction direction = directionRepository.findById(directionGestionDto.getId()).orElseThrow();

            direction.setGestionUtilisateur(directionGestionDto.getGestionUtilisateur());
            direction.setGestionSeminairePedagogiques(directionGestionDto.getGestionSeminairePedagogiques());
            direction.setStatusDirection(directionGestionDto.getStatus());
            direction.getIdPersonne().setNom(directionGestionDto.getNom());
            direction.getIdPersonne().setPrenoms(directionGestionDto.getPrenoms());

            directionRepository.save(direction);
        }

    }
    public void delete(Integer id) {
        Direction direction = directionRepository.findById(id).orElseThrow();
        Personne personne= direction.getIdPersonne();
        Fonction fonction = fonctionRepository.findByName(ERole.DIRECTION).orElseThrow();

        Status status = statusRepository.findByIdAuthentificationAndIdFonction(personne.getAuthentification(),fonction).orElseThrow();

        statusRepository.delete(status);
        directionRepository.deleteById(id);

    }
    public ArrayList<DirectionGestionDto> get() {
        List<Direction> listdirection = directionRepository.findAll();
        ArrayList<DirectionGestionDto> directionGestionDtos = new ArrayList<>();

        for(Direction direction: listdirection){
            Integer i = 0;
            directionGestionDtos.add(i,new DirectionGestionDto(
                    direction.getId(),
                    direction.getIdPersonne().getNom(),
                    direction.getIdPersonne().getPrenoms(),
                    direction.getGestionUtilisateur(),
                    direction.getGestionSeminairePedagogiques(),
                    direction.getStatusDirection(),
                    direction.getIdPersonne().getEmail()
            ));
            i+=1;
        }
        return  directionGestionDtos;
    }
    public DirectionGestionDto getById(Integer id) {
        Personne personne = personneRepository.findById(id).orElseThrow();
        Direction getdirection = personne.getDirection();
        DirectionGestionDto directionGestionDto = new DirectionGestionDto();

        directionGestionDto.setId(getdirection.getId());
        directionGestionDto.setGestionUtilisateur(getdirection.getGestionUtilisateur());
        directionGestionDto.setGestionSeminairePedagogiques(getdirection.getGestionSeminairePedagogiques());
        directionGestionDto.setStatus(getdirection.getStatusDirection());
        directionGestionDto.setEmail(getdirection.getIdPersonne().getEmail());

        return directionGestionDto;
    }
    public DirectionGestionDto getDirecteur() {
        Direction directeur = directionRepository.findByStatusDirection("DIRECTEUR").orElseThrow();
        DirectionGestionDto direction = new DirectionGestionDto();
        direction.setNom(directeur.getIdPersonne().getNom());
        direction.setPrenoms(directeur.getIdPersonne().getPrenoms());

        Enseignant enseignant = enseignantRepository.findByIdPersonne(directeur.getIdPersonne()).orElseThrow();
        direction.setStatusEnseignant(enseignant.getStatusEnseignant());
        return  direction;
    }
}
