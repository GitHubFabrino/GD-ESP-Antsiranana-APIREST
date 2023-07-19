package com.appli.springjwt.service;

import com.appli.springjwt.models.*;
import com.appli.springjwt.repository.*;
import com.appli.springjwt.dto.EnseignantDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EnseignantService {
    @Autowired
    PersonneRepository personneRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AuthentificationRepository authentificationRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    StatusRepository statusRepository;
    /*@Autowired
    EmailService emailService;

     */
    @Autowired
    FonctionRepository fonctionRepository;
    @Autowired
    private EnseignantRepository enseignantRepository;
@Transactional
    public void save(EnseignantDto enseignantDto) {
        // à deployer
        //String password = generateRandomPassword();
        String password = "enseignantesp";

        String matricule;
        DecimalFormat nf = new DecimalFormat("000");

        if(enseignantRepository.count()<1){
            matricule = "1";
        }else{
            // matricule = etudiantRepository.findById(etudiantRepository.count()).orElseThrow().getNumeroMatricule();
            //matricule = String.valueOf(enseignantRepository.findById(intValue(enseignantRepository.count())).orElseThrow().getId()+1);

            matricule = String.valueOf(enseignantRepository.findTopByOrderByIdDesc().orElseThrow().getId()+1);
        }
        ;
        // Create new user's account
        // à deployer
        /*
        Authentification authentification = new Authentification(enseignantDto.getEmail(),
                encoder.encode(password));

         */
        String pseudo = enseignantDto.getNom().toLowerCase() +"."+ enseignantDto.getPrenoms().toLowerCase().split(" ")[0];
        Authentification authentification = new Authentification(
                enseignantDto.getEmail(),
                encoder.encode(password));

        Set<Fonction> fonctions = new HashSet<>();
        Enseignant enseignant= new Enseignant();
        Personne personne = null;

        if(personneRepository.existsByNomAndPrenoms(enseignantDto.getNom(),enseignantDto.getPrenoms())){
            personne = personneRepository.findByNomAndPrenoms(enseignantDto.getNom(),enseignantDto.getPrenoms()).orElseThrow();
            personne.setEmail(enseignantDto.getEmail());
        }else{
            personne = new Personne();

            personne.setNom(enseignantDto.getNom());
            personne.setPrenoms(enseignantDto.getPrenoms());
            personne.setEmail(enseignantDto.getEmail());
        }

        enseignant.setNumeroMatricule(matricule);
        enseignant.setGradeEnseignant(enseignantDto.getGrade());
        enseignant.setSpecialite(enseignantDto.getSpecialite());
        enseignant.setStatusEnseignant(enseignantDto.getStatus());

        Fonction enseignantFonction = roleRepository.findByName(ERole.ENSEIGNANT)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        fonctions.add(enseignantFonction);

        authentification.setRoles(fonctions);
        authentification.setIdPersonne(personne);

        // à deployer
       // emailService.sendSimpleMessage(enseignantDto.getEmail(), "LOGIN ESP Antsiranana  ", " Votre compte a été créé avec le mot de passe suivant." + password);

        enseignant.setIdPersonne(personne);

        personneRepository.save(personne);
        authentificationRepository.save(authentification);

        enseignantRepository.save(enseignant);
    }

    public String generateRandomPassword() {
        // Generate a random password with 8 characters
        String password = RandomStringUtils.randomAlphanumeric(8);
        return password;
    }


    public void update(Integer id, ArrayList<EnseignantDto> dto) {

        for(EnseignantDto enseignantDto: dto){
            Enseignant enseignant = enseignantRepository.findById(id).orElseThrow();
            Authentification authentification = enseignant.getIdPersonne().getAuthentification();
            authentification.setUsername(enseignantDto.getEmail());

            enseignant.setGradeEnseignant(enseignantDto.getGrade());
            enseignant.setSpecialite(enseignantDto.getSpecialite());
            enseignant.setStatusEnseignant(enseignantDto.getStatus());
            enseignant.getIdPersonne().setNom(enseignantDto.getNom());
            enseignant.getIdPersonne().setPrenoms(enseignantDto.getPrenoms());
            enseignant.getIdPersonne().setEmail(enseignantDto.getEmail());

            enseignantRepository.save(enseignant);
        }
    }


    public ArrayList<EnseignantDto> get() {
        List<Enseignant> listenseignant = enseignantRepository.findAll();
        ArrayList<EnseignantDto> enseignantDtos = new ArrayList<>();

        for(Enseignant enseignant : listenseignant){
            Integer i = 0;
            enseignantDtos.add(i, new EnseignantDto(
                    enseignant.getId(),
                    enseignant.getIdPersonne().getNom(),
                    enseignant.getIdPersonne().getPrenoms(),
                    enseignant.getGradeEnseignant(),
                    enseignant.getSpecialite(),
                    enseignant.getStatusEnseignant(),
                    enseignant.getIdPersonne().getEmail()
            ));
            i+=1;
        }
        return enseignantDtos;
    }

    public EnseignantDto getById(Integer id) {
        Personne personne = personneRepository.findById(id).orElseThrow();
        Enseignant enseignant = personne.getEnseignant();

        EnseignantDto enseignantDto = new EnseignantDto();

        enseignantDto.setId(enseignant.getId());
        enseignantDto.setNom(enseignant.getIdPersonne().getNom());
        enseignantDto.setPrenoms(enseignant.getIdPersonne().getPrenoms());
        enseignantDto.setGrade(enseignant.getGradeEnseignant());
        enseignantDto.setSpecialite(enseignant.getSpecialite());
        enseignantDto.setStatus(enseignant.getStatusEnseignant());
        enseignantDto.setEmail(enseignant.getIdPersonne().getEmail());

        return enseignantDto;
    }
@Transactional
    public void delete(Integer id) {
        Enseignant enseignant = enseignantRepository.findById(id).orElseThrow();
        Personne personne= enseignant.getIdPersonne();
        Fonction fonction = fonctionRepository.findByName(ERole.ENSEIGNANT).orElseThrow();

        Status status = statusRepository.findByIdAuthentificationAndIdFonction(personne.getAuthentification(),fonction).orElseThrow();

        statusRepository.delete(status);
        enseignantRepository.deleteById(id);
    }

}
