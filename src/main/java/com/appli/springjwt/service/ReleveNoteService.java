package com.appli.springjwt.service;

import com.appli.springjwt.dto.*;
import com.appli.springjwt.models.*;
import com.appli.springjwt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
public class ReleveNoteService {

    @Autowired
    RelevenoteRepository relevenoteRepository;
    @Autowired
    CursusRepository cursusRepository;
    @Autowired
    ProgrammeenseignementRepository programmeenseignementRepository;
    @Autowired
    UeEcRepository ueEcRepository;
    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    ResultatfinauRepository resultatfinauRepository;
    @Autowired
    ElementconstitutifRepository elementconstitutifRepository;
    @Autowired
    UniteenseignementRepository uniteenseignementRepository;
    @Autowired
    DefinitiondroitRepository definitiondroitRepository;
    @Autowired
    DefinitionparcourRepository definitionparcourRepository;
    @Autowired
    ProgrammeService programmeService;
    @Autowired
    ValidationueRepository validationueRepository;

    public void creer(Integer id, ArrayList<MoyenneEtudiantDto> moyenneEtudiantDtos) {

        for (MoyenneEtudiantDto dto : moyenneEtudiantDtos) {
            Cursus cursus = cursusRepository.findById(dto.getIdCursus()).orElseThrow();
            UeEc ueEc = ueEcRepository.findById(id).orElseThrow();
            if (!relevenoteRepository.existsByIdCursusAndIdUeEc(cursus, ueEc)) {
                Relevenote relevenote = new Relevenote(cursus, dto.getNote(),ueEc);
                relevenoteRepository.save(relevenote);

            } else {
                Relevenote relevenote = relevenoteRepository.findByIdCursusAndIdUeEc(cursus, ueEc).orElseThrow();
                relevenote.setNote(dto.getNote());
                relevenoteRepository.save(relevenote);
            }
        }
    }
    public void updateByEtudiant(Integer id, MoyenneEtudiantDto moyenneEtudiantDtos) {

            Cursus cursus = cursusRepository.findById(moyenneEtudiantDtos.getIdCursus()).orElseThrow();
            UeEc ueEc = ueEcRepository.findById(id).orElseThrow();

            if (!relevenoteRepository.existsByIdCursusAndIdUeEc(cursus, ueEc)) {
                Relevenote relevenote = new Relevenote(cursus, moyenneEtudiantDtos.getNote(),ueEc);
                relevenoteRepository.save(relevenote);

            } else {
                Relevenote relevenote = relevenoteRepository.findByIdCursusAndIdUeEc(cursus, ueEc).orElseThrow();
                relevenote.setNote(moyenneEtudiantDtos.getNote());
                relevenoteRepository.save(relevenote);
            }

    }
    public ArrayList<MoyenneEtudiantDto> getNote(Integer idUEEC, Integer idDp) {

        ArrayList<Cursus> cursus = cursusRepository.findAllByIdDp(definitionparcourRepository.findById(idDp).orElseThrow());
        ArrayList<MoyenneEtudiantDto> moyenneEtudiantDtos = new ArrayList<>();

        for(Cursus c: cursus){
            UeEc ueEc = ueEcRepository.findById(idUEEC).orElseThrow();
            Relevenote relevenote = relevenoteRepository.findByIdCursusAndIdUeEc(c,ueEc).orElse(null);
            if (relevenote == null){
                return moyenneEtudiantDtos;
            }
            moyenneEtudiantDtos.add(new MoyenneEtudiantDto(
                    relevenote.getIdCursus().getId(),
                    relevenote.getNote()));
        }

        return moyenneEtudiantDtos;
    }
    public ArrayList<MoyenneGeneraleDto> getNoteDPEtudiant(Integer idEtudiant, Integer id1, Integer id2) {

        ArrayList<MoyenneGeneraleDto> etudiantDTOList = new ArrayList<>();

        BigDecimal moyenneFinale = BigDecimal.valueOf(0);
        List<Cursus> cursusList = cursusRepository.findAllByIdDp(definitionparcourRepository.findById(id1).orElseThrow());

        List<Programmeenseignement> idPeList = programmeenseignementRepository.findByIdDpIn(Arrays.asList(definitionparcourRepository.findById(id1).orElseThrow(), definitionparcourRepository.findById(id2).orElseThrow()));

        List<UeEc> ueEcList = idPeList.stream().map(Programmeenseignement::getIdUeEc).collect(Collectors.toList());
        List<Cursus> cursusListDp = cursusRepository.findByIdEtudiantAndIdDpIn(
                etudiantRepository.findById(idEtudiant).orElseThrow(),
                Arrays.asList(definitionparcourRepository.findById(id1).orElseThrow(), definitionparcourRepository.findById(id2).orElseThrow()));

        Map<Integer, List<Cursus>> cursusListDpMap = cursusListDp.stream().collect(Collectors.groupingBy(etu -> etu.getIdEtudiant().getId()));
        System.out.println(" SIZE cursusListDpMap = "+ cursusListDpMap.size());
        Integer c = 0;

        Byte codeRedoublement =null;

        for (Map.Entry<Integer, List<Cursus>> cursus : cursusListDpMap.entrySet()) {
            c += 1;
            Etudiant etudiant = etudiantRepository.findById(cursus.getKey()).orElse(null);
            Resultatfinau resultatfinau = resultatfinauRepository.findByIdEtudiantAndIdDp(
                    etudiant,
                    definitionparcourRepository.findById(id2).orElse(null)).orElse(null);
            if(resultatfinau == null){
                codeRedoublement =null;
            }else {
                codeRedoublement = resultatfinau.getCodeRedoublement();
            }

            if (etudiant == null) {
                continue;
            }
            List<Cursus> cursusByEtudiant = cursus.getValue();
            Set<Cursus> cursusSet = etudiant.getCursus();
            BigDecimal moyenneGenerale = BigDecimal.valueOf(0);
            BigDecimal credit = BigDecimal.valueOf(0);

            for (Cursus cs : cursusByEtudiant) {

                List<Relevenote> releveNoteList = relevenoteRepository.findByIdCursus(cursusRepository.findById(cs.getId()).orElse(null));
                if (releveNoteList.isEmpty()) {
                    continue;
                }

                Integer i = 0;
                for (Relevenote releveNote : releveNoteList) {
                    i += 1;
                    moyenneGenerale = moyenneGenerale.add(releveNote.getNote().multiply(releveNote.getIdUeEc().getCreditEc()));
                    credit = credit.add(releveNote.getIdUeEc().getCreditEc());

                }
            }

            moyenneGenerale = moyenneGenerale.divide(credit,new MathContext(3));
            etudiantDTOList.add(new MoyenneGeneraleDto(etudiant.getId(), etudiant.getIdPersonne().getNom(), etudiant.getIdPersonne().getPrenoms(), moyenneGenerale,codeRedoublement));

        }

        return etudiantDTOList;
    }
    public ArrayList<MoyenneGeneraleDto> getNoteDP(Integer id1, Integer id2) {

        ArrayList<MoyenneGeneraleDto> etudiantDTOList = new ArrayList<>();
        BigDecimal moyenneFinale = BigDecimal.valueOf(0);
        List<Cursus> cursusList = cursusRepository.findAllByIdDp(definitionparcourRepository.findById(id1).orElseThrow());
        List<Programmeenseignement> idPeList = programmeenseignementRepository.findByIdDpIn(Arrays.asList(definitionparcourRepository.findById(id1).orElseThrow(), definitionparcourRepository.findById(id2).orElseThrow()));

        List<UeEc> ueEcList = idPeList.stream().map(Programmeenseignement::getIdUeEc).collect(Collectors.toList());
        List<Cursus> cursusListDp = cursusRepository.findByIdDpIn(Arrays.asList(definitionparcourRepository.findById(id1).orElseThrow(), definitionparcourRepository.findById(id2).orElseThrow()));
        Map<Integer, List<Cursus>> cursusListDpMap = cursusListDp.stream().collect(Collectors.groupingBy(etu -> etu.getIdEtudiant().getId()));
        Integer c = 0;

        Byte codeRedoublement =null;

        // Boucle pour traiter chaque étudiant
        for (Map.Entry<Integer, List<Cursus>> cursus : cursusListDpMap.entrySet()) {
            c += 1;
            Etudiant etudiant = etudiantRepository.findById(cursus.getKey()).orElse(null);
            Cursus cursus1 = cursusRepository.findByIdEtudiantAndIdDp(
                    etudiantRepository.findById(etudiant.getId()).orElseThrow(() -> new NoSuchElementException("Cursus non trouvé")),
                    definitionparcourRepository.findById(id1).orElseThrow(() -> new NoSuchElementException("Cursus non trouvé"))).orElseThrow(() -> new NoSuchElementException("Cursus non trouvé"));
            Resultatfinau resultatfinau = resultatfinauRepository.findByIdEtudiantAndIdDp(
                    etudiant,
                    definitionparcourRepository.findById(id2).orElse(null)).orElse(null);

            if(resultatfinau == null){
                codeRedoublement =null;
            }else {
                codeRedoublement = resultatfinau.getCodeRedoublement();
            }

            if (etudiant == null) {
                continue;
            }
            List<Cursus> cursusByEtudiant = cursus.getValue();
            Set<Cursus> cursusSet = etudiant.getCursus();
            BigDecimal moyenneGenerale = BigDecimal.valueOf(0);
            BigDecimal credit = BigDecimal.valueOf(0);

            for (Cursus cs : cursusByEtudiant) {

                List<Relevenote> releveNoteList = relevenoteRepository.findByIdCursus(cursusRepository.findById(cs.getId()).orElse(null));
                if (releveNoteList.isEmpty()) {
                    continue;
                }

                Integer i = 0;
                //BigDecimal credit = BigDecimal.valueOf(0);
                for (Relevenote releveNote : releveNoteList) {
                    i += 1;
                    moyenneGenerale = moyenneGenerale.add(releveNote.getNote().multiply(releveNote.getIdUeEc().getCreditEc()));
                    credit = credit.add(releveNote.getIdUeEc().getCreditEc());
                }
            }
            if (credit.floatValue() == 0 || moyenneGenerale.floatValue() == 0){

                moyenneGenerale = BigDecimal.valueOf(0);

            }else {
                moyenneGenerale = moyenneGenerale.divide(credit,new MathContext(3));
            }
            etudiantDTOList.add(new MoyenneGeneraleDto(etudiant.getId(), etudiant.getIdPersonne().getNom(), etudiant.getIdPersonne().getPrenoms(), moyenneGenerale,codeRedoublement));

        }

        return etudiantDTOList;
    }
    public MoyenneGeneraleDto modifierCodeRedoubleme(Integer idEtudiant, Integer id1, Integer id2, MoyenneEtudiantDto moyenneEtudiantDtos) {
        Etudiant etudiant = etudiantRepository.findById(idEtudiant).orElse(null);

        if (etudiant == null){
            return null;
        }else {
            Resultatfinau resultatfinau = resultatfinauRepository.findByIdEtudiantAndIdDp(
                    etudiant,
                    definitionparcourRepository.findById(id2).orElse(null)).orElse(null);
            if (resultatfinau != null ){
                resultatfinau.setCodeRedoublement(moyenneEtudiantDtos.getCodeRedoublement());
                resultatfinauRepository.save(resultatfinau);
            }else return null;
        }

        return null;
    }
    private BigDecimal calculerMoyenneGenerale(List<Relevenote> releveNoteList) {
        BigDecimal sommeNotes = BigDecimal.ZERO;
        int nombreNotes = 0;
        for (Relevenote releveNote : releveNoteList) {
            if (releveNote.getNote() != null) {
                sommeNotes = sommeNotes.add(releveNote.getNote());
                nombreNotes++;
            }
        }
        if (nombreNotes == 0) {
            return null;
        } else {
            return sommeNotes.divide(new BigDecimal(nombreNotes), 2, RoundingMode.HALF_UP);
        }
    }

    public ArrayList<ReleveNoteDto> getReleveEtudiant(Integer idEtudiant, Integer idDp1) {
        List<ProgrammeGetDto> pe1 = programmeService.getByIdDp(idDp1);
        ArrayList<ReleveNoteDto> dto = new ArrayList<>();
        Cursus cursus = cursusRepository.findByIdEtudiantAndIdDp(
                etudiantRepository.findById(idEtudiant).orElseThrow(() -> new NoSuchElementException("Cursus non trouvé")),
                definitionparcourRepository.findById(idDp1).orElseThrow(() -> new NoSuchElementException("Cursus non trouvé"))).orElseThrow(() -> new NoSuchElementException("Cursus non trouvé"));

        BigDecimal moyenneUECredit = BigDecimal.valueOf(0);
        BigDecimal moyenneUECreditTotal = BigDecimal.valueOf(0);
        BigDecimal CreditTotal = BigDecimal.valueOf(0);
        for(ProgrammeGetDto programme : pe1){

            ArrayList<Float> noteECList = new ArrayList<>();
            ArrayList<String> nomECList = new ArrayList<>();
            BigDecimal moyenneUE = BigDecimal.valueOf(0);
            BigDecimal credit = BigDecimal.valueOf(0);

            ArrayList<Integer> ueecList = programme.getIdUEEC();
            for(Integer idUEEC:ueecList){
                Relevenote releve = relevenoteRepository.findByIdCursusAndIdUeEc(
                        cursus,
                        ueEcRepository.findById(idUEEC).orElseThrow()
                ).orElse(null);
                if (releve != null){
                    noteECList.add(releve.getNote().floatValue());
                }
            }

            for( ElementConstitutifDto nomEC: programme.getNomEC()){
                nomECList.add(nomEC.getNomEC());
            }

            List<Relevenote> releveNoteList = relevenoteRepository.findByIdCursusAndIdUeEcIn(cursus,ueEcRepository.findByIdIn(ueecList));


            ArrayList<Float> note = new ArrayList<>();
            ArrayList<Byte> value = new ArrayList<>();

            if (releveNoteList != null){

                for (Relevenote releveNote : releveNoteList) {
                    note.add(releveNote.getNote().floatValue());
                    moyenneUE = moyenneUE.add(releveNote.getNote().multiply(releveNote.getIdUeEc().getCreditEc()));

                    credit = credit.add(releveNote.getIdUeEc().getCreditEc());

                }

                if (credit.floatValue() == 0){
                    moyenneUE = BigDecimal.valueOf(0);
                }else {
                    moyenneUE = moyenneUE.divide(credit,new MathContext(3));

                    moyenneUECredit = moyenneUECredit.add(moyenneUE.multiply(credit));
                    CreditTotal = CreditTotal.add(credit);

                }
            }
            Uniteenseignement uniteenseignement = uniteenseignementRepository.findById(programme.getIdUE().get(0)).orElse(null);

            if (uniteenseignement !=null){

                Validationue validationUE = validationueRepository.findByIdUeAndIdCursus(uniteenseignement, cursus).orElse( null);
                if (validationUE == null){
                    Validationue validationue = new Validationue();
                    validationue.setIdUe(uniteenseignement);
                    validationue.setIdCursus(cursus);
                    BigDecimal seuil = BigDecimal.TEN;

                    if (moyenneUE.compareTo(seuil) < 0){
                        for (float n : note) {
                            if (n < 3) {
                                value.clear();
                                value.add((byte) 0);
                                validationue.setValidationUe((byte) 0);
                                break;
                            }else{
                                value.clear();
                                value.add((byte) 2);
                                validationue.setValidationUe((byte) 2);
                            }
                        }
                    } else {

                        for (float n : note) {
                            if (n >= 3) {
                                value.clear();
                                value.add((byte) 1);
                                validationue.setValidationUe((byte) 1);
                            }else{
                                value.clear();
                                value.add((byte) 3);
                                validationue.setValidationUe((byte) 3);
                                break;
                            }

                        }
                    }
                    validationueRepository.save(validationue);

                }else {
                    Validationue validationUETY = validationueRepository.findByIdUeAndIdCursus(uniteenseignement, cursus).orElse( null);
                    validationUETY.setIdUe(uniteenseignement);
                    validationUETY.setIdCursus(cursus);

                    ArrayList<Byte> value1 = new ArrayList<>();
                    BigDecimal seuil = BigDecimal.TEN;

                    if (moyenneUE.compareTo(seuil) < 0){
                        for (float n : note) {
                            if (n < 3) {
                                value1.clear();
                                value1.add((byte) 0);
                                System.out.println("Value 1");
                                System.out.println(value1);
                                validationUETY.setValidationUe((byte) 0);
                                break;
                            }else{
                                value1.clear();
                                value1.add((byte) 2);
                                System.out.println("Value 2 ");
                                System.out.println(value1);
                                validationUETY.setValidationUe((byte) 2);
                                //break;
                            }
                        }
                    } else {

                        for (float n : note) {
                            if (n >= 3) {
                                value.clear();
                                value.add((byte) 1);
                                validationUETY.setValidationUe((byte) 1);
                            }else{
                                value.clear();
                                value.add((byte) 3);
                                validationUETY.setValidationUe((byte) 3);
                                break;
                            }
                        }
                    }
                    validationueRepository.save(validationUETY);
                }

                Validationue validationU = validationueRepository.findByIdUeAndIdCursus(uniteenseignement, cursus).orElse( null);
               dto.add(new ReleveNoteDto(
                        programme.getNomUE().get(0),
                        ueecList,
                        nomECList,
                        noteECList,
                        moyenneUE.floatValue(),
                        credit.floatValue(),
                        validationU.getValidationUe()
                ));
            }
            moyenneUECreditTotal = moyenneUECreditTotal.add(moyenneUECredit);
        }
        return dto;
    }


}
