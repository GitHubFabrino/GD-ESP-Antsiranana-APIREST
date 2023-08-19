package com.appli.springjwt.service;

import com.appli.springjwt.dto.*;
import com.appli.springjwt.models.*;
import com.appli.springjwt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
@Transactional
@Service
public class EtudiantService {
    @Autowired
    CursusRepository cursusRepository;
    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    PersonneRepository personneRepository;
    @Autowired
    DefinitionparcourRepository definitionparcourRepository;
    @Autowired
    ResultatfinauRepository resultatfinauRepository;
    @Autowired
    ProgrammeService programmeService;
    @Autowired
    RelevenoteRepository relevenoteRepository;
    @Autowired
    UeEcRepository ueEcRepository;
    @Autowired
    ValidationueRepository validationueRepository;
    @Autowired
    UniteenseignementRepository uniteenseignementRepository;
    @Autowired
    ReleveNoteService releveNoteService;

    public void creerResultat(Integer id2, DeliberationAUDto deliberationAUDto, Integer id1) {
        List<Integer> id = List.of(id1,id2);
        validationUEGenerale(id,deliberationAUDto);
    }
    private void validationUEGenerale(List<Integer> id, DeliberationAUDto dto) {

        for(ResultatAUDto resultatEtudiantsDto : dto.getListIdEtudiant()){
            ArrayList<Integer> validationUEList = new ArrayList<>();

            Resultatfinau resultat =new Resultatfinau();

            Definitionparcour dp1 = definitionparcourRepository.findById(id.get(0)).orElseThrow();
            Definitionparcour dp2 = definitionparcourRepository.findById(id.get(1)).orElseThrow();

            Etudiant etudiantResultat = etudiantRepository.findById(resultatEtudiantsDto.getIdEtudiant()).orElseThrow();

            resultat.setIdEtudiant(etudiantResultat);
            resultat.setIdDp(definitionparcourRepository.findById(id.get(1)).orElseThrow());

            float moyenne = releveNoteService.getNoteDPEtudiant(resultatEtudiantsDto.getIdEtudiant(), id.get(0), id.get(1)).get(0).getMoyenne().floatValue();

            List<Cursus> cursus = cursusRepository.findByIdEtudiantAndIdDpIn(
                    etudiantResultat,
                        Arrays.asList(dp1, dp2));

            List<ProgrammeGetDto> pe1 = programmeService.getByIdDp(id.get(0));
            List<ProgrammeGetDto> pe2 = programmeService.getByIdDp(id.get(1));

                    Integer pe1Index=0;
                    for(ProgrammeGetDto programme : pe1){
                        System.out.println("PE1 NO :"+ pe1Index);
                        ArrayList<Integer> idUeEcList = programme.getIdUEEC();
                        //ArrayList<Integer> idUeList = programme.getIdUE();
                        // Validationue validationueobj = new Validationue();

                        Uniteenseignement uniteenseignement = uniteenseignementRepository.findById(programme.getIdUE().get(0)).orElseThrow();
                        ArrayList<Integer> validationEC = new ArrayList<>();

                        BigDecimal moyenneUE = BigDecimal.ZERO;
                        BigDecimal totalCredit = BigDecimal.ZERO;

                        for(Integer ueec:idUeEcList){
                            BigDecimal note = relevenoteRepository.findByIdCursusAndIdUeEc(cursus.get(0), ueEcRepository.findById(ueec).orElseThrow()).orElseThrow().getNote();
                            System.out.println("NOTE ="+ note.floatValue()+" ECE = "+dto.getEce());
                            if(note.floatValue() < dto.getEce()){
                                validationEC.add(0);
                            } else {
                                validationEC.add(1);
                            }
                            BigDecimal creditEC = ueEcRepository.findById(ueec).orElseThrow().getCreditEc();
                            if (note == null || creditEC == null) {
                                continue;
                            }
                            moyenneUE = moyenneUE.add(note.multiply(creditEC));
                            totalCredit = totalCredit.add(creditEC);
                        }
                        System.out.println("VALIDATION EC SIZE = "+ validationEC.size());

                        if (totalCredit.compareTo(BigDecimal.ZERO) != 0) {
                            moyenneUE = moyenneUE.divide(totalCredit, 2, RoundingMode.HALF_UP);
                        }
                    //
                        //System.out.println("VALIDATION UE NO "+ programme.getIdUE().get(0)+" = "+ validationUEList.get(pe1Index));
                        if(validationueRepository.existsByIdUeAndIdCursus(uniteenseignement,cursus.get(0))){
                            Validationue validationSave = validationueRepository.findByIdUeAndIdCursus(uniteenseignement,cursus.get(0)).orElseThrow();

                                if(validationEC.contains(0)){
                                    validationUEList.add(0);
                                    validationSave.setValidationUe((byte) 0);
                                } else if (moyenneUE.floatValue()>= 10) {
                                    validationUEList.add(1);
                                    validationSave.setValidationUe((byte) 1);
                                } else if (moyenneUE.floatValue()>= dto.getUec()) {
                                    validationUEList.add(2);
                                    validationSave.setValidationUe((byte) 2);
                                } else{
                                    validationUEList.add(0);
                                    validationSave.setValidationUe((byte) 0);
                                }
                            validationueRepository.save(validationSave);
                        } else {
                            Validationue validationSave = new Validationue();
                            if(validationEC.contains(0)){
                                validationUEList.add(0);
                                validationSave.setValidationUe((byte) 0);
                            } else if (moyenneUE.floatValue()>= 10) {
                                validationUEList.add(1);
                                validationSave.setValidationUe((byte) 1);
                            } else if (moyenneUE.floatValue()>= dto.getUec()) {
                                validationUEList.add(2);
                                validationSave.setValidationUe((byte) 2);
                            } else{
                                validationUEList.add(0);
                                validationSave.setValidationUe((byte) 0);
                            }
                            validationSave.setIdUe(uniteenseignement);
                            validationSave.setIdCursus(cursus.get(0));
                            validationueRepository.save(validationSave);
                        }
                        pe1Index+=1;
                    }

                    for(ProgrammeGetDto programme : pe2){
                        ArrayList<Integer> idUeEcList = programme.getIdUEEC();
                        //ArrayList<Integer> idUeList = programme.getIdUE();

                        Uniteenseignement uniteenseignement = uniteenseignementRepository.findById(programme.getIdUE().get(0)).orElseThrow();
                        ArrayList<Integer> validationEC = new ArrayList<>();

                        BigDecimal moyenneUE = BigDecimal.ZERO;
                        BigDecimal totalCredit = BigDecimal.ZERO;

                        for(Integer ueec:idUeEcList){
                            BigDecimal note = relevenoteRepository.findByIdCursusAndIdUeEc(cursus.get(1), ueEcRepository.findById(ueec).orElseThrow()).orElseThrow().getNote();
                            System.out.println("NOTE ="+ note.floatValue()+" ECE = "+dto.getEce());
                            if(note.floatValue() < dto.getEce()){
                                validationEC.add(0);
                            } else {
                                validationEC.add(1);
                            }
                            BigDecimal creditEC = ueEcRepository.findById(ueec).orElseThrow().getCreditEc();
                            if (note == null || creditEC == null) {
                                continue;
                            }
                            moyenneUE = moyenneUE.add(note.multiply(creditEC));
                            totalCredit = totalCredit.add(creditEC);
                        }

                        if (totalCredit.compareTo(BigDecimal.ZERO) != 0) {
                            moyenneUE = moyenneUE.divide(totalCredit, 2, RoundingMode.HALF_UP);
                        }

                        if(validationueRepository.existsByIdUeAndIdCursus(uniteenseignement,cursus.get(1))){
                            Validationue validationSave = validationueRepository.findByIdUeAndIdCursus(uniteenseignement,cursus.get(1)).orElseThrow();
                            if(validationEC.contains(0)){
                                validationUEList.add(0);
                                validationSave.setValidationUe((byte) 0);
                            } else if (moyenneUE.floatValue()>= 10) {
                                validationUEList.add(1);
                                validationSave.setValidationUe((byte) 1);
                            } else if (moyenneUE.floatValue()>= dto.getUec()) {
                                validationUEList.add(2);
                                validationSave.setValidationUe((byte) 2);
                            } else{
                                validationUEList.add(0);
                                validationSave.setValidationUe((byte) 0);
                            }
                            validationueRepository.save(validationSave);

                        } else {
                            Validationue validationSave = new Validationue();
                            if(validationEC.contains(0)){
                                validationUEList.add(0);
                                validationSave.setValidationUe((byte) 0);
                            } else if (moyenneUE.floatValue()>= 10) {
                                validationUEList.add(1);
                                validationSave.setValidationUe((byte) 1);
                            } else if (moyenneUE.floatValue()>= dto.getUec()) {
                                validationUEList.add(2);
                                validationSave.setValidationUe((byte) 2);
                            } else{
                                validationUEList.add(0);
                                validationSave.setValidationUe((byte) 0);
                            }
                            validationSave.setIdUe(uniteenseignement);
                            validationSave.setIdCursus(cursus.get(1));
                            validationueRepository.save(validationSave);
                        }
                    }

                    for(Integer vUE : validationUEList) {

                        ArrayList<Integer> vUEList1= new ArrayList<>();
                        for(Integer v: validationUEList){
                            vUEList1.add(1);
                        }
                        if(vUEList1.equals(validationUEList)){
                            resultat.setCodeRedoublement((byte) 1); //admis
                        } else {
                            if(validationUEList.contains(0)){
                                if(moyenne>dto.getMgv()){
                                    resultat.setCodeRedoublement((byte) 3); //admis par condition
                                }else{
                                    resultat.setCodeRedoublement((byte) 4); //redoublant
                                }
                            } else if (!validationUEList.contains(0) && validationUEList.contains(2)) {
                                if(moyenne> dto.getMgv()){
                                    resultat.setCodeRedoublement((byte) 2); // admis par compensation
                                }else {
                                    resultat.setCodeRedoublement((byte) 4); //redoublant

                                }
                            }
                        }
                    }

            if(resultatfinauRepository.existsByIdEtudiantAndIdDp(resultat.getIdEtudiant(),definitionparcourRepository.findById(id.get(1)).orElseThrow())){
                Resultatfinau resultatfinau = resultatfinauRepository.findByIdEtudiantAndIdDp(resultat.getIdEtudiant(),definitionparcourRepository.findById(id.get(1)).orElseThrow()).orElseThrow();
                resultatfinau.setCodeRedoublement(resultat.getCodeRedoublement());
                Etudiant etudiant = resultat.getIdEtudiant();
                if(resultat.getCodeRedoublement()==4){
                    etudiant.setStatusEtudiant("REDOUBLANT");
                }else{
                    etudiant.setStatusEtudiant("PASSANT");
                }
                etudiantRepository.save(etudiant);
                resultatfinauRepository.save(resultatfinau);
            } else {
                Resultatfinau resultatfinau = new Resultatfinau(resultat.getIdEtudiant(),definitionparcourRepository.findById(id.get(1)).orElseThrow(),resultat.getCodeRedoublement());
                resultatfinauRepository.save(resultatfinau);
            }

            Resultatfinau etudiantRedoublant = resultatfinauRepository.findByIdEtudiantAndIdDp(etudiantResultat, dp2).orElseThrow();
            if(etudiantRedoublant.getCodeRedoublement() == 4){
                List<Uniteenseignement> ueList = uniteenseignementRepository.findByIdDpIn(Arrays.asList(dp1, dp2));
                System.out.println("????????????????????????????????????????????????????????????????????????");
                System.out.println("Nombre ue " + ueList.size());
                System.out.println("????????????????????????????????????????????????????????????????????????");

                for( Uniteenseignement ue:ueList){
                  //  System.out.println("NOM UE : " + ue.getNomUe());

                    for(Cursus c:cursus) {
                     //   System.out.println("NOM Cursus : " + c.getId());
                        Validationue validationue = validationueRepository.findByIdUeAndIdCursus(ue, c).orElse(null);
                        if(validationue == null){}
                        else{
                       //     System.out.println("VALIDATION : " + validationue);
                            //Todo eto ilay misy olana str misy valeur null anatiny bd table validationue
                            if (validationue.getValidationUe() == null){
                                System.out.println("Null be ato ayyy");
                            }else{
                                if (validationue.getValidationUe() == 2 ){
                                    validationue.setValidationUe((byte) 0);
                                    validationueRepository.save(validationue);
                                }

                            }
                        }
                    }
                }
            }

        }

    }

    private void validationUE(Integer id, List<Integer> listdp, DeliberationAUDto dto) {

        Definitionparcour dp = definitionparcourRepository.findById(id).orElseThrow();
        ArrayList<Cursus> cursus = cursusRepository.findAllByIdDp(dp);

        List<ProgrammeGetDto> pe = programmeService.getByIdDp(id);

        String niveau = dp.getIdNiveau().getNiveau();
        BigDecimal noteEliminatoire = BigDecimal.valueOf(0);
        if(niveau == "L1" | niveau == "L2" |niveau == "L3"){
            noteEliminatoire=BigDecimal.valueOf(3);
        }
        if(niveau == "M1" | niveau == "M2" ){
            noteEliminatoire=BigDecimal.valueOf(5);
        }

        for(Cursus c:cursus){
            float moyenne = releveNoteService.getNoteDPEtudiant(c.getIdEtudiant().getId(), listdp.get(0), listdp.get(1)).get(0).getMoyenne().floatValue();
            ArrayList<Integer> validationUE= new ArrayList<>();

            for(ProgrammeGetDto programme:pe){

                ArrayList<Integer> idUeEcList = programme.getIdUEEC();
                ArrayList<Integer> idUeList = programme.getIdUE();

                Validationue validationueobj = new Validationue();
                ArrayList<Integer> validationEC = new ArrayList<>();

                for(Integer ueec:idUeEcList){

                    BigDecimal note = relevenoteRepository.findByIdCursusAndIdUeEc(c, ueEcRepository.findById(ueec).orElseThrow()).orElseThrow().getNote();
                    if(note.floatValue()<dto.getEce()){
                        validationEC.add(0);
                    } else if (dto.getEce()>= note.floatValue() && note.floatValue()>dto.getEcv()) {
                        validationEC.add(2);
                    } else if (note.floatValue()>dto.getEcv()) {
                        validationEC.add(1);
                    }
                }

                if(validationEC.contains(0)){
                    validationUE.add(0);
                } else{
                    ArrayList<Integer> vECList1= new ArrayList<>();
                    for(Integer v:validationEC){
                        vECList1.add(1);
                    }
                    if(vECList1.equals(validationEC)){
                         validationUE.add(1);
                    } else{
                        if(moyenne>dto.getMgv()){
                            validationUE.add(2);
                        }else{
                            validationUE.add(0);
                        }
                    }
                }

                /*
                validationUE.add(0);
                validationueobj.setIdCursus(c);

                for(Integer idue:idUeList){
                    validationueobj.setIdUe(uniteenseignementRepository.findById(idue).orElseThrow());

                    if(validationueRepository.existsByIdUeAndIdCursus(uniteenseignementRepository.findById(idue).orElseThrow(),c)){
                        Validationue validation = validationueRepository.findByIdUeAndIdCursus(uniteenseignementRepository.findById(idue).orElseThrow(),c).orElseThrow();
                        validationueobj.setValidationUe(validationUE);
                        validationueRepository.save(validation);
                    }else {
                        validationueobj.setValidationUe(validationUE);
                        validationueRepository.save(validationueobj);
                    }
                }
*/
            }
        }
    }

    public EtudiantDto getById(Integer id) {
        EtudiantDto etudiantDto = new EtudiantDto();
        try {
            Etudiant etudiant = etudiantRepository.findByIdPersonne(personneRepository.findById(id).orElseThrow()).orElseThrow();

            etudiantDto.setId(etudiant.getId());
            etudiantDto.setNumeroMatricule(etudiant.getNumeroMatricule());
            etudiantDto.setAnneeBacc(etudiant.getAnneeBacc());
            etudiantDto.setStatusEtudiant(etudiant.getStatusEtudiant());
            etudiantDto.setIdBacc(etudiant.getIdBacc().getId());

        } catch (Exception e) {
            return null;
        }
        return  etudiantDto;
    }

    public ArrayList<CursusDto> getByIdDP(Integer id) {
        ArrayList<CursusDto> cursusDto = new ArrayList<>();
        ArrayList<Cursus> cursus = cursusRepository.findAllByIdDp(definitionparcourRepository.findById(id).orElseThrow());

        for(Cursus c: cursus) {
            Integer i = 0;
            cursusDto.add(i,new CursusDto(
                    c.getId(),
                    c.getValiditeIp(),
                    c.getIdEtudiant().getIdPersonne().getNom(),
                    c.getIdEtudiant().getIdPersonne().getPrenoms(),
                    c.getIdEtudiant().getIdPersonne().getTelephone(),
                    c.getIdEtudiant().getIdPersonne().getEmail()

            ));
            i+=1;
        }
        Collections.reverse(cursusDto);
        return cursusDto;

    }

    public ArrayList<CursusDto> get(Integer id) {
        ArrayList<CursusDto> cursusDto = new ArrayList<>();
        ArrayList<Cursus> cursus = cursusRepository.findAllByIdDpAndValiditeIp(definitionparcourRepository.findById(id).orElseThrow(),true);
        
        for(Cursus c: cursus) {
            //Set<Cursus> cursusList = c.getIdEtudiant().getCursus();
            //cursusList = cursusRepository.find
            Integer i = 0;
            cursusDto.add(i,new CursusDto(
                    c.getId(),
                    c.getValiditeIp(),
                    c.getIdEtudiant().getId(),
                    c.getIdEtudiant().getIdPersonne().getNom(),
                    c.getIdEtudiant().getIdPersonne().getPrenoms(),
                    c.getIdEtudiant().getIdPersonne().getTelephone(),
                    c.getIdEtudiant().getIdPersonne().getEmail()
            ));
            i+=1;
        }
        Collections.reverse(cursusDto);
        return cursusDto;
    }


    public ArrayList<ResultatAUDto> getResultatByIdDP(Integer id) {

        ArrayList<ResultatAUDto> resultatAUDtos = new ArrayList<>();
        List<Resultatfinau> resultatfinaus = resultatfinauRepository.findAllByIdDp(definitionparcourRepository.findById(id).orElseThrow());

        for(Resultatfinau r: resultatfinaus) {
            Integer i = 0;
            resultatAUDtos.add(i,new ResultatAUDto(
                    r.getIdEtudiant().getId(),
                    r.getIdEtudiant().getIdPersonne().getId(),
                    r.getIdEtudiant().getIdPersonne().getNom(),
                    r.getIdEtudiant().getIdPersonne().getPrenoms(),
                    r.getCodeRedoublement()
            ));
            i+=1;
        }
        Collections.reverse(resultatAUDtos);
        return resultatAUDtos;

    }
    public AttestationDto getAttestation(Integer idPersonne,Integer idDp) {
        Personne personne = personneRepository.findById(idPersonne).orElseThrow();
        Definitionparcour dp = definitionparcourRepository.findById(idDp).orElseThrow();

        AttestationDto attestationDto = new AttestationDto();
        MentionDto mentionDto = new MentionDto();
        PersonneDto personneDto = new PersonneDto();

        mentionDto.setMention(dp.getIdDm().getIdMention().getMention());
        mentionDto.setNom(dp.getIdDm().getIdEnseignant().getIdPersonne().getNom());
        mentionDto.setPrenoms(dp.getIdDm().getIdEnseignant().getIdPersonne().getPrenoms());
        mentionDto.setSexe(dp.getIdDm().getIdEnseignant().getIdPersonne().getSexe());

        personneDto.setDateNaissance(personne.getDateNaissance());
        personneDto.setLieuNaissance(personne.getLieuNaissance());
        personneDto.setNumeroCIN(personne.getNumeroCIN());
        personneDto.setSexe(personne.getSexe());
        
        attestationDto.setMention(mentionDto);
        attestationDto.setParcours(dp.getIdParcours().getParcours());
        attestationDto.setPersonne(personneDto);
        attestationDto.setAnneeFin(dp.getIdAu().getFinAU().getYear());

        return attestationDto;
    }

}
