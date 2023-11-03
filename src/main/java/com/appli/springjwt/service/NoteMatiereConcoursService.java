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

        for(int i=0; i<objCandidatsDtos.size(); i++) {

            Candidatconcourstci candidatsConcours = candidatconcourstciRepository.findById(objCandidatsDtos.get(i).getIdCandidatCTCI()).orElseThrow();
            ArrayList<BigDecimal> noteConcours = dto.getCandidats().get(i).getNotes();
            BigDecimal somme = BigDecimal.valueOf(0);

            for(int j=0; j<objNoteCandidatsDtos.size(); j++) {
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
            for(int n = 0; n<noteConcours.size(); n++) {
                somme = somme.add(noteConcours.get(n));
            }

        }

        return null;
    }

    public Candidatconcourstci creerNoteByEtudiant(NoteMatiereConcoursDto dto) {

        ArrayList<Integer> objMatiereDtos = dto.getMatiere();
        ArrayList<Candidats> objCandidatsDtos = dto.getCandidats();
        ArrayList<BigDecimal> objNoteCandidatsDtos = dto.getCandidats().get(0).getNotes();

        Concourstci concours = concourstciRepository.findById(dto.getIdCTCI()).orElseThrow();
        Centreconcourstci centre = centreconcourstciRepository.findById(dto.getIdCentreCTCI()).orElseThrow();

        ArrayList<Notematiereconcourstci> notematiereconcoursCandidatsytci = notematiereconcourstciRepository.findAllByIdCtciAndIdCentrectci(concours, centre);

        for(int i=0; i<objCandidatsDtos.size(); i++) {

            Candidatconcourstci candidatsConcours = candidatconcourstciRepository.findById(objCandidatsDtos.get(i).getIdCandidatCTCI()).orElseThrow();
            ArrayList<BigDecimal> noteConcours = dto.getCandidats().get(i).getNotes();
            BigDecimal somme = BigDecimal.valueOf(0);

            for(int j=0; j<objNoteCandidatsDtos.size(); j++) {

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

        }

        return null;
    }

    public NoteMatiereConcoursDto getNote(Integer numeroConcours, Integer numeroCentre) {
        Concourstci concourstci = concourstciRepository.findById(numeroConcours).orElseThrow();
        Centreconcourstci centreconcourstci= centreconcourstciRepository.findById(numeroCentre).orElseThrow();

        ArrayList<NoteMatiereConcoursDto> notematiereconcoursTCIDtos = new ArrayList<>();
        NoteMatiereConcoursDto notematiereconcoursDtos = new NoteMatiereConcoursDto();
        try {
            ArrayList<Notematiereconcourstci> notematiereconcourstci = notematiereconcourstciRepository.findAllByIdCtciAndIdCentrectci(concourstci, centreconcourstci);
            ArrayList<Notematiereconcourstci> noteCandidat = notematiereconcourstciRepository.findAllByIdCandidatctci(notematiereconcourstci.get(0).getIdCandidatctci());

            ArrayList<Integer> matiere = new ArrayList<>();
            ArrayList<Candidats> candidatsList = new ArrayList<>();

            for(Notematiereconcourstci note : noteCandidat){
                Integer i = 0;
                matiere.add(i, note.getIdMctci().getId() );
                i+=1;
            }
            Collections.reverse(matiere);

            Integer k = 0;
            for(int i = 0; i<(notematiereconcourstci.size()/ noteCandidat.size()); i++) {
                ArrayList<BigDecimal> notes = new ArrayList<>();

                for(int j = 0; j<noteCandidat.size(); j++){
                    notes.add(j,notematiereconcourstci.get(j+k).getNoteMctci());
                }

                candidatsList.add(i,new Candidats(
                        candidatconcourstciRepository.findById(notematiereconcourstci.get(k).getIdCandidatctci().getId()).orElseThrow().getId(),
                        candidatconcourstciRepository.findById(notematiereconcourstci.get(k).getIdCandidatctci().getId()).orElseThrow().getIdPersonne().getNom(),
                        candidatconcourstciRepository.findById(notematiereconcourstci.get(k).getIdCandidatctci().getId()).orElseThrow().getIdPersonne().getPrenoms(),
                        notes
                ));
                System.out.println("G13");
                k= k + noteCandidat.size();
                System.out.println("k : " + k);
                System.out.println("G14");
            }

            notematiereconcoursDtos.setMatiere(matiere);
            notematiereconcoursDtos.setCandidatNotesDto(candidatsList);
            System.out.println("G15");
            return notematiereconcoursDtos;

        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

}
