package com.appli.springjwt.service;

import com.appli.springjwt.models.*;
import com.appli.springjwt.repository.*;
import com.appli.springjwt.dto.AuthentificationDto;
import com.appli.springjwt.dto.AutorisationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AutorisationInscriptionService {
    @Autowired
    CentreconcourstciRepository centreconcourstciRepository;
    @Autowired
    CandidatconcourstciRepository candidatconcourstciRepository;
    @Autowired
    ConcourstciRepository concourstciRepository;

    @Autowired
    AnneeunivRepository anneeunivRepository;
    @Autowired
    PersonneRepository personneRepository;
    @Autowired
    AutorisationinscriptionaRepository autorisationinscriptionaRepository;
    @Autowired
    NiveauRepository niveauRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthentificationRepository authentificationRepository;

    public Candidatconcourstci creerAutorisation(Integer idConcours, AutorisationDto autorisationDto) {
        System.out.println("AU 1 " );
        System.out.println("AutorisationDto : " + autorisationDto.getNiveau() );

        Concourstci concourstci = concourstciRepository.findById(idConcours).orElseThrow();
        List<Centreconcourstci> centreconcourstci = centreconcourstciRepository.findByIdCTCI(concourstci);

        for (Centreconcourstci centreconcours : centreconcourstci) {
            ArrayList<Candidatconcourstci> candidatList = candidatconcourstciRepository.findAllByPassationCandidatCTCIAndIdCentreCTCI(true, centreconcours);

            for (Candidatconcourstci candidat : candidatList) {

                Personne personne = candidat.getIdPersonne();
                System.out.println(personne);
                Niveau niveau = niveauRepository.findById(autorisationDto.getIdNiveau()).orElseThrow();
                Anneeuniv anneeuniv = anneeunivRepository.findById(autorisationDto.getIdAU()).orElseThrow();

                if (!autorisationinscriptionaRepository.existsByIdPersonneAndIdAuAndIdNiveau(personne, anneeuniv, niveau)) {
                    Autorisationinscriptiona autorisationinscriptiona = new Autorisationinscriptiona(
                            false, personne, niveau, anneeuniv
                    );
                    autorisationinscriptionaRepository.save(autorisationinscriptiona);
                } else {

                }
            }
        }

        return null;
    }

    public ArrayList<AutorisationDto> ListAutorisation() {

        ArrayList<Autorisationinscriptiona> autorisationinscriptiona = autorisationinscriptionaRepository.findAll();
        ArrayList<AutorisationDto> autorisationDto = new ArrayList<>();

        for (Autorisationinscriptiona autorisation : autorisationinscriptiona) {
            Integer i = 0;
            autorisationDto.add(i, new AutorisationDto(
                    autorisation.getId(),
                    autorisation.getIdPersonne().getNom(),
                    autorisation.getIdPersonne().getPrenoms(),
                    autorisation.getNumeroRecu(),
                    autorisation.getAutorisation()
            ));
            i += 1;
        }
        Collections.reverse(autorisationDto);
        return autorisationDto;
    }

    public AuthentificationDto modifierAutorisation(Integer idAutorisation, AutorisationDto dto) {
        Autorisationinscriptiona autorisationinscriptiona = autorisationinscriptionaRepository.findById(idAutorisation).orElseThrow();
        autorisationinscriptiona.setAutorisation(dto.getAutorisation());
        autorisationinscriptiona.setNumeroRecu(dto.getNumeroRecu());

        String randomPass = alphaNumericString(8);
        //String randomPass ="TEST";

        String nom = autorisationinscriptiona.getIdPersonne().getNom();
        String prenom = autorisationinscriptiona.getIdPersonne().getPrenoms();
        String pseudo;
        String prenomSplit[];
        Set<Fonction> fonctions = new HashSet<>();

        if (prenom == "") {
            pseudo = nom.split(" ")[0] + nom.split(" ")[0];
        } else {
            prenomSplit = prenom.split(" ");
            System.out.println(prenomSplit[0]);
            pseudo = nom.split(" ")[0] + "_" + (prenomSplit[0]);
        }

        if (autorisationinscriptionaRepository.existsByNumeroRecu(dto.getNumeroRecu())) {
            return null;
        } else {

            if (userRepository.existsByUsername(pseudo)) {

                Authentification authentification = userRepository.findByUsername(pseudo).orElseThrow();
                //Authentification authentification = new Authentification(pseudo, encoder.encode(randomPass));
                authentification.setPass_word(randomPass);
                authentification.setPassword(encoder.encode(randomPass));
                userRepository.save(authentification);
                autorisationinscriptionaRepository.save(autorisationinscriptiona);

                return new AuthentificationDto(pseudo, randomPass);

            } else {

                Fonction etudianFonction = roleRepository.findByName(ERole.ETUDIANT).orElseThrow();
                fonctions.add(etudianFonction);
/*
            authentification.setIdPersonne(authentification.getIdPersonne());
            authentification.setRoles(fonctions);
*/
                Authentification authentification = new Authentification(pseudo, encoder.encode(randomPass), autorisationinscriptiona.getIdPersonne(),randomPass, fonctions);
                userRepository.save(authentification);
                autorisationinscriptionaRepository.save(autorisationinscriptiona);

                return new AuthentificationDto(pseudo, randomPass);
            }

        }

    }

    public ArrayList<AutorisationDto> ListAutorisationNiveau(Integer id, Integer idAU) {
        Niveau niveau = niveauRepository.findById(id).orElseThrow();
        Anneeuniv anneeuniv = anneeunivRepository.findById(idAU).orElseThrow();
        ArrayList<Autorisationinscriptiona> autorisationinscriptiona = autorisationinscriptionaRepository.findAllByIdNiveauAndIdAu(niveau, anneeuniv);
        ArrayList<AutorisationDto> autorisationDto = new ArrayList<>();

        for (Autorisationinscriptiona autorisation : autorisationinscriptiona) {
            Integer i = 0;

            System.out.println(autorisation.getIdPersonne());
           Authentification password = authentificationRepository.findByIdPersonne(autorisation.getIdPersonne());
           if (password!= null){
               System.out.println("PASSWORD " + password.getPass_word());
               autorisationDto.add(i, new AutorisationDto(
                       autorisation.getId(),
                       autorisation.getIdPersonne().getNom(),
                       autorisation.getIdPersonne().getPrenoms(),
                       autorisation.getNumeroRecu(),
                       autorisation.getAutorisation()
                       ,password.getPass_word(),
                       password.getUsername()
               ));
               System.out.println(password.getUsername());
           }else{
               autorisationDto.add(i, new AutorisationDto(
                       autorisation.getId(),
                       autorisation.getIdPersonne().getNom(),
                       autorisation.getIdPersonne().getPrenoms(),
                       autorisation.getNumeroRecu(),
                       autorisation.getAutorisation()
               ));
           }





     /*  password.setPass_word("");
            authentificationRepository.save(password);*/



            i += 1;
        }
        Collections.reverse(autorisationDto);
        return autorisationDto;

    }

    public static String alphaNumericString(int len) {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }

    public AutorisationDto listAutorisationById(Integer idPersonne, Integer idAU) {
        AutorisationDto autorisationDto = new AutorisationDto();
        Autorisationinscriptiona autorisation = autorisationinscriptionaRepository.findByIdPersonneAndIdAu(
                personneRepository.findById(idPersonne).orElse(null),
                anneeunivRepository.findById(idAU).orElse(null));
        autorisationDto.setNiveau(autorisation.getIdNiveau().getNiveau());
        return autorisationDto;
    }

    public void creerAutorisationEtudiant(Integer id, Integer idAnnee, ArrayList<AutorisationDto> autorisationDto) {

        for (AutorisationDto dto : autorisationDto) {
            Personne personne = personneRepository.findById(dto.getIdPersonne()).orElseThrow();
            Niveau niveau = niveauRepository.findById(id).orElseThrow();
            Anneeuniv anneeuniv = anneeunivRepository.findById(idAnnee).orElseThrow();

            if (!autorisationinscriptionaRepository.existsByIdPersonneAndIdAuAndIdNiveau(personne, anneeuniv, niveau)) {
                Autorisationinscriptiona autorisationinscriptiona = new Autorisationinscriptiona();
                System.out.println("NIVEAU ACTUEL : "+niveau.getNiveau());
                if(dto.getCodeRedoublement()==1 || dto.getCodeRedoublement()==2 || dto.getCodeRedoublement()==3){
                    if (niveau.getNiveau().equalsIgnoreCase("L1") ) {
                        autorisationinscriptiona.setIdNiveau(niveauRepository.findByNiveau("L2").orElseThrow());
                    } else if (niveau.getNiveau().equalsIgnoreCase("L2")) {
                        autorisationinscriptiona.setIdNiveau(niveauRepository.findByNiveau("L3").orElseThrow());
                    } else if (niveau.getNiveau().equalsIgnoreCase("L3")) {
                        autorisationinscriptiona.setIdNiveau(niveauRepository.findByNiveau("M1").orElseThrow());
                    } else if (niveau.getNiveau().equalsIgnoreCase("M1")) {
                        autorisationinscriptiona.setIdNiveau(niveauRepository.findByNiveau("M2").orElseThrow());
                    } else {
                        autorisationinscriptiona.setIdNiveau(niveauRepository.findByNiveau("D").orElseThrow());
                    }

                } else if (dto.getCodeRedoublement()==4) {
                    autorisationinscriptiona.setIdNiveau(niveau);
                }
                autorisationinscriptiona.setIdPersonne(personne);
                autorisationinscriptiona.setIdAu(anneeuniv);
                autorisationinscriptiona.setAutorisation(false);
                autorisationinscriptionaRepository.save(autorisationinscriptiona);

            } else {
            }
        }
    }
/*
    public void ajouterpassword(Authentification auth) {
        Authentification password = authentificationRepository.findByIdPersonne(auth.getIdPersonne());
        System.out.println("PASSWORD " + password);

        password.setPass_word(auth.getPass_word());
        authentificationRepository.save(password);

    }*/
}

