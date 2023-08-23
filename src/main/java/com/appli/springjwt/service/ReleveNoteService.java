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


    public void updateByEtudiant(Integer id, MoyenneEtudiantDto moyenneEtudiantDtos) {


            Cursus cursus = cursusRepository.findById(moyenneEtudiantDtos.getIdCursus()).orElseThrow();
            UeEc ueEc = ueEcRepository.findById(id).orElseThrow();

            System.out.println("cursus : "+ cursus.getId());
            System.out.println("ueEc : "+ ueEc.getId() );
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
        // List<Relevenote> relevenoteList = relevenoteRepository.findByIdCursusInAndIdPeIn(cursusListDp,idPeList);

        Map<Integer, List<Cursus>> cursusListDpMap = cursusListDp.stream().collect(Collectors.groupingBy(etu -> etu.getIdEtudiant().getId()));
        System.out.println(" SIZE cursusListDpMap = "+ cursusListDpMap.size());
        Integer c = 0;

        Byte codeRedoublement =null;

        for (Map.Entry<Integer, List<Cursus>> cursus : cursusListDpMap.entrySet()) {
            c += 1;
            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
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

    //Méthode qui calcule les moyennes générales des étudiants en fonction de leurs résultats académiques
    public ArrayList<MoyenneGeneraleDto> getNoteDP(Integer id1, Integer id2) {

        // Liste qui stockera les moyennes générales des étudiants
        ArrayList<MoyenneGeneraleDto> etudiantDTOList = new ArrayList<>();

        // Variable qui stockera la moyenne finale
        BigDecimal moyenneFinale = BigDecimal.valueOf(0);

        // Récupération de la liste des cursus associés au programme d'études id1
        List<Cursus> cursusList = cursusRepository.findAllByIdDp(definitionparcourRepository.findById(id1).orElseThrow());

        // Récupération de la liste des programmes d'enseignement associés aux programmes d'études id1 et id2
        List<Programmeenseignement> idPeList = programmeenseignementRepository.findByIdDpIn(Arrays.asList(definitionparcourRepository.findById(id1).orElseThrow(), definitionparcourRepository.findById(id2).orElseThrow()));

        // Récupération de la liste des UeEc associés aux programmes d'enseignement trouvés précédemment
        List<UeEc> ueEcList = idPeList.stream().map(Programmeenseignement::getIdUeEc).collect(Collectors.toList());

        // Récupération de la liste des cursus associés aux programmes d'études id1 et id2
        List<Cursus> cursusListDp = cursusRepository.findByIdDpIn(Arrays.asList(definitionparcourRepository.findById(id1).orElseThrow(), definitionparcourRepository.findById(id2).orElseThrow()));
        // List<Relevenote> relevenoteList = relevenoteRepository.findByIdCursusInAndIdPeIn(cursusListDp,idPeList);

        // Création d'une map pour grouper les cursus par identifiant d'étudiant
        Map<Integer, List<Cursus>> cursusListDpMap = cursusListDp.stream().collect(Collectors.groupingBy(etu -> etu.getIdEtudiant().getId()));
        System.out.println(" SIZE cursusListDpMap = "+ cursusListDpMap.size());
        Integer c = 0;

        Byte codeRedoublement =null;

        // Boucle pour traiter chaque étudiant
        for (Map.Entry<Integer, List<Cursus>> cursus : cursusListDpMap.entrySet()) {
            c += 1;
            System.out.println("c:" + c);

            // Récupération de l'objet étudiant à partir de son identifiant
            Etudiant etudiant = etudiantRepository.findById(cursus.getKey()).orElse(null);
            System.out.println("Nom Etudiant : " + etudiant.getIdPersonne().getNom());
            System.out.println(cursusList);

            //Todo ici
            Cursus cursus1 = cursusRepository.findByIdEtudiantAndIdDp(
                    etudiantRepository.findById(etudiant.getId()).orElseThrow(() -> new NoSuchElementException("Cursus non trouvé")),
                    definitionparcourRepository.findById(id1).orElseThrow(() -> new NoSuchElementException("Cursus non trouvé"))).orElseThrow(() -> new NoSuchElementException("Cursus non trouvé"));
            System.out.println("CURSUS 1 : " + cursus1);

            // Récupération de l'objet Resultatfinau associé à l'étudiant et au programme d'études id2
            Resultatfinau resultatfinau = resultatfinauRepository.findByIdEtudiantAndIdDp(
                    etudiant,
                    definitionparcourRepository.findById(id2).orElse(null)).orElse(null);

        // Validationue validationUE = (Validationue) validationueRepository.findByIdCursus( cursus).orElse( null);
            System.out.println("TESTE Recuperation validation");
         //   System.out.println(validationUE.getValidationUe());
            System.out.println("*******************************************************");

            // Vérification si l'objet Resultatfinau est trouvé
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
                    System.out.println("note " + i + " : " + releveNote.getNote());
                    System.out.println("Credit " + i + " : " + releveNote.getIdUeEc().getCreditEc());
                    System.out.println("Coef " + i + " : " + releveNote.getIdUeEc().getCoefficientEc());
                    System.out.println("Note x Credit : " + releveNote.getNote().multiply(releveNote.getIdUeEc().getCreditEc()));

                    // Programmeenseignement programmeEnseignement = programmeenseignementRepository.findById(releveNote.getIdPe().getId()).orElse(null);
                    // UeEc ue_ec = ueEcRepository.findById(programmeEnseignement.getIdUeEc().getId()).orElse(null);

                    // System.out.println("ue_ec avec EC = "+ ue_ec.getIdEc() + " dont credit : "+ue_ec.getCreditEc()+" et UE = "+ ue_ec.getIdUe().getNomUe());
                    System.out.println("idPE : " + releveNote.getIdUeEc().getId() + " ,idUe_ec : " + releveNote.getIdUeEc().getId());
                    System.out.println("Credit de idEc: " + releveNote.getIdUeEc().getIdEc().getId() + " est creditEc= " + releveNote.getIdUeEc().getCreditEc());

                    moyenneGenerale = moyenneGenerale.add(releveNote.getNote().multiply(releveNote.getIdUeEc().getCreditEc()));
                    System.out.println("Somme des notes avec credit : " + moyenneGenerale);
                    credit = credit.add(releveNote.getIdUeEc().getCreditEc());
                    System.out.println("Somme des credit : " + credit);
                }
            }
            if (credit.floatValue() == 0 || moyenneGenerale.floatValue() == 0){
                System.out.println("zero be");
                System.out.println("credit pour etudiant no "+ credit);
                System.out.println("TOTAL NOTE : "+ moyenneGenerale);

                moyenneGenerale = BigDecimal.valueOf(0);
                System.out.println(" MOYENNE =" + moyenneGenerale);

            }else {
                System.out.println("credit pour etudiant : "+ credit);
                System.out.println("TOTAL NOTE : "+ moyenneGenerale);

                moyenneGenerale = moyenneGenerale.divide(credit,new MathContext(3));
                System.out.println(" MOYENNE =" + moyenneGenerale);
            }

            // Eto ilay mety fa nasina condution
            /*System.out.println("credit pour etudiant no "+ credit);
            System.out.println("TOTAL NOTE : "+ moyenneGenerale);
            moyenneGenerale = moyenneGenerale.divide(credit,new MathContext(3));
            System.out.println(" MOYENNE =" + moyenneGenerale);
            */
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


    public MoyenneGeneraleDto modifierCodeRedoubleme(Integer idEtudiant, Integer id1, Integer id2, MoyenneEtudiantDto moyenneEtudiantDtos) {

        System.out.println(  " Code de redoublement : "+ moyenneEtudiantDtos.getCodeRedoublement());

        Etudiant etudiant = etudiantRepository.findById(idEtudiant).orElse(null);

        if (etudiant == null){
            return null;
        }else {
            // Récupération de l'objet Resultatfinau associé à l'étudiant et au programme d'études id2
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
        System.out.println("******************************************************************");
        System.out.println("DONNEES RECUS  : " + idEtudiant + "  et  "+ idDp1);
        List<ProgrammeGetDto> pe1 = programmeService.getByIdDp(idDp1);
       // System.out.println("ici R1");
       // System.out.println(pe1);
        ArrayList<ReleveNoteDto> dto = new ArrayList<>();
       // System.out.println("ici R2");
      //  System.out.println(dto);
        //System.out.println("ici R3");
        //System.out.println(etudiantRepository.findById(idEtudiant));
        //System.out.println("ici R4");
        //System.out.println(definitionparcourRepository.findById(idDp1));
        //System.out.println("ici R5");

        Cursus cursus = cursusRepository.findByIdEtudiantAndIdDp(
                etudiantRepository.findById(idEtudiant).orElseThrow(() -> new NoSuchElementException("Cursus non trouvé")),
                definitionparcourRepository.findById(idDp1).orElseThrow(() -> new NoSuchElementException("Cursus non trouvé"))).orElseThrow(() -> new NoSuchElementException("Cursus non trouvé"));
        //System.out.println("ici R6");
        //System.out.println( " ID Cursus : " +cursus.getId());
        //System.out.println(" ID Etudiant : " + cursus.getIdEtudiant());

        BigDecimal moyenneUECredit = BigDecimal.valueOf(0);
        BigDecimal moyenneUECreditTotal = BigDecimal.valueOf(0);
        BigDecimal CreditTotal = BigDecimal.valueOf(0);
        for(ProgrammeGetDto programme : pe1){
            System.out.println("============================================================================");
          //  System.out.println("ici R7");
            //System.out.println("Programme " + programme);

            ArrayList<Float> noteECList = new ArrayList<>();
            ArrayList<String> nomECList = new ArrayList<>();
            BigDecimal moyenneUE = BigDecimal.valueOf(0);
            BigDecimal credit = BigDecimal.valueOf(0);

            //System.out.println("ici R8");
            ArrayList<Integer> ueecList = programme.getIdUEEC();
            //System.out.println("UE et EC Liste : " + ueecList);
            //System.out.println("ici R9");
            for(Integer idUEEC:ueecList){
              //  System.out.println("ID CURSUS : " + cursus.getId());
                //System.out.println("idUEEC" + idUEEC);
                //System.out.println("ici R10");
                Relevenote releve = relevenoteRepository.findByIdCursusAndIdUeEc(
                        cursus,
                        ueEcRepository.findById(idUEEC).orElseThrow()
                ).orElse(null);
                if (releve != null){
                    noteECList.add(releve.getNote().floatValue());
                  //  System.out.println(noteECList);
                    //System.out.println("ici R11");
                }
            }

            //System.out.println(programme.getNomUE());
            for( ElementConstitutifDto nomEC: programme.getNomEC()){
                nomECList.add(nomEC.getNomEC());
              //  System.out.println(nomECList);
               // System.out.println("ici R12");
            }
          //  System.out.println("--------------------------------------");
          //  System.out.println("ueecList" + ueecList);
            //System.out.println(ueEcRepository.findByIdIn(ueecList));
            List<Relevenote> releveNoteList = relevenoteRepository.findByIdCursusAndIdUeEcIn(cursus,ueEcRepository.findByIdIn(ueecList));

          //  System.out.println("RELEVE DE NOTE LISTE : " + releveNoteList);
          //  System.out.println("ici R13");

            ArrayList<Float> note = new ArrayList<>();
            ArrayList<Byte> value = new ArrayList<>();

            if (releveNoteList != null){

                for (Relevenote releveNote : releveNoteList) {
                    System.out.println("*********************************************");
                    System.out.println("Chaque RELEVE DE NOTE : " + releveNote);
                  //  System.out.println("ici R14");
                    System.out.println("NOTE : " + releveNote.getNote());
                    System.out.println( "MATIERE :  "+releveNote.getIdUeEc().getIdEc().getNomEc());

                    note.add(releveNote.getNote().floatValue());
                   // System.out.println("SOMME Des Notes : " + note);
                    System.out.println("CREDIT : " + releveNote.getIdUeEc().getCreditEc());
                    System.out.println(" NOTE x CREDIT :  " + releveNote.getNote().multiply(releveNote.getIdUeEc().getCreditEc()));

                    moyenneUE = moyenneUE.add(releveNote.getNote().multiply(releveNote.getIdUeEc().getCreditEc()));
                    System.out.println("moyenneUE somme note x credit :  " + moyenneUE );
                    System.out.println("ici R15");

                    credit = credit.add(releveNote.getIdUeEc().getCreditEc());
                    System.out.println("SOMME CREDIT : " + credit);
                    System.out.println("ici R16");
                   /* if (credit.floatValue() == 0){
                        releveNote.setNote(BigDecimal.valueOf(0));
                        relevenoteRepository.save(releveNote);
                    }*/
                }

                if (credit.floatValue() == 0){
                    moyenneUE = BigDecimal.valueOf(0);
                }else {
                    moyenneUE = moyenneUE.divide(credit,new MathContext(3));
                    System.out.println("moyenneUE diviser par somme credit :  " + moyenneUE );
                    System.out.println("ici R17");
                    System.out.println("moyenneUE x credit :  " + moyenneUE +"x " + credit + " = " + moyenneUE.multiply(credit));
                    System.out.println("moyenneUE somme :  " + moyenneUECredit +" + " + moyenneUE.multiply(credit) + " = " + moyenneUECredit.add(moyenneUE.multiply(credit)));

                    moyenneUECredit = moyenneUECredit.add(moyenneUE.multiply(credit));
                    System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
                    System.out.println("SOMME MOYENNE UE x Credit : " + moyenneUECredit);

                    CreditTotal = CreditTotal.add(credit);
                    System.out.println("Credit TOTAL " + CreditTotal);

                }
            /*    moyenneUECreditTotal= moyenneUECreditTotal.add(moyenneUECredit);
            System.out.println("SOMME MOYENNE UE x Credit TOTAL : " + moyenneUECreditTotal);
                System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");*/

            }else {
                System.out.println(" NULL NULL NULL NULL NULL NULL NULL v NULl v v NULL NULL NULL NULL NULL NULL");

            }
            /*moyenneUECreditTotal= moyenneUECreditTotal.add(moyenneUECredit);
            System.out.println("SOMME MOYENNE UE x Credit TOTAL : " + moyenneUECreditTotal);*/

            Uniteenseignement uniteenseignement = uniteenseignementRepository.findById(programme.getIdUE().get(0)).orElse(null);
            //System.out.println(uniteenseignement);
            //System.out.println("éto zahay");
            if (uniteenseignement !=null){
              //  System.out.println("ici R18");
                //System.out.println("uniteenseignement" + uniteenseignement);
                //System.out.println(cursus);
                Validationue validationUE = validationueRepository.findByIdUeAndIdCursus(uniteenseignement, cursus).orElse( null);
                if (validationUE == null){
                  //  System.out.println("null ra ty attt ato mints");
                    Validationue validationue = new Validationue();
                    validationue.setIdUe(uniteenseignement);
                    validationue.setIdCursus(cursus);

                    System.out.println("MOYENNE UE" + moyenneUE);
                    BigDecimal seuil = BigDecimal.TEN;

                    if (moyenneUE.compareTo(seuil) < 0){
                        System.out.println("moyenne ambany 10");
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
                               // break;
                            }
                        }
                       // validationue.setValidationUe((byte) value.get(0));
                    } else {
                        System.out.println("moyenne ambony 10");

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
                      //  validationue.setValidationUe((byte) value.get(0));
                        System.out.println("ok");
                    }
                    validationueRepository.save(validationue);

                }else {
                    Validationue validationUETY = validationueRepository.findByIdUeAndIdCursus(uniteenseignement, cursus).orElse( null);
                    System.out.println(" TSY null ra ty attt ato mints");
                   // Validationue validationue = new Validationue();
                    validationUETY.setIdUe(uniteenseignement);
                    validationUETY.setIdCursus(cursus);

                    ArrayList<Byte> value1 = new ArrayList<>();

                    System.out.println("MOYENNE UE" + moyenneUE);
                    BigDecimal seuil = BigDecimal.TEN;

                    if (moyenneUE.compareTo(seuil) < 0){
                        System.out.println("moyenne ambany 10");
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
                        //validationUETY.setValidationUe((byte) value1.get(0));
                    } else {
                        System.out.println("moyenne ambony 10");

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
                        //validationUETY.setValidationUe((byte) value.get(0));
                        System.out.println("ok");
                    }
                    validationueRepository.save(validationUETY);
                }

                Validationue validationU = validationueRepository.findByIdUeAndIdCursus(uniteenseignement, cursus).orElse( null);
                System.out.println(validationU);
                System.out.println("ici R19");
               dto.add(new ReleveNoteDto(
                        programme.getNomUE().get(0),
                        ueecList,
                        nomECList,
                        noteECList,
                        moyenneUE.floatValue(),
                        credit.floatValue(),
                        validationU.getValidationUe()
                ));
                System.out.println(validationU.getId());
                System.out.println("ici R20");
            }
            System.out.println("ici R21");
           /* moyenneUECredit = moyenneUECredit.add(moyenneUE.multiply(credit));
            System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
            System.out.println("SOMME MOYENNE UE x Credit farany : " + moyenneUECredit);*/
            moyenneUECreditTotal = moyenneUECreditTotal.add(moyenneUECredit);
            System.out.println("TOTAL: " + moyenneUECreditTotal);
        }
        return dto;
    }


}
