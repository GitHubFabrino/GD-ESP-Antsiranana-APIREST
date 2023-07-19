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

            System.out.println("cursus : "+ cursus.getId());
            System.out.println("ueEc : "+ ueEc.getId() );
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
        // List<Relevenote> relevenoteList = relevenoteRepository.findByIdCursusInAndIdPeIn(cursusListDp,idPeList);

        Map<Integer, List<Cursus>> cursusListDpMap = cursusListDp.stream().collect(Collectors.groupingBy(etu -> etu.getIdEtudiant().getId()));
        System.out.println(" SIZE cursusListDpMap = "+ cursusListDpMap.size());
        Integer c = 0;

        Byte codeRedoublement =null;

        for (Map.Entry<Integer, List<Cursus>> cursus : cursusListDpMap.entrySet()) {
            c += 1;
            System.out.println("c:" + c);
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
            System.out.println("cursusByEtudiant SIZE = "+ cursusByEtudiant.size());
            Set<Cursus> cursusSet = etudiant.getCursus();
            BigDecimal moyenneGenerale = BigDecimal.valueOf(0);
            BigDecimal credit = BigDecimal.valueOf(0);

            for (Cursus cs : cursusByEtudiant) {

                List<Relevenote> releveNoteList = relevenoteRepository.findByIdCursus(cursusRepository.findById(cs.getId()).orElse(null));
                //List<Relevenote> releveNoteList = relevenoteRepository.findByIdCursusInAndIdPeIn(cursusListDp, idPeList);
                System.out.println("releveNoteList size = " + releveNoteList.size());
                if (releveNoteList.isEmpty()) {
                    continue;
                }

                Integer i = 0;
                //BigDecimal credit = BigDecimal.valueOf(0);
                for (Relevenote releveNote : releveNoteList) {
                    i += 1;
                    System.out.println("note" + i);

                    // Programmeenseignement programmeEnseignement = programmeenseignementRepository.findById(releveNote.getIdPe().getId()).orElse(null);
                    // UeEc ue_ec = ueEcRepository.findById(programmeEnseignement.getIdUeEc().getId()).orElse(null);

                    // System.out.println("ue_ec avec EC = "+ ue_ec.getIdEc() + " dont credit : "+ue_ec.getCreditEc()+" et UE = "+ ue_ec.getIdUe().getNomUe());
                    System.out.println("idPE : " + releveNote.getIdUeEc().getId() + " ,idUe_ec : " + releveNote.getIdUeEc().getId());
                    System.out.println("Credit de idEc: " + releveNote.getIdUeEc().getIdEc().getId() + " est creditEc= " + releveNote.getIdUeEc().getCreditEc());

                    moyenneGenerale = moyenneGenerale.add(releveNote.getNote().multiply(releveNote.getIdUeEc().getCreditEc()));
                    credit = credit.add(releveNote.getIdUeEc().getCreditEc());

                    /*
                    ueEcList = ueEcRepository.findAllByIdUe(ue);
                    ueEcList.stream().forEach(ueec -> {
                    ueec.getProgrammeenseignements().stream().forEach(pe -> {
                    });
                    });
                     */
                }
            }

            System.out.println("credit pour etudiant no "+ credit);
            System.out.println("TOTAL NOTE : "+ moyenneGenerale);

            moyenneGenerale = moyenneGenerale.divide(credit,new MathContext(3));
            System.out.println(" MOYENNE =" + moyenneGenerale);
            etudiantDTOList.add(new MoyenneGeneraleDto(etudiant.getId(), etudiant.getIdPersonne().getNom(), etudiant.getIdPersonne().getPrenoms(), moyenneGenerale,codeRedoublement));

            /*
            moy.add(moyenneGenerale);
            etu.add(etudiant.getId());
             */
        }
        /*
        for(Integer e:etu) {
            Integer i = 0;
            etudiantDTOList.add(new MoyenneGeneraleDto(e,moy.get(i)));
            i+=1;
        }
         */
        return etudiantDTOList;
    }

    public ArrayList<MoyenneGeneraleDto> getNoteDP(Integer id1, Integer id2) {

        ArrayList<MoyenneGeneraleDto> etudiantDTOList = new ArrayList<>();

        BigDecimal moyenneFinale = BigDecimal.valueOf(0);
        List<Cursus> cursusList = cursusRepository.findAllByIdDp(definitionparcourRepository.findById(id1).orElseThrow());

        List<Programmeenseignement> idPeList = programmeenseignementRepository.findByIdDpIn(Arrays.asList(definitionparcourRepository.findById(id1).orElseThrow(), definitionparcourRepository.findById(id2).orElseThrow()));

        List<UeEc> ueEcList = idPeList.stream().map(Programmeenseignement::getIdUeEc).collect(Collectors.toList());
        List<Cursus> cursusListDp = cursusRepository.findByIdDpIn(Arrays.asList(definitionparcourRepository.findById(id1).orElseThrow(), definitionparcourRepository.findById(id2).orElseThrow()));
        // List<Relevenote> relevenoteList = relevenoteRepository.findByIdCursusInAndIdPeIn(cursusListDp,idPeList);

        Map<Integer, List<Cursus>> cursusListDpMap = cursusListDp.stream().collect(Collectors.groupingBy(etu -> etu.getIdEtudiant().getId()));
        System.out.println(" SIZE cursusListDpMap = "+ cursusListDpMap.size());
        Integer c = 0;

        Byte codeRedoublement =null;

        for (Map.Entry<Integer, List<Cursus>> cursus : cursusListDpMap.entrySet()) {
            c += 1;
            System.out.println("c:" + c);
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
            System.out.println("cursusByEtudiant SIZE = "+ cursusByEtudiant.size());
            Set<Cursus> cursusSet = etudiant.getCursus();
            BigDecimal moyenneGenerale = BigDecimal.valueOf(0);
            BigDecimal credit = BigDecimal.valueOf(0);

            for (Cursus cs : cursusByEtudiant) {

                List<Relevenote> releveNoteList = relevenoteRepository.findByIdCursus(cursusRepository.findById(cs.getId()).orElse(null));
                //List<Relevenote> releveNoteList = relevenoteRepository.findByIdCursusInAndIdPeIn(cursusListDp, idPeList);
                System.out.println("releveNoteList size = " + releveNoteList.size());
                if (releveNoteList.isEmpty()) {
                    continue;
                }

                Integer i = 0;
                //BigDecimal credit = BigDecimal.valueOf(0);
                for (Relevenote releveNote : releveNoteList) {
                    i += 1;
                    System.out.println("note" + i);

                    // Programmeenseignement programmeEnseignement = programmeenseignementRepository.findById(releveNote.getIdPe().getId()).orElse(null);
                    // UeEc ue_ec = ueEcRepository.findById(programmeEnseignement.getIdUeEc().getId()).orElse(null);

                    // System.out.println("ue_ec avec EC = "+ ue_ec.getIdEc() + " dont credit : "+ue_ec.getCreditEc()+" et UE = "+ ue_ec.getIdUe().getNomUe());
                    System.out.println("idPE : " + releveNote.getIdUeEc().getId() + " ,idUe_ec : " + releveNote.getIdUeEc().getId());
                    System.out.println("Credit de idEc: " + releveNote.getIdUeEc().getIdEc().getId() + " est creditEc= " + releveNote.getIdUeEc().getCreditEc());

                    moyenneGenerale = moyenneGenerale.add(releveNote.getNote().multiply(releveNote.getIdUeEc().getCreditEc()));
                    credit = credit.add(releveNote.getIdUeEc().getCreditEc());

                    /*
                    ueEcList = ueEcRepository.findAllByIdUe(ue);
                    ueEcList.stream().forEach(ueec -> {
                    ueec.getProgrammeenseignements().stream().forEach(pe -> {
                    });
                    });
                     */
                }
            }

            System.out.println("credit pour etudiant no "+ credit);
            System.out.println("TOTAL NOTE : "+ moyenneGenerale);

            moyenneGenerale = moyenneGenerale.divide(credit,new MathContext(3));
            System.out.println(" MOYENNE =" + moyenneGenerale);
            etudiantDTOList.add(new MoyenneGeneraleDto(etudiant.getId(), etudiant.getIdPersonne().getNom(), etudiant.getIdPersonne().getPrenoms(), moyenneGenerale,codeRedoublement));

            /*
            moy.add(moyenneGenerale);
            etu.add(etudiant.getId());
             */
        }
        /*
        for(Integer e:etu) {
            Integer i = 0;
            etudiantDTOList.add(new MoyenneGeneraleDto(e,moy.get(i)));
            i+=1;
        }
         */
        return etudiantDTOList;
    }



/*
    public ArrayList<MoyenneGeneraleDto> getMoyenneGenerale(Integer idDP, Integer idDP1) {
        List<Programmeenseignement> idPeList = programmeenseignementRepository.findByIdDpIn(Arrays.asList(idDP, idDP1));
        List<Cursus> cursusList = cursusRepository.findByIdDpIn(Arrays.asList(idDP, idDP1));
        List<Relevenote> relevenoteList = relevenoteRepository.findByIdCursusAndIdPeIn(cursusList, idPeList);
        Map<Integer, List<Relevenote>> etudiantReleveNotesMap = new HashMap<>();
        for (Relevenote releveNote : relevenoteList) {
            Integer etudiantId = releveNote.getId().getIdEtudiant();
            etudiantReleveNotesMap.computeIfAbsent(etudiantId, k -> new ArrayList<>()).add(releveNote);
        }
        List<EtudiantDTO> etudiantDTOList = new ArrayList<>();
        for (Map.Entry<Integer, List<Relevenote>> entry : etudiantReleveNotesMap.entrySet()) {
            Integer etudiantId = entry.getKey();
            List<Relevenote> releveNoteList = entry.getValue();
            BigDecimal moyenneGenerale = calculerMoyenneGenerale(releveNoteList);
            Etudiant etudiant = etudiantRepository.findById(etudiantId).orElse(null);
            if (etudiant != null) {
                EtudiantDTO etudiantDTO = new EtudiantDTO();
                etudiantDTO.setNom(etudiant.getNom());
                etudiantDTO.setPrenoms(etudiant.getPrenoms());
                etudiantDTO.setMoyenneGenerale(moyenneGenerale);
                etudiantDTOList.add(etudiantDTO);
            }
        }
        return etudiantDTOList;

    }

 */

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
                etudiantRepository.findById(idEtudiant).orElseThrow(),
                definitionparcourRepository.findById(idDp1).orElseThrow()).orElseThrow();

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
                ).orElseThrow();
                noteECList.add(releve.getNote().floatValue());
            }

            for( ElementConstitutifDto nomEC: programme.getNomEC()){
                nomECList.add(nomEC.getNomEC());
            }

            List<Relevenote> releveNoteList = relevenoteRepository.findByIdCursusAndIdUeEcIn(cursus,ueEcRepository.findByIdIn(ueecList));
            for (Relevenote releveNote : releveNoteList) {
                moyenneUE = moyenneUE.add(releveNote.getNote().multiply(releveNote.getIdUeEc().getCreditEc()));
                credit = credit.add(releveNote.getIdUeEc().getCreditEc());
            }
            moyenneUE = moyenneUE.divide(credit,new MathContext(3));

            Uniteenseignement uniteenseignement = uniteenseignementRepository.findById(programme.getIdUE().get(0)).orElseThrow();
            Validationue validationUE = validationueRepository.findByIdUeAndIdCursus(uniteenseignement, cursus).orElseThrow();

            dto.add(new ReleveNoteDto(
                    programme.getNomUE().get(0),
                    ueecList,
                    nomECList,
                    noteECList,
                    moyenneUE.floatValue(),
                    credit.floatValue(),
                    validationUE.getValidationUe()
            ));
        }
        return dto;
    }


}
