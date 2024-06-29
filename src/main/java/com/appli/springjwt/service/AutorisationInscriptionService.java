package com.appli.springjwt.service;

import com.appli.springjwt.models.*;
import com.appli.springjwt.repository.*;
import com.appli.springjwt.dto.AuthentificationDto;
import com.appli.springjwt.dto.AutorisationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.appli.springjwt.utils.EmailUtils.getEmailMessageEtudiant;

@Service
public class AutorisationInscriptionService {
    public static final String LOGIN_ESP_ANTSIRANANA = "LOGIN ESP Antsiranana ";
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

    @Autowired
    EmailService emailService;

    @Autowired
    DefinitionparcourRepository definitionparcourRepository;

    public Candidatconcourstci creerAutorisation(Integer idConcours, AutorisationDto autorisationDto) {
System.out.println("donne" + idConcours + "autre" + autorisationDto);
        Concourstci concourstci = concourstciRepository.findById(idConcours).orElseThrow();
        List<Centreconcourstci> centreconcourstci = centreconcourstciRepository.findByIdCTCI(concourstci);

        for (Centreconcourstci centreconcours : centreconcourstci) {
            ArrayList<Candidatconcourstci> candidatList = candidatconcourstciRepository.findAllByPassationCandidatCTCIAndIdCentreCTCI(true, centreconcours);

            for (Candidatconcourstci candidat : candidatList) {

                Personne personne = candidat.getIdPersonne();

                Niveau niveau = niveauRepository.findById(autorisationDto.getIdNiveau()).orElseThrow();
                Anneeuniv anneeuniv = anneeunivRepository.findById(autorisationDto.getIdAU()).orElseThrow();
                System.out.println("ita " + personne.getId() + personne.getNom() + " niveau " + niveau.getNiveau() + "AU "+ anneeuniv.getNomAU());
                if (!autorisationinscriptionaRepository.existsByIdPersonneAndIdAuAndIdNiveau(personne, anneeuniv, niveau)) {
                    Autorisationinscriptiona autorisationinscriptiona = new Autorisationinscriptiona(
                            false, personne, niveau, anneeuniv
                    );
                    System.out.println("ato /////////////////////" + autorisationinscriptiona );
                    autorisationinscriptionaRepository.save(autorisationinscriptiona);
                } else {
                  //  System.out.println("kkkkk");
              //      Autorisationinscriptiona autorisationinscriptiona = autorisationinscriptionaRepository.findByIdPersonneAndIdAuAndIdNiveau(personne, anneeuniv, niveau);
              //    System.out.println("ziy " + autorisationinscriptiona);
              //    autorisationinscriptiona.setAutorisation(true);
                   // Autorisationinscriptiona autorisationinscriptiona = new Autorisationinscriptiona(
                  //          true, personne, niveau, anneeuniv
                   // );

               //     System.out.println("ato /////////////////////" + autorisationinscriptiona );
                     //  autorisationinscriptionaRepository.save(autorisationinscriptiona);
                }
            }
        }

        return null;
    }


    public Candidatconcourstci creerAutorisationAttente(Integer idConcours, AutorisationDto autorisationDto) {

        Concourstci concourstci = concourstciRepository.findById(idConcours).orElseThrow();
        List<Centreconcourstci> centreconcourstci = centreconcourstciRepository.findByIdCTCI(concourstci);

        for (Centreconcourstci centreconcours : centreconcourstci) {
            ArrayList<Candidatconcourstci> candidatList = candidatconcourstciRepository.findAllByPassationCandidatCTCIAttenteAndIdCentreCTCI(true, centreconcours);

            for (Candidatconcourstci candidat : candidatList) {

                Personne personne = candidat.getIdPersonne();

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


        String nomAU = autorisationinscriptiona.getIdAu().getNomAU();
        String niveau = autorisationinscriptiona.getIdNiveau().getNiveau();

        ArrayList<Definitionparcour> parcoursteste = definitionparcourRepository.findAllByIdAuAndIdNiveau(
                autorisationinscriptiona.getIdAu(),
                autorisationinscriptiona.getIdNiveau());

        var i = 0;

        String parcoursInscription = null;
        String parcoursInscriptionAcronyme = null;
        String mention = null;

        for (Definitionparcour parcour : parcoursteste) {

            String sem = parcour.getIdSemestre().getSemestre();
            if (Objects.equals(sem, "S1")){

                parcoursInscription = parcour.getIdParcours().getParcours();
                parcoursInscriptionAcronyme = parcour.getIdParcours().getAcronymeParcours();
                mention = parcour.getIdDm().getIdMention().getMention();
            }
            if (Objects.equals(sem, "S3")){
                parcoursInscription = parcour.getIdParcours().getParcours();
                parcoursInscriptionAcronyme = parcour.getIdParcours().getAcronymeParcours();
                mention = parcour.getIdDm().getIdMention().getMention();
            }
            if (Objects.equals(sem, "S5")){
                parcoursInscription = parcour.getIdParcours().getParcours();
                parcoursInscriptionAcronyme = parcour.getIdParcours().getAcronymeParcours();
                mention = parcour.getIdDm().getIdMention().getMention();
            }
            if (Objects.equals(sem, "S7")){
                parcoursInscription = parcour.getIdParcours().getParcours();
                parcoursInscriptionAcronyme = parcour.getIdParcours().getAcronymeParcours();
                mention = parcour.getIdDm().getIdMention().getMention();
            }
            if (Objects.equals(sem, "S9")){
                parcoursInscription = parcour.getIdParcours().getParcours();
                parcoursInscriptionAcronyme = parcour.getIdParcours().getAcronymeParcours();
                mention = parcour.getIdDm().getIdMention().getMention();
            }
        }

        String randomPass = alphaNumericString(8);
        //String randomPass ="TEST";

        String nom = autorisationinscriptiona.getIdPersonne().getNom();
        String prenom = autorisationinscriptiona.getIdPersonne().getPrenoms();
        String pseudo;
        String prenomSplit[];
        Set<Fonction> fonctions = new HashSet<>();
        System.out.println("aaaaaaaaaaaaaaaaaaaaaa");


        if (prenom == null) {
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


                System.out.println(nom);
                System.out.println(prenom);
                System.out.println(pseudo);
                System.out.println(randomPass);
                System.out.println(mention);
                System.out.println(parcoursInscription);
                System.out.println(parcoursInscriptionAcronyme);
                System.out.println(nomAU);
                System.out.println(niveau);


                //TODO Ici envoie email
                emailService.sendSimpleMessage(
                        "rakotoharilalainafabrino@gmail.com",
                        LOGIN_ESP_ANTSIRANANA,
                        getEmailMessageEtudiant(
                                nom,
                                prenom,
                                randomPass,
                                pseudo,
                                nomAU,
                                mention,
                                parcoursInscription,
                                parcoursInscriptionAcronyme,
                                niveau));

                return new AuthentificationDto(pseudo, randomPass);

            } else {

                Fonction etudianFonction = roleRepository.findByName(ERole.ETUDIANT).orElseThrow();
                fonctions.add(etudianFonction);

                Authentification authentification = new Authentification(pseudo, encoder.encode(randomPass), autorisationinscriptiona.getIdPersonne(), randomPass, fonctions);
                userRepository.save(authentification);
                autorisationinscriptionaRepository.save(autorisationinscriptiona);

                //TODO Ici envoie email
                emailService.sendSimpleMessage(
                        "rakotoharilalainafabrino@gmail.com",
                        LOGIN_ESP_ANTSIRANANA,
                        getEmailMessageEtudiant(
                                nom,
                                prenom,
                                randomPass,
                                pseudo,
                                nomAU,
                                mention,
                                parcoursInscription,
                                parcoursInscriptionAcronyme,
                                niveau));

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

            System.out.println(" ======================== "+autorisation.getIdPersonne());
            ArrayList<Personne> personne = personneRepository.findByPrenomsAndNom(autorisation.getIdPersonne().getPrenoms(), autorisation.getIdPersonne().getNom());

            if (personne.size() == 1 ){
                Authentification password = authentificationRepository.findByIdPersonne(autorisation.getIdPersonne());
                if (password!= null ){
                    System.out.println("PASSWORD " + password.getPass_word());
                    String mdp = password.getPass_word();
                    /*if (mdp.length() != 8 ){
                        System.out.println("Aucun mdp");
                    }else {
                        autorisationDto.add(i, new AutorisationDto(
                                autorisation.getId(),
                                autorisation.getIdPersonne().getNom(),
                                autorisation.getIdPersonne().getPrenoms(),
                                autorisation.getNumeroRecu(),
                                autorisation.getAutorisation()
                                ,password.getPass_word(),
                                password.getUsername()
                        ));
                    }*/

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
            }else {
                for (Personne personne1: personne){
                    Authentification password = authentificationRepository.findByIdPersonne(personne1);
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
                       // autorisationDto.add(i, new AutorisationDto(
                        //        autorisation.getId(),
                       //         autorisation.getIdPersonne().getNom(),
                        //        autorisation.getIdPersonne().getPrenoms(),
                        //        autorisation.getNumeroRecu(),
                        //        autorisation.getAutorisation()
                      //  ));
                    }
                }

            }



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
        System.out.println("id personne " + idPersonne + " id au " + idAU);
        //Todo mana double
        Personne personne = personneRepository.findById(idPersonne).orElse(null);
        System.out.println(personne.getNom());
        ArrayList<Personne> personne1 = personneRepository.findByPrenomsAndNom( personne.getPrenoms() ,personne.getNom());
        //System.out.println(personne1.getId());
        for (Personne personne2 : personne1){
            System.out.println(personne2.getId());
            Autorisationinscriptiona autorisation = autorisationinscriptionaRepository.findByIdPersonneAndIdAu(
                    personneRepository.findById(personne2.getId()).orElse(null),
                    anneeunivRepository.findById(idAU).orElse(null));
            if (autorisation != null){
                autorisationDto.setNiveau(autorisation.getIdNiveau().getNiveau());
            }
        }

       // Autorisationinscriptiona autorisation = autorisationinscriptionaRepository.findByIdPersonneAndIdAu(
       //         personneRepository.findById(idPersonne).orElse(null),
     //           anneeunivRepository.findById(idAU).orElse(null));
     //   System.out.println("vrais" + autorisation);
      //  autorisationDto.setNiveau(autorisation.getIdNiveau().getNiveau());
        return autorisationDto;
    }

    public void creerAutorisationEtudiant(Integer id, Integer idAnnee, ArrayList<AutorisationDto> autorisationDto) {

        System.out.println("T 1");
        for (AutorisationDto dto : autorisationDto) {
            Personne personne = personneRepository.findById(dto.getIdPersonne()).orElseThrow();
            Niveau niveau = niveauRepository.findById(id).orElseThrow();
            Anneeuniv anneeuniv = anneeunivRepository.findById(idAnnee).orElseThrow();

            System.out.println("T 2");

            if (!autorisationinscriptionaRepository.existsByIdPersonneAndIdAuAndIdNiveau(personne, anneeuniv, niveau)) {
                System.out.println("T 3");
                Authentification personneUpdate = authentificationRepository.findByIdPersonne(personne);
                if (personneUpdate != null){
                    System.out.println("T 4");
                    personneUpdate.setPass_word("");
                   // personneUpdate.setPassword(null);
                    authentificationRepository.save(personneUpdate);
                }
                System.out.println("T 6");
                Autorisationinscriptiona autorisationinscriptiona = new Autorisationinscriptiona();
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
                System.out.println("T 7");
            }
        }
    }
}

