package com.appli.springjwt.service;

import com.appli.springjwt.dto.CandidatConcoursDto;
import com.appli.springjwt.dto.CandidatDto;
import com.appli.springjwt.models.*;
import com.appli.springjwt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
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


    public Candidatconcourstci creerCandidat(CandidatDto dto){

        ArrayList<CandidatConcoursDto> objCandidatConcoursDto = dto.getCandidatConcoursTCI();

        for (CandidatConcoursDto candidat :objCandidatConcoursDto ){
            if (candidat.getId() == null) {

                Personne personne = new Personne(candidat.getNom(),candidat.getPrenoms(),candidat.getTelephone());
                personneRepository.save(personne);

                Centreconcourstci centreconcourstci = centreconcourstciRepository.findById(candidat.getIdCentreCTCI()).orElseThrow();

                Candidatconcourstci candidatconcourstci = new Candidatconcourstci(candidat.getNumeroCandidatCTCI(),false,personne,centreconcourstci);
                candidatconcourstciRepository.save(candidatconcourstci);

            } else{

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
                    candidat.getIdCentreCTCI().getId(),
                    candidat.getIdCentreCTCI().getNomCentreCTCI()
            ));
            i+=1;
        }

        Collections.reverse(candidatConcoursDtos);
        return candidatConcoursDtos;
    }

    public ArrayList<CandidatConcoursDto> getCandidatConcoursList(Integer idConcours) {
        BigDecimal somme = BigDecimal.valueOf(0);
        BigDecimal credit = BigDecimal.valueOf(0);
        Concourstci concourstci = concourstciRepository.findById(idConcours).orElseThrow();
        System.out.println("CD2");

        List<Centreconcourstci> centreconcourstci = centreconcourstciRepository.findByIdCTCI(concourstci);
        System.out.println("CD4");

        ArrayList<CandidatConcoursDto> candidatConcoursDtos= new ArrayList<>();

        for (Centreconcourstci centreconcours: centreconcourstci ){

            ArrayList<Candidatconcourstci> ObjCandidatconcourstci = candidatconcourstciRepository.findAllByIdCentreCTCI(centreconcourstciRepository.findById(centreconcours.getId()).orElseThrow());
            System.out.println("nombre des candidat par centre : "+ObjCandidatconcourstci.size());

            for (Candidatconcourstci candidat :ObjCandidatconcourstci ){
                Integer i = 0;
                somme = BigDecimal.valueOf(0);
                credit = BigDecimal.valueOf(0);
                Set<Notematiereconcourstci> notematiereconcourstci = candidat.getNotematiereconcourstcis();

                for (Notematiereconcourstci notematiere: notematiereconcourstci){

                    BigDecimal creditMCTCI = notematiere.getIdMctci().getCreditMCTCI();
                    BigDecimal NoteWithCoef = notematiere.getNoteMctci().multiply(creditMCTCI) ;
                    somme = somme.add(NoteWithCoef);

                    credit = credit.add(creditMCTCI) ;

                }
                  BigDecimal moyenne = BigDecimal.valueOf(0);
                if (!credit.equals(BigDecimal.ZERO)){
                    moyenne = somme.divide(credit , 2 , RoundingMode.HALF_UP);
                    //todo ajout candidat.getPassationCandidatCTCIAttente()
                }

                candidatConcoursDtos.add(i,new CandidatConcoursDto(
                        candidat.getId(),
                        candidat.getIdPersonne().getNom(),
                        candidat.getIdPersonne().getPrenoms(),
                        candidat.getIdPersonne().getTelephone(),
                        candidat.getNumeroCandidatCTCI(),
                        moyenne,
                        candidat.getPassationCandidatCTCI(),
                        candidat.getPassationCandidatCTCIAttente(),
                        candidat.getIdCentreCTCI().getId(),
                        candidat.getIdCentreCTCI().getNomCentreCTCI()
                ));

                System.out.println("CD11");
                i+=1;
                // TODO vita verification niany
            }

        }
                System.out.println("nombre des candidat : "+candidatConcoursDtos.size());
                System.out.println("nombre des centre : "+centreconcourstci.size());
                return candidatConcoursDtos;
    }

    public ArrayList<CandidatConcoursDto> getCandidatConcoursLista(Integer idConcours) {
        BigDecimal somme = BigDecimal.valueOf(0);
        BigDecimal credit = BigDecimal.valueOf(0);

        Concourstci concourstci = concourstciRepository.findById(idConcours).orElseThrow();

        System.out.println("CD2");

        List<Centreconcourstci> centreconcourstci = centreconcourstciRepository.findByIdCTCI(concourstci);

        ArrayList<CandidatConcoursDto> candidatConcoursDtos= new ArrayList<>();
        for (Centreconcourstci centreconcours: centreconcourstci ){
            ArrayList<Candidatconcourstci> ObjCandidatconcourstci = candidatconcourstciRepository.findAllByIdCentreCTCI(centreconcourstciRepository.findById(centreconcours.getId()).orElseThrow());
            System.out.println("CD6");

            for (Candidatconcourstci candidat :ObjCandidatconcourstci ){
                Integer i = 0;
                somme = BigDecimal.valueOf(0);
                credit = BigDecimal.valueOf(0);
                Set<Notematiereconcourstci> notematiereconcourstci = candidat.getNotematiereconcourstcis();
                System.out.println("CD7");

                for (Notematiereconcourstci notematiere: notematiereconcourstci){
                    BigDecimal creditMCTCI = notematiere.getIdMctci().getCreditMCTCI();
                    BigDecimal NoteWithCoef = notematiere.getNoteMctci().multiply(creditMCTCI) ;
                    somme = somme.add(NoteWithCoef);
                    credit = credit.add(creditMCTCI) ;
                }
                BigDecimal moyenne = BigDecimal.valueOf(0);
                if (!credit.equals(BigDecimal.ZERO)){
                    moyenne = somme.divide(credit , 2 , RoundingMode.HALF_UP);
                }
                candidatConcoursDtos.add(i,new CandidatConcoursDto(
                        candidat.getId(),
                        candidat.getIdPersonne().getNom(),
                        candidat.getIdPersonne().getPrenoms(),
                        candidat.getIdPersonne().getTelephone(),
                        candidat.getNumeroCandidatCTCI(),
                        moyenne,
                        candidat.getPassationCandidatCTCI(),
                        candidat.getPassationCandidatCTCIAttente(),
                        candidat.getIdCentreCTCI().getId(),
                        candidat.getIdCentreCTCI().getNomCentreCTCI()
                ));
                System.out.println("CD11");
                i+=1;
                // TODO vita verification niany
            }
        }

        return candidatConcoursDtos;
    }

    public ArrayList<CandidatConcoursDto> getCandidatConcoursListResultat(Integer idConcours,Integer idCentre) {
        BigDecimal somme = BigDecimal.valueOf(0);
        BigDecimal credit = BigDecimal.valueOf(0);

        Concourstci concourstci = concourstciRepository.findById(idConcours).orElseThrow();
        System.out.println("CD2");

        List<Centreconcourstci> centreconcourstci = centreconcourstciRepository.findByIdCTCI(concourstci);
        System.out.println("CD3");

        Centreconcourstci centre = centreconcourstciRepository.findById(idCentre).orElseThrow();
        Integer centreConcours = null;
        for (Centreconcourstci centreconcours: centreconcourstci ){
            if(centreconcours.getNomCentreCTCI().equals(centre.getNomCentreCTCI())){
                centreConcours = centreconcours.getId();

            }
        }

        ArrayList<CandidatConcoursDto> candidatConcoursDtos= new ArrayList<>();
        ArrayList<Candidatconcourstci> ObjCandidatconcourstci = candidatconcourstciRepository.findAllByIdCentreCTCI(centreconcourstciRepository.findById(centreConcours).orElseThrow());
        System.out.println("CD6");

        for (Candidatconcourstci candidat :ObjCandidatconcourstci ){
            Integer i = 0;
            somme = BigDecimal.valueOf(0);
            credit = BigDecimal.valueOf(0);
            Set<Notematiereconcourstci> notematiereconcourstci = candidat.getNotematiereconcourstcis();
            System.out.println("CD7");

            for (Notematiereconcourstci notematiere: notematiereconcourstci){

                BigDecimal creditMCTCI = notematiere.getIdMctci().getCreditMCTCI();
                BigDecimal NoteWithCoef = notematiere.getNoteMctci().multiply(creditMCTCI) ;

                somme = somme.add(NoteWithCoef);
                credit = credit.add(creditMCTCI) ;
            }
            BigDecimal moyenne = BigDecimal.valueOf(0);
            if (!credit.equals(BigDecimal.ZERO)){
               moyenne = somme.divide(credit , 2 , RoundingMode.HALF_UP);
            }
            candidatConcoursDtos.add(i,new CandidatConcoursDto(
                    candidat.getId(),
                    candidat.getIdPersonne().getNom(),
                    candidat.getIdPersonne().getPrenoms(),
                    candidat.getIdPersonne().getTelephone(),
                    candidat.getNumeroCandidatCTCI(),
                    moyenne,
                    candidat.getPassationCandidatCTCI(),
                    candidat.getPassationCandidatCTCIAttente(),
                    candidat.getIdCentreCTCI().getId(),
                    candidat.getIdCentreCTCI().getNomCentreCTCI()
            ));
            System.out.println("CD11");
            i+=1;
            // TODO vita verification niany
        }

        return candidatConcoursDtos;
    }

    public void creerCandidatConcours(ArrayList<CandidatConcoursDto> dto) {

        for (CandidatConcoursDto candidat :dto ){

            Candidatconcourstci candidat1 = candidatconcourstciRepository.findById(candidat.getId()).orElseThrow();
            candidat1.setPassationCandidatCTCI(candidat.getPassationCandidatCTCI());
            candidat1.setPassationCandidatCTCIAttente(candidat.getPassationCandidatCTCIAttente());
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
