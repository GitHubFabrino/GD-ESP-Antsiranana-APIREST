package com.appli.springjwt.service;

import com.appli.springjwt.dto.InscriptionAdministrativeDto;
import com.appli.springjwt.models.*;
import com.appli.springjwt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DecimalFormat;
import java.util.ArrayList;

@Transactional
@Service
public class InscriptionAdministrativeService {
    @Autowired
    NiveauRepository niveauRepository;
    @Autowired
    static
    AnneeunivRepository anneeunivRepository;
    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    PersonneRepository personneRepository;
    @Autowired
    BaccRepository baccRepository;
    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    InscriptionadministrativeRepository inscriptionadministrativeRepository;
    @Autowired
    AutorisationinscriptionaRepository autorisationinscriptionaRepository;

    public void save(ArrayList<InscriptionAdministrativeDto> dto) {

        String nombre = "000";
        DecimalFormat nf = new DecimalFormat("000");
        //n.format(20);
        String matricule;

        for(InscriptionAdministrativeDto inscriptionAdministrativeDto: dto){

            System.out.println("++++++++1" + inscriptionAdministrativeDto);

            Anneeuniv anneeuniv = anneeunivRepository.findTopByOrderByIdDesc().orElseThrow();

            Personne personne = personneRepository.findById(inscriptionAdministrativeDto.getIdPersonne()).orElseThrow();
            System.out.println("********2"+ personne);

            //Todo 25 / 03 /2024
            ArrayList<Personne> personnes = personneRepository.findByPrenomsAndNom(personne.getPrenoms(), personne.getNom());
            for(Personne personne1: personnes){
                System.out.println("********5"+ personne1);
                Integer id_personne_inscr = personne1.getId();
                Integer id_Annee_Universitaire = inscriptionAdministrativeDto.getIdAU();
                Autorisationinscriptiona autorisation = autorisationinscriptionaRepository.findByIdPersonneAndIdAu(
                        personneRepository.findById(id_personne_inscr).orElse(null),
                        anneeunivRepository.findById(id_Annee_Universitaire).orElse(null));
                System.out.println("********6"+ autorisation);
                if (autorisation != null){
                    Niveau niveau = niveauRepository.findById(autorisation.getIdNiveau().getId()).orElseThrow();
                    Bacc bacc = baccRepository.findById(inscriptionAdministrativeDto.getIdBacc()).orElseThrow();
                    Etudiant etudiant = new Etudiant();
                    if (inscriptionAdministrativeDto.getNumeroMatricule() == ""){
                        matricule = "";
                    }else {
                        matricule = inscriptionAdministrativeDto.getNumeroMatricule();
                    }
                   // Etudiant etudiant = new Etudiant();
                    /*            matricule = id_personne_inscr +"/"+ niveau.getNiveau();*/
                    if (inscriptionAdministrativeDto.getNumeroMatricule() == ""){
                        matricule = "";
                    }else {
                        matricule = inscriptionAdministrativeDto.getNumeroMatricule();
                    }

                    if(etudiantRepository.existsByIdPersonne(personne)){

                        etudiant = etudiantRepository.findByIdPersonne(personne).orElseThrow();

                        etudiant.getIdPersonne().setNom(inscriptionAdministrativeDto.getNom());
                        etudiant.getIdPersonne().setPrenoms(inscriptionAdministrativeDto.getPrenoms());
                        etudiant.getIdPersonne().setSexe(inscriptionAdministrativeDto.getSexe());
                        etudiant.getIdPersonne().setDateNaissance(inscriptionAdministrativeDto.getDateNaissance());
                        etudiant.getIdPersonne().setLieuNaissance(inscriptionAdministrativeDto.getLieuNaissance());
                        etudiant.getIdPersonne().setPaysNaissance(inscriptionAdministrativeDto.getPaysNaissance());
                        etudiant.getIdPersonne().setVilleNaissance(inscriptionAdministrativeDto.getVilleNaissance());
                        etudiant.getIdPersonne().setNationalite(inscriptionAdministrativeDto.getNationalite());
                        etudiant.getIdPersonne().setAdresse(inscriptionAdministrativeDto.getAdresse());
                        etudiant.getIdPersonne().setNumeroCIN(inscriptionAdministrativeDto.getNumeroCIN());
                        etudiant.getIdPersonne().setDateDelivreCIN(inscriptionAdministrativeDto.getDateDelivreCIN());
                        etudiant.getIdPersonne().setVilleDelivreCIN(inscriptionAdministrativeDto.getVilleDelivreCIN());
                        etudiant.getIdPersonne().setAffiliation1(inscriptionAdministrativeDto.getAffiliation1());
                        etudiant.getIdPersonne().setAffiliation2(inscriptionAdministrativeDto.getAffiliation2());
                        etudiant.getIdPersonne().setTelephone(inscriptionAdministrativeDto.getTelephone());
                        etudiant.getIdPersonne().setEmail(inscriptionAdministrativeDto.getEmail());

                        etudiant.getIdPersonne().setAnnee1(inscriptionAdministrativeDto.getAnnee1());
                        etudiant.getIdPersonne().setAnnee2(inscriptionAdministrativeDto.getAnnee2());
                        etudiant.getIdPersonne().setAnnee3(inscriptionAdministrativeDto.getAnnee3());

                        etudiant.getIdPersonne().setEtude1(inscriptionAdministrativeDto.getEtude1());
                        etudiant.getIdPersonne().setEtude2(inscriptionAdministrativeDto.getEtude2());
                        etudiant.getIdPersonne().setEtude3(inscriptionAdministrativeDto.getEtude3());

                        etudiant.setAnneeBacc(inscriptionAdministrativeDto.getAnneeBacc());
                        etudiant.setIdBacc(bacc);
                        etudiant.setNumeroMatricule(matricule);

                    }
                    else {


                        personne.setNom(inscriptionAdministrativeDto.getNom());
                        personne.setPrenoms(inscriptionAdministrativeDto.getPrenoms());
                        personne.setSexe(inscriptionAdministrativeDto.getSexe());
                        personne.setDateNaissance(inscriptionAdministrativeDto.getDateNaissance());
                        personne.setLieuNaissance(inscriptionAdministrativeDto.getLieuNaissance());
                        personne.setPaysNaissance(inscriptionAdministrativeDto.getPaysNaissance());
                        personne.setVilleNaissance(inscriptionAdministrativeDto.getVilleNaissance());
                        personne.setNationalite(inscriptionAdministrativeDto.getNationalite());
                        personne.setAdresse(inscriptionAdministrativeDto.getAdresse());
                        personne.setNumeroCIN(inscriptionAdministrativeDto.getNumeroCIN());
                        personne.setDateDelivreCIN(inscriptionAdministrativeDto.getDateDelivreCIN());
                        personne.setVilleDelivreCIN(inscriptionAdministrativeDto.getVilleDelivreCIN());
                        personne.setAffiliation1(inscriptionAdministrativeDto.getAffiliation1());
                        personne.setAffiliation2(inscriptionAdministrativeDto.getAffiliation2());
                        personne.setTelephone(inscriptionAdministrativeDto.getTelephone());
                        personne.setEmail(inscriptionAdministrativeDto.getEmail());

                        etudiant.setAnneeBacc(inscriptionAdministrativeDto.getAnneeBacc());
                        etudiant.setIdPersonne(personne);
                        etudiant.setNumeroMatricule(matricule);
                        etudiant.setIdBacc(bacc);

                    }

                    //emailRepository.save(email);
                    personneRepository.save(personne);
                    baccRepository.save(bacc);
                    etudiantRepository.save(etudiant);

                    if(etudiantRepository.existsByIdPersonne(personne)){

                        etudiant = etudiantRepository.findByIdPersonne(personne).orElseThrow();

                        etudiant.getIdPersonne().setNom(inscriptionAdministrativeDto.getNom());
                        etudiant.getIdPersonne().setPrenoms(inscriptionAdministrativeDto.getPrenoms());
                        etudiant.getIdPersonne().setSexe(inscriptionAdministrativeDto.getSexe());
                        etudiant.getIdPersonne().setDateNaissance(inscriptionAdministrativeDto.getDateNaissance());
                        etudiant.getIdPersonne().setLieuNaissance(inscriptionAdministrativeDto.getLieuNaissance());
                        etudiant.getIdPersonne().setPaysNaissance(inscriptionAdministrativeDto.getPaysNaissance());
                        etudiant.getIdPersonne().setVilleNaissance(inscriptionAdministrativeDto.getVilleNaissance());
                        etudiant.getIdPersonne().setNationalite(inscriptionAdministrativeDto.getNationalite());
                        etudiant.getIdPersonne().setAdresse(inscriptionAdministrativeDto.getAdresse());
                        etudiant.getIdPersonne().setNumeroCIN(inscriptionAdministrativeDto.getNumeroCIN());
                        etudiant.getIdPersonne().setDateDelivreCIN(inscriptionAdministrativeDto.getDateDelivreCIN());
                        etudiant.getIdPersonne().setVilleDelivreCIN(inscriptionAdministrativeDto.getVilleDelivreCIN());
                        etudiant.getIdPersonne().setAffiliation1(inscriptionAdministrativeDto.getAffiliation1());
                        etudiant.getIdPersonne().setAffiliation2(inscriptionAdministrativeDto.getAffiliation2());
                        etudiant.getIdPersonne().setTelephone(inscriptionAdministrativeDto.getTelephone());
                        etudiant.getIdPersonne().setEmail(inscriptionAdministrativeDto.getEmail());

                        etudiant.getIdPersonne().setAnnee1(inscriptionAdministrativeDto.getAnnee1());
                        etudiant.getIdPersonne().setAnnee2(inscriptionAdministrativeDto.getAnnee2());
                        etudiant.getIdPersonne().setAnnee3(inscriptionAdministrativeDto.getAnnee3());

                        etudiant.getIdPersonne().setEtude1(inscriptionAdministrativeDto.getEtude1());
                        etudiant.getIdPersonne().setEtude2(inscriptionAdministrativeDto.getEtude2());
                        etudiant.getIdPersonne().setEtude3(inscriptionAdministrativeDto.getEtude3());

                        etudiant.setAnneeBacc(inscriptionAdministrativeDto.getAnneeBacc());
                        etudiant.setIdBacc(bacc);
                        etudiant.setNumeroMatricule(matricule);

                        personneRepository.save(personne);
                        baccRepository.save(bacc);
                        etudiantRepository.save(etudiant);

                    }


                    if(inscriptionadministrativeRepository.existsByIdAuAndIdEtudiant(anneeuniv,etudiant)){
                        System.out.println("anatiny");
                        Inscriptionadministrative inscription = inscriptionadministrativeRepository.findByIdAuAndIdEtudiant(anneeuniv,etudiant).orElseThrow();

                        if (inscription == null){
                            System.out.println("null");
                        }else {
                            System.out.println(" Tsi null");
                        }
                        System.out.println(" id persone " + inscription.getIdEtudiant());
                        System.out.println(anneeuniv.getNomAU());
                        System.out.println(niveau);
                        System.out.println(etudiant);

                        inscription.setValiditeIa(true);
                        inscription.setIdAu(anneeuniv);
                        inscription.setIdNiveau(niveau);
                        inscription.setIdEtudiant(etudiant);
                        etudiant.setNumeroMatricule(matricule);
                        inscription.getIdEtudiant().setIdBacc(bacc);

                        etudiantRepository.save(etudiant);
                        inscriptionadministrativeRepository.save(inscription);
                        System.out.println(" id persone " + inscription.getIdEtudiant());
                        System.out.println(" anneeuniv.getId(); " +  anneeuniv.getId());

                    }else {
                        Inscriptionadministrative inscription = new Inscriptionadministrative();

                        inscription.setValiditeIa(true);
                        Anneeuniv anneeUniversitaire = anneeunivRepository.findById(id_Annee_Universitaire).orElseThrow();
                        inscription.setIdAu(anneeUniversitaire);
                        inscription.setIdNiveau(niveau);
                        inscription.setIdEtudiant(etudiant);
                        inscription.getIdEtudiant().setIdBacc(bacc);

                        inscriptionadministrativeRepository.save(inscription);

                        etudiant.setNumeroMatricule(matricule);
                        etudiantRepository.save(etudiant);
                    }
                }
            }


           // Integer id_personne_inscr = inscriptionAdministrativeDto.getIdPersonne();
           // Integer id_Annee_Universitaire = inscriptionAdministrativeDto.getIdAU();
         //   Autorisationinscriptiona autorisation = autorisationinscriptionaRepository.findByIdPersonneAndIdAu(
          //          personneRepository.findById(id_personne_inscr).orElse(null),
          //          anneeunivRepository.findById(id_Annee_Universitaire).orElse(null));
         //   System.out.println("********3"+ autorisation);
         //   System.out.println("********4"+ autorisation.getIdNiveau().getId());
          //  Niveau niveau = niveauRepository.findById(autorisation.getIdNiveau().getId()).orElseThrow();




    }
}

    public void update(Integer id, InscriptionAdministrativeDto inscriptionAdministrativeDto) {
        Inscriptionadministrative inscriptionadministrative = inscriptionadministrativeRepository.findById(id).orElseThrow();
        inscriptionadministrative.setValiditeIa(inscriptionAdministrativeDto.getValiditeIA());

        System.out.println("update");

        inscriptionadministrativeRepository.save(inscriptionadministrative);
    }

    public ArrayList<InscriptionAdministrativeDto> getByIdAU(Integer idNiveau, Integer idAnnee) {
        Niveau niveau = niveauRepository.findById(idNiveau).orElseThrow();
        Anneeuniv anneeuniv = anneeunivRepository.findById(idAnnee).orElseThrow();
        ArrayList<Inscriptionadministrative> inscriptionadministrative = inscriptionadministrativeRepository.findByIdAuAndIdNiveau(anneeuniv, niveau);
        ArrayList<InscriptionAdministrativeDto> dto = new ArrayList<>();

        for (Inscriptionadministrative inscription : inscriptionadministrative) {
            Integer i = 0;
            dto.add(i, new InscriptionAdministrativeDto(
                    inscription.getId(),
                    inscription.getValiditeIa(),
                    inscription.getIdEtudiant().getId(),
                    inscription.getIdEtudiant().getIdPersonne().getId(),
                    inscription.getIdEtudiant().getIdPersonne().getNom(),
                    inscription.getIdEtudiant().getIdPersonne().getPrenoms()
            ));
            System.out.println(inscription.getIdEtudiant().getIdPersonne().getNom()
);
            i += 1;
        }
        return dto;
    }

    @Autowired
    public InscriptionAdministrativeService(AnneeunivRepository anneeunivRepository) {
        this.anneeunivRepository = anneeunivRepository;
    }
    public static boolean isNomAUAlreadyExists(String nomAU) {
        return anneeunivRepository.findByNomAU(nomAU).isPresent();
    }
}
