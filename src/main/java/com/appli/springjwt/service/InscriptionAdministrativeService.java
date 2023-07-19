package com.appli.springjwt.service;

import com.appli.springjwt.dto.InscriptionAdministrativeDto;
import com.appli.springjwt.models.*;
import com.appli.springjwt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static org.aspectj.runtime.internal.Conversions.intValue;

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
        Integer matricule;

        for(InscriptionAdministrativeDto inscriptionAdministrativeDto: dto){

            Anneeuniv anneeuniv = anneeunivRepository.findTopByOrderByIdDesc().orElseThrow();
            Personne personne = personneRepository.findById(inscriptionAdministrativeDto.getIdPersonne()).orElseThrow();
            Bacc bacc = baccRepository.findById(inscriptionAdministrativeDto.getIdBacc()).orElseThrow();

            Autorisationinscriptiona autorisation = autorisationinscriptionaRepository.findByIdPersonneAndIdAu(
                    personne, anneeuniv
            );

            Niveau niveau = niveauRepository.findById(autorisation.getIdNiveau().getId()).orElseThrow();

            String[] dateSplit = anneeuniv.getNomAU().split(" - ");
            String dateSplit1 = dateSplit[0];
            String dateSplit2 = dateSplit[1];
            String datePrefix =  dateSplit1.substring(dateSplit1.length()-2) + dateSplit2.substring(dateSplit2.length()-2);
            System.out.println(  dateSplit1.substring(dateSplit1.length()-2));
            System.out.println( dateSplit2.substring(dateSplit2.length()-2) );

            Etudiant etudiant = new Etudiant();

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

                etudiant.setAnneeBacc(inscriptionAdministrativeDto.getAnneeBacc());
                etudiant.setIdBacc(bacc);

            } else {

                if(etudiantRepository.count()<1){
                    matricule = 1 ;
                }else{
                    // matricule = etudiantRepository.findById(etudiantRepository.count()).orElseThrow().getNumeroMatricule();
                    matricule = etudiantRepository.findById(intValue(etudiantRepository.count())).orElseThrow().getNumeroMatricule() + 1;
                }

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

            if(inscriptionadministrativeRepository.existsByIdAuAndIdEtudiant(anneeuniv,etudiant)){
                Inscriptionadministrative inscription = inscriptionadministrativeRepository.findByIdAuAndIdEtudiant(anneeuniv,etudiant).orElseThrow();

                inscription.setValiditeIa(false);
                inscription.setIdAu(anneeuniv);
                inscription.setIdNiveau(niveau);
                inscription.setIdEtudiant(etudiant);
                inscription.getIdEtudiant().setIdBacc(bacc);

                inscriptionadministrativeRepository.save(inscription);

            }else {
                Inscriptionadministrative inscription = new Inscriptionadministrative();

                inscription.setValiditeIa(false);
                inscription.setIdAu(anneeuniv);
                inscription.setIdNiveau(niveau);
                inscription.setIdEtudiant(etudiant);
                inscription.getIdEtudiant().setIdBacc(bacc);

                inscriptionadministrativeRepository.save(inscription);
            }
    }
}

    public void update(Integer id, InscriptionAdministrativeDto inscriptionAdministrativeDto) {
        Inscriptionadministrative inscriptionadministrative = inscriptionadministrativeRepository.findById(id).orElseThrow();
        inscriptionadministrative.setValiditeIa(inscriptionAdministrativeDto.getValiditeIA());

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
            i += 1;
        }
        return dto;
    }

    //private final AnneeunivRepository anneeunivRepository;

    @Autowired
    public InscriptionAdministrativeService(AnneeunivRepository anneeunivRepository) {
        this.anneeunivRepository = anneeunivRepository;
    }
    public static boolean isNomAUAlreadyExists(String nomAU) {
        // Vérifie si une Anneeuniv avec le même nomAU existe déjà
        return anneeunivRepository.findByNomAU(nomAU).isPresent();
    }
}
