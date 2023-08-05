package com.appli.springjwt.service;

import com.appli.springjwt.dto.Candidats;
import com.appli.springjwt.dto.NoteMatiereConcoursDto;
import com.appli.springjwt.models.*;
import com.appli.springjwt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

@Transactional
@Service
public class NoteMatiereConcoursService {

    @Autowired
    private ConcourstciRepository concourstciRepository;
    @Autowired
    private CentreconcourstciRepository centreconcourstciRepository;
    @Autowired
    private CalendrierconcourstciRepository calendrierconcourstciRepository;
    @Autowired
    private MatiereconcourstciRepository matiereconcourstciRepository;
    @Autowired
    private NotematiereconcourstciRepository notematiereconcourstciRepository;
    @Autowired
    private CandidatconcourstciRepository candidatconcourstciRepository;

    public Candidatconcourstci creerNote(NoteMatiereConcoursDto dto) {

        ArrayList<Integer> objMatiereDtos = dto.getMatiere();
        ArrayList<Candidats> objCandidatsDtos = dto.getCandidats();
        ArrayList<BigDecimal> objNoteCandidatsDtos = dto.getCandidats().get(0).getNotes();

        Concourstci concours = concourstciRepository.findById(dto.getIdCTCI()).orElseThrow();
        Centreconcourstci centre = centreconcourstciRepository.findById(dto.getIdCentreCTCI()).orElseThrow();

        ArrayList<Notematiereconcourstci> notematiereconcoursCandidatsytci = notematiereconcourstciRepository.findAllByIdCtciAndIdCentrectci(concours, centre);
        //ArrayList<Matiereconcourstci> matiereconcourstci =  matiereconcourstciRepository.findAllById(id);

        for(int i=0; i<objCandidatsDtos.size(); i++) {

            Candidatconcourstci candidatsConcours = candidatconcourstciRepository.findById(objCandidatsDtos.get(i).getIdCandidatCTCI()).orElseThrow();
            ArrayList<BigDecimal> noteConcours = dto.getCandidats().get(i).getNotes();
            BigDecimal somme = BigDecimal.valueOf(0);

            //  if (objMatiereDtos.get(i).getId() == null) {
            for(int j=0; j<objNoteCandidatsDtos.size(); j++) {
                System.out.println("setIdCentrectci "+dto.getIdCentreCTCI());
                System.out.println("setIdCandidatctci "+objCandidatsDtos.get(i).getIdCandidatCTCI());


                Candidatconcourstci candidats = candidatconcourstciRepository.findById(objCandidatsDtos.get(i).getIdCandidatCTCI()).orElseThrow();
                Matiereconcourstci matiere = matiereconcourstciRepository.findById(dto.getMatiere().get(j)).orElseThrow();

                if (notematiereconcourstciRepository.existsByIdCtciAndIdCentrectciAndIdCandidatctciAndIdMctci(
                        concours, centre, candidats,matiere ) ) {

                    Notematiereconcourstci notematiereconcourstci = notematiereconcourstciRepository.findByIdCtciAndIdCentrectciAndIdCandidatctciAndIdMctci(
                            concours, centre, candidats,matiere).orElseThrow();

                    notematiereconcourstci.setIdCtci(concourstciRepository.findById(dto.getIdCTCI()).orElseThrow());
                    notematiereconcourstci.setIdCentrectci(centreconcourstciRepository.findById(dto.getIdCentreCTCI()).orElseThrow());
                    notematiereconcourstci.setIdCandidatctci(candidatconcourstciRepository.findById(objCandidatsDtos.get(i).getIdCandidatCTCI()).orElseThrow());
                    notematiereconcourstci.setIdMctci(matiereconcourstciRepository.findById(dto.getMatiere().get(j)).orElseThrow());
                    notematiereconcourstci.setNoteMctci(dto.getCandidats().get(i).getNotes().get(j));

                    notematiereconcourstciRepository.save(notematiereconcourstci);

                } else {

                    Notematiereconcourstci notematiereconcourstci = new Notematiereconcourstci();

                    notematiereconcourstci.setIdCtci(concourstciRepository.findById(dto.getIdCTCI()).orElseThrow());
                    notematiereconcourstci.setIdCentrectci(centreconcourstciRepository.findById(dto.getIdCentreCTCI()).orElseThrow());
                    notematiereconcourstci.setIdCandidatctci(candidatconcourstciRepository.findById(objCandidatsDtos.get(i).getIdCandidatCTCI()).orElseThrow());
                    notematiereconcourstci.setIdMctci(matiereconcourstciRepository.findById(dto.getMatiere().get(j)).orElseThrow());
                    notematiereconcourstci.setNoteMctci(dto.getCandidats().get(i).getNotes().get(j));

                    notematiereconcourstciRepository.save(notematiereconcourstci);
                }

            }
            // }
            for(int n = 0; n<noteConcours.size(); n++) {
                somme = somme.add(noteConcours.get(n));
            }

           // candidatsConcours.setMoyenneCandidatCTCI(somme.divide(BigDecimal.valueOf(noteConcours.size())));
        }

        //return candidatconcourstciRepository.findById(objCandidatsDtos.get(0).getIdCandidatCTCI()).orElseThrow();
        return null;
    }

    public NoteMatiereConcoursDto getNote(Integer numeroConcours, Integer numeroCentre) {
        System.out.println("G1");
        System.out.println("Donnee recu : ");
        System.out.println("Numero concour : " + numeroConcours);
        System.out.println("Numero Centre : " + numeroCentre);

        Concourstci concourstci = concourstciRepository.findById(numeroConcours).orElseThrow();
        System.out.println("concour : " + concourstci);
        System.out.println("G2");

        Centreconcourstci centreconcourstci= centreconcourstciRepository.findById(numeroCentre).orElseThrow();
        System.out.println("centre concourstci : " + centreconcourstci);
        System.out.println("G3");

        ArrayList<NoteMatiereConcoursDto> notematiereconcoursTCIDtos = new ArrayList<>();
        NoteMatiereConcoursDto notematiereconcoursDtos = new NoteMatiereConcoursDto();
        try {
            ArrayList<Notematiereconcourstci> notematiereconcourstci = notematiereconcourstciRepository.findAllByIdCtciAndIdCentrectci(concourstci, centreconcourstci);
            System.out.println("note matiere concourstci : " + notematiereconcourstci);
            System.out.println("G4");

            System.out.println("notematiereconcourstci.get(0).getIdCandidatctci() : " + notematiereconcourstci.get(0).getIdCandidatctci());
            ArrayList<Notematiereconcourstci> noteCandidat = notematiereconcourstciRepository.findAllByIdCandidatctci(notematiereconcourstci.get(0).getIdCandidatctci());
            System.out.println("note Candidat: " + noteCandidat);
            System.out.println("G5");

            ArrayList<Integer> matiere = new ArrayList<>();
            ArrayList<Candidats> candidatsList = new ArrayList<>();

            for(Notematiereconcourstci note : noteCandidat){
                Integer i = 0;
                System.out.println("note : " + note);
                System.out.println("note.getIdMctci().getId() : " + note.getIdMctci().getId());
                matiere.add(i, note.getIdMctci().getId() );
                System.out.println("matiere : " + matiere);
                System.out.println("G6");
                i+=1;
            }
            Collections.reverse(matiere);
            System.out.println("Collections.reverse(matiere) : " + matiere);
            System.out.println("G7");

            Integer k = 0;
            System.out.println("notematiereconcourstci.size()/ noteCandidat.size() : " + notematiereconcourstci.size()/ noteCandidat.size());
            System.out.println("G8");

            for(int i = 0; i<(notematiereconcourstci.size()/ noteCandidat.size()); i++) {
                ArrayList<BigDecimal> notes = new ArrayList<>();

                System.out.println("noteCandidat.size() : " + noteCandidat.size());
                System.out.println("G9");

                for(int j = 0; j<noteCandidat.size(); j++){
                    System.out.println("notematiereconcourstci.get(j+k).getNoteMctci() : " + notematiereconcourstci.get(j+k).getNoteMctci());
                    System.out.println("G10");

                    notes.add(j,notematiereconcourstci.get(j+k).getNoteMctci());
                    System.out.println("notes : " +notes);
                    System.out.println("G11");

                }
                System.out.println("notematiereconcourstci ID : " + candidatconcourstciRepository.findById(notematiereconcourstci.get(k).getIdCandidatctci().getId()).orElseThrow().getId());
                System.out.println("notematiereconcourstci NOM :" + candidatconcourstciRepository.findById(notematiereconcourstci.get(k).getIdCandidatctci().getId()).orElseThrow().getIdPersonne().getNom());
                System.out.println("notematiereconcourstci PRENOM : " + candidatconcourstciRepository.findById(notematiereconcourstci.get(k).getIdCandidatctci().getId()).orElseThrow().getIdPersonne().getPrenoms());
                System.out.println("notematiereconcourstci NOTE : " + notes);
                System.out.println("G12");
                candidatsList.add(i,new Candidats(
                        candidatconcourstciRepository.findById(notematiereconcourstci.get(k).getIdCandidatctci().getId()).orElseThrow().getId(),
                        candidatconcourstciRepository.findById(notematiereconcourstci.get(k).getIdCandidatctci().getId()).orElseThrow().getIdPersonne().getNom(),
                        candidatconcourstciRepository.findById(notematiereconcourstci.get(k).getIdCandidatctci().getId()).orElseThrow().getIdPersonne().getPrenoms(),
                        notes
                ));
                System.out.println("candidatsList : " + candidatsList);
                System.out.println("G13");
                k= k + noteCandidat.size();
                System.out.println("k : " + k);
                System.out.println("G14");
            }

            notematiereconcoursDtos.setMatiere(matiere);
            notematiereconcoursDtos.setCandidatNotesDto(candidatsList);
            System.out.println("notematiereconcoursDtos : " + notematiereconcoursDtos);
            System.out.println("G15");
            return notematiereconcoursDtos;

        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
    /*
    public ArrayList<NoteMatiereConcoursDto> getNoteConcoursList(Integer numeroConcours, Integer numeroCentre) {
        Concourstci concourstci = concourstciRepository.findById(numeroConcours).orElseThrow();
        Centreconcourstci centreconcourstci= centreconcourstciRepository.findById(numeroCentre).orElseThrow();

        ArrayList<NoteMatiereConcoursDto> notematiereconcoursTCIDtos = new ArrayList<>();

        ArrayList<Notematiereconcourstci> notematiereconcourstci= notematiereconcourstciRepository.findAllByIdCTCIAndIdCentrectci(concourstci,centreconcourstci);

        for(Notematiereconcourstci note : notematiereconcourstci){
            Integer i = 0;
            notematiereconcoursTCIDtos.add(i,new NoteMatiereConcoursDto(
                    note.getConcourstci().getId(),
                    note.getIdCentrectci().getId(),
                    note.getIdMctci().getId(),

            ));
        }

        return null;
    }

     */

}
