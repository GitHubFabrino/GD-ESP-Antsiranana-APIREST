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

        for (CandidatConcoursDto candidat :objCandidatConcoursDto ){

            if (candidat.getId() == null) {
                System.out.println("Candidat: "+ candidat.getNom());

                Personne personne = new Personne(candidat.getNom(),candidat.getPrenoms(),candidat.getTelephone());
                personneRepository.save(personne);

                Centreconcourstci centreconcourstci = centreconcourstciRepository.findById(candidat.getIdCentreCTCI()).orElseThrow();

                Candidatconcourstci candidatconcourstci = new Candidatconcourstci(candidat.getNumeroCandidatCTCI(),false,personne,centreconcourstci);
                candidatconcourstciRepository.save(candidatconcourstci);

            } else{

                Candidatconcourstci candidat1 = candidatconcourstciRepository.findById(candidat.getId()).orElseThrow();
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
        Concourstci concourstci = concourstciRepository.findById(idConcours).orElseThrow();
        List<Centreconcourstci> centreconcourstci = centreconcourstciRepository.findByIdCTCI(concourstci);

        Centreconcourstci centre = centreconcourstciRepository.findById(idCentre).orElseThrow();

        Integer centreConcours = null;
        for (Centreconcourstci centreconcours: centreconcourstci ){
            if(centreconcours.getNomCentreCTCI().equals(centre.getNomCentreCTCI())){
                centreConcours = centreconcours.getId();
            }
        }

        ArrayList<CandidatConcoursDto> candidatConcoursDtos= new ArrayList<>();
        ArrayList<Candidatconcourstci> ObjCandidatconcourstci = candidatconcourstciRepository.findAllByIdCentreCTCI(centreconcourstciRepository.findById(centreConcours).orElseThrow());

        for (Candidatconcourstci candidat :ObjCandidatconcourstci ){
            Integer i = 0;
            somme = BigDecimal.valueOf(0);
            Set<Notematiereconcourstci> notematiereconcourstci = candidat.getNotematiereconcourstcis();
            for (Notematiereconcourstci notematiere: notematiereconcourstci){
                somme = somme.add(notematiere.getNoteMctci());
            }

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
            i+=1;
        }

        return candidatConcoursDtos;
    }

    public void creerCandidatConcours(ArrayList<CandidatConcoursDto> dto) {

        //ArrayList<CandidatConcoursDto> objCandidatConcoursDto = dto.getCandidatConcoursTCI();

        for (CandidatConcoursDto candidat :dto ){
            Candidatconcourstci candidat1 = candidatconcourstciRepository.findById(candidat.getId()).orElseThrow();
            candidat1.setPassationCandidatCTCI(candidat.getPassationCandidatCTCI());

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
