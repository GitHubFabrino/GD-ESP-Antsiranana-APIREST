package com.appli.springjwt.service;

import com.appli.springjwt.dto.CandidatConcoursDto;
import com.appli.springjwt.dto.CandidatDto;
import com.appli.springjwt.models.*;
import com.appli.springjwt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

@Transactional
@Service
public class CandidatService {
    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    PersonneRepository personneRepository;
    @Autowired
    CentreconcourstciRepository centreconcourstciRepository;
    @Autowired
    CandidatconcourstciRepository candidatconcourstciRepository;
    @Autowired
    private ConcourstciRepository concourstciRepository;

    @Autowired
    private CalendrierconcourstciRepository calendrierconcourstciRepository;
    @Autowired
    private MatiereconcourstciRepository matiereconcourstciRepository;
    @Autowired
    private NotematiereconcourstciRepository notematiereconcourstciRepository;

    //ArrayList<CandidatConcoursDto> candidatConcoursDtos;

    public Candidatconcourstci creerCandidat(CandidatDto dto){

        ArrayList<CandidatConcoursDto> objCandidatConcoursDto = dto.getCandidatConcoursTCI();

        System.out.println("eto");
        for (CandidatConcoursDto candidat :objCandidatConcoursDto ){
            System.out.println("ato");
            if (candidat.getId() == null) {
                System.out.println("Get Id : null ");
                System.out.println("Candidat: "+ candidat.getNom());
                System.out.println("Centre d'exam : "+ candidat.getIdCentreCTCI());

                Personne personne = new Personne(candidat.getNom(),candidat.getPrenoms(),candidat.getTelephone());
                personneRepository.save(personne);
                System.out.println("Information candidats Apres enregistrer succee dans table personne: ");
                System.out.println(personne.getId());
                System.out.println(personne.getNom());
                System.out.println(personne.getPrenoms());
               // System.out.println(personne.getId());

                Centreconcourstci centreconcourstci = centreconcourstciRepository.findById(candidat.getIdCentreCTCI()).orElseThrow();

                Candidatconcourstci candidatconcourstci = new Candidatconcourstci(candidat.getNumeroCandidatCTCI(),false,personne,centreconcourstci);
                candidatconcourstciRepository.save(candidatconcourstci);
                System.out.println("Information candidats Apres enregistrer succee dans table candidatconcourstci:");
                System.out.println(candidatconcourstci.getId());
                System.out.println(candidatconcourstci.getIdCentreCTCI().getNomCentreCTCI());
                System.out.println(candidatconcourstci.getNumeroCandidatCTCI());
                System.out.println(candidatconcourstci.getIdPersonne().getNom());

            } else{
                System.out.println("Info candidats avant enregistrement :");
                System.out.println("Get Id : " + candidat.getId());
                System.out.println("Get nom : " + candidat.getNom());
                System.out.println("Get prenom : " + candidat.getPrenoms());
                System.out.println("Get IdCentreCTCI : " + candidat.getIdCentreCTCI());

                Candidatconcourstci candidat1 = candidatconcourstciRepository.findById(candidat.getId()).orElse(null);
                candidat1.getIdPersonne().setNom(candidat.getNom());
                candidat1.getIdPersonne().setPrenoms(candidat.getPrenoms());
                candidat1.getIdPersonne().setTelephone(candidat.getTelephone());
                candidat1.setNumeroCandidatCTCI(candidat.getNumeroCandidatCTCI());
                candidat1.getIdCentreCTCI().setId(candidat.getIdCentreCTCI());
                candidat1.setPassationCandidatCTCI(candidatconcourstciRepository.findById(candidat.getId()).orElseThrow().getPassationCandidatCTCI());

                candidatconcourstciRepository.save(candidat1);

            }

        }
        return null;
    }

    public ArrayList<CandidatConcoursDto> getCandidatList(Integer numero) {
        //Iterable<Concourstci> concourstci = concourstciRepository.findAll();

        //  concoursTCISansDesDto = new ArrayList<ConcoursTCISansDesDto>();
        Centreconcourstci centreconcourstci = centreconcourstciRepository.findById(numero).orElseThrow();

        ArrayList<CandidatConcoursDto> candidatConcoursDtos= new ArrayList<>();
        ArrayList<Candidatconcourstci> ObjCandidatconcourstci = candidatconcourstciRepository.findAllByIdCentreCTCI(centreconcourstci);

        for (Candidatconcourstci candidat :ObjCandidatconcourstci ){
            Integer i = 0;
            candidatConcoursDtos.add(i,new CandidatConcoursDto(
                    candidat.getId(),
                    candidat.getIdPersonne().getNom(),
                    candidat.getIdPersonne().getPrenoms(),
                    candidat.getIdPersonne().getTelephone(),
                    candidat.getNumeroCandidatCTCI(),
                    candidat.getIdCentreCTCI().getId()
            ));
            System.out.println(candidatConcoursDtos.get(i).getNom());
            i+=1;
        }
        //System.out.println(candidatConcoursDtos.get(1).getPrenoms());

        Collections.reverse(candidatConcoursDtos);
        return candidatConcoursDtos;
        //return candidatconcourstciRepository.findAllBy();
        //  return concourstciRepository.findAll();}
    }

    public ArrayList<CandidatConcoursDto> getCandidatConcoursList(Integer idConcours,Integer idCentre) {
        BigDecimal somme = BigDecimal.valueOf(0);
        //BigDecimal credit = BigDecimal.ZERO;
        byte credit = 0 ;
        System.out.println("CD1");
        System.out.println("Donne recu : ");
        System.out.println("idConcours : " + idConcours);
        System.out.println("idCentre : " +idCentre);
        Concourstci concourstci = concourstciRepository.findById(idConcours).orElseThrow();
        System.out.println("concourstci : " + concourstci);
        System.out.println("CD2");

        List<Centreconcourstci> centreconcourstci = centreconcourstciRepository.findByIdCTCI(concourstci);
        System.out.println("centreconcourstci : " + centreconcourstci);
        System.out.println("CD3");

        Centreconcourstci centre = centreconcourstciRepository.findById(idCentre).orElseThrow();
        System.out.println("centre : " + centre);
        System.out.println("CD4");

        Integer centreConcours = null;
        for (Centreconcourstci centreconcours: centreconcourstci ){
            if(centreconcours.getNomCentreCTCI().equals(centre.getNomCentreCTCI())){
                centreConcours = centreconcours.getId();
                System.out.println("centreConcours : " + centreConcours);
                System.out.println("CD5");
            }
        }

        ArrayList<CandidatConcoursDto> candidatConcoursDtos= new ArrayList<>();
        ArrayList<Candidatconcourstci> ObjCandidatconcourstci = candidatconcourstciRepository.findAllByIdCentreCTCI(centreconcourstciRepository.findById(centreConcours).orElseThrow());
        System.out.println("ObjCandidatconcourstci : " + ObjCandidatconcourstci);
        System.out.println("CD6");

        for (Candidatconcourstci candidat :ObjCandidatconcourstci ){
            Integer i = 0;
            somme = BigDecimal.valueOf(0);
            Set<Notematiereconcourstci> notematiereconcourstci = candidat.getNotematiereconcourstcis();
            System.out.println("notematiereconcourstci : " + notematiereconcourstci);
            System.out.println("CD7");

            for (Notematiereconcourstci notematiere: notematiereconcourstci){
                System.out.println("notematiere : " + notematiere);
                somme = somme.add(notematiere.getNoteMctci());
                System.out.println("credit : " + notematiere.getIdMctci().getCreditMCTCI());
                byte creditMCTCIByte = notematiere.getIdMctci().getCreditMCTCI();
                //BigDecimal creditMCTCI = new BigDecimal(creditMCTCIByte);
                System.out.println("credit : " + creditMCTCIByte);

                credit += creditMCTCIByte;
                System.out.println("somme des credit : " + credit);
                System.out.println("somme : " + somme);
                System.out.println("CD8");
            }
            System.out.println("somme TOTAL : " + somme);
            System.out.println("CD9");
          //  System.out.println( " Moyenne teste : " +somme.divide(credit));
            System.out.println("notematiereconcourstci.size(): " + notematiereconcourstci.size());
            System.out.println("CD10");

            candidatConcoursDtos.add(i,new CandidatConcoursDto(
                    candidat.getId(),
                    candidat.getIdPersonne().getNom(),
                    candidat.getIdPersonne().getPrenoms(),
                    candidat.getIdPersonne().getTelephone(),
                    candidat.getNumeroCandidatCTCI(),
                    somme.divide(BigDecimal.valueOf(notematiereconcourstci.size())),
                    candidat.getPassationCandidatCTCI()
            ));

            System.out.println(candidatConcoursDtos.get(i).getNom());
            System.out.println("Moyenne GENERALE: " +  somme.divide(BigDecimal.valueOf(notematiereconcourstci.size())));
            System.out.println("CD11");
            i+=1;
            // TODO vita verification niany
        }

        return candidatConcoursDtos;
    }

    public void creerCandidatConcours(ArrayList<CandidatConcoursDto> dto) {

        //ArrayList<CandidatConcoursDto> objCandidatConcoursDto = dto.getCandidatConcoursTCI();


        for (CandidatConcoursDto candidat :dto ){
            Candidatconcourstci candidat1 = candidatconcourstciRepository.findById(candidat.getId()).orElseThrow();
            candidat1.setPassationCandidatCTCI(candidat.getPassationCandidatCTCI());
            System.out.println(candidat.getStatus_etudiant());
            System.out.println(candidat.getNom() + candidat.getPrenoms());
            //Etudiant etudiant = etudiantRepository.findById(candidat.getId()).orElseThrow();
            //etudiant.setStatusEtudiant(candidat.getStatus_etudiant());

            //etudiantRepository.save(etudiant);
            candidatconcourstciRepository.save(candidat1);

        }
    }

    public Optional<Candidatconcourstci> getCandidatById(Integer numero) {
        return candidatconcourstciRepository.findById(numero);
    }

    public void deleteCandidat(Integer id) {
        candidatconcourstciRepository.deleteById(id);
    }


}
