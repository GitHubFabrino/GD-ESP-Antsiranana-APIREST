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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProgrammeService {
    @Autowired
    ProgrammeenseignementRepository programmeenseignementRepository;
    @Autowired
    DefinitionparcourRepository definitionparcourRepository;
    @Autowired
    UeEcRepository ueEcRepository;
    @Autowired
    EnseignantRepository enseignantRepository;
    @Autowired
    UniteenseignementRepository uniteenseignementRepository;
    @Autowired
    ElementconstitutifRepository elementconstitutifRepository;

 @Transactional
    public void save(ProgrammeEnseignementDto programmeDto, Integer idDp) {

    Uniteenseignement uniteenseignement = new Uniteenseignement();

    BigDecimal creditUE = BigDecimal.valueOf(0);

    ArrayList<UniteEnseignementDto> eclist = programmeDto.getUe();

Integer i=0;

        for (UniteEnseignementDto ue : programmeDto.getUe()) {

            ArrayList<ElementConstitutifDto> ecList = programmeDto.getEc();
            BigDecimal sumCredits = ecList.stream().map(ElementConstitutifDto::getCreditEC).reduce(BigDecimal.ZERO, BigDecimal::add);

            if (uniteenseignementRepository.existsByCodeUe(ue.getCodeUE())) {
                uniteenseignement = uniteenseignementRepository.findByCodeUe(ue.getCodeUE()).orElseThrow();
                uniteenseignement.setIdEnseignant(enseignantRepository.findById(ue.getIdEnseignant()).orElseThrow());
                uniteenseignement.setIdDp(definitionparcourRepository.findById(idDp).orElseThrow());
                uniteenseignement.setCreditUe(sumCredits);
                uniteenseignementRepository.save(uniteenseignement);
            } else {
                uniteenseignement.setCodeUe(ue.getCodeUE());
                uniteenseignement.setCreditUe(sumCredits);
                uniteenseignement.setNomUe(ue.getNomUE());
                uniteenseignement.setIdEnseignant(enseignantRepository.findById(ue.getIdEnseignant()).orElseThrow());
                uniteenseignement.setIdDp(definitionparcourRepository.findById(idDp).orElseThrow());
                uniteenseignementRepository.save(uniteenseignement);
            }

            for (ElementConstitutifDto ec : programmeDto.getEc()) {

                Elementconstitutif elementconstitutif = new Elementconstitutif();
                i+=1;

                if (elementconstitutifRepository.existsByCodeEc(ec.getCodeEC())) {
                    elementconstitutif = elementconstitutifRepository.findByCodeEc(ec.getCodeEC()).orElseThrow();
                } else {

                    elementconstitutif.setNomEc(ec.getNomEC());
                    elementconstitutif.setCodeEc(ec.getCodeEC());

                elementconstitutifRepository.save(elementconstitutif);
            }

                creditUE = creditUE.add(ec.getCreditEC());

                // A chaque EC

                UeEc associerUeEc = new UeEc();

        if (ueEcRepository.existsByIdEcAndIdUe(
                elementconstitutifRepository.findById(elementconstitutif.getId()).orElseThrow(),
                uniteenseignementRepository.findById(uniteenseignement.getId()).orElseThrow()
        )) {
            associerUeEc = ueEcRepository.findByIdEcAndIdUe(
                    elementconstitutifRepository.findById(elementconstitutif.getId()).orElseThrow(),
                    uniteenseignementRepository.findById(uniteenseignement.getId()).orElseThrow()
            ).orElseThrow();

            associerUeEc.setIdEnseignant(enseignantRepository.findById(ec.getIdEnseignant()).orElseThrow());
            associerUeEc.setIdEc(elementconstitutif);
            associerUeEc.setIdUe(uniteenseignement);
            associerUeEc.setCreditEc(ec.getCreditEC());
            associerUeEc.setVolumeHoraireEd(ec.getVolumeHoraireED());
            associerUeEc.setVolumeHoraireEt(ec.getVolumeHoraireET());
            associerUeEc.setVolumeHoraireTp(ec.getVolumeHoraireTP());
            associerUeEc.setVolumeHoraireAutre(ec.getVolumeHoraireAutre());

            associerUeEc.setVolumeHoraireTotal(ec.getVolumeHoraireTotal());
                    ueEcRepository.save(associerUeEc);
        } else {

                associerUeEc.setIdEnseignant(enseignantRepository.findById(ec.getIdEnseignant()).orElseThrow());
                associerUeEc.setIdEc(elementconstitutif);
                associerUeEc.setIdUe(uniteenseignement);
                associerUeEc.setCreditEc(ec.getCreditEC());

                associerUeEc.setVolumeHoraireEd(ec.getVolumeHoraireED());
                associerUeEc.setVolumeHoraireEt(ec.getVolumeHoraireET());
                associerUeEc.setVolumeHoraireTp(ec.getVolumeHoraireTP());
                associerUeEc.setVolumeHoraireAutre(ec.getVolumeHoraireAutre());

                associerUeEc.setVolumeHoraireTotal(ec.getVolumeHoraireTotal());

                ueEcRepository.save(associerUeEc);

                }

                Programmeenseignement programme = new Programmeenseignement();

                if (programmeenseignementRepository.existsByIdUeEcAndIdDp(
                        associerUeEc,
                        definitionparcourRepository.findById(idDp).orElseThrow()
                )) {
                    } else {
                    programme.setIdDp(definitionparcourRepository.findById(idDp).orElseThrow());
                    programme.setIdUeEc(associerUeEc);

                    programmeenseignementRepository.save(programme);
                }
            } //fin boucle ec
        } //fin boucle ue
}
    public ArrayList<ProgrammeDto> get(Integer id) {
        List<Programmeenseignement> programme = programmeenseignementRepository.findAllByIdDp(definitionparcourRepository.findById(id).orElseThrow());
        ArrayList<ProgrammeDto> programmeDtos = new ArrayList<>();

        for(Programmeenseignement p: programme){
            Integer i= 0 ;
            programmeDtos.add(i,new ProgrammeDto(
                    p.getId(),
                    p.getIdDp().getId(),
                    p.getIdUeEc().getId()
            ));
            i+=1;
        }
        return programmeDtos;
    }
    public List<ProgrammeGetDto> getByIdDp(Integer id) {
        List<Programmeenseignement> programme = programmeenseignementRepository.findAllByIdDp(definitionparcourRepository.findById(id).orElseThrow());
        ArrayList<ProgrammeGetDto> programmeDtos = new ArrayList<>();

        List<UeEc> ueecList = findUeEcByDpMap(definitionparcourRepository.findById(id).orElse(null));
        System.out.println("UEEC");

        ArrayList<String> nomUE = new ArrayList<>();
        ArrayList<String> codeUE = new ArrayList<>();
        ArrayList<Integer> idUE = new ArrayList<>();

        Map<Integer, List<UeEc>> ueMap = ueecList.stream().collect(Collectors.groupingBy(ueEc -> ueEc.getIdUe().getId()));
        Integer ueMapCompteur =0;
        for (Map.Entry<Integer, List<UeEc>> entry : ueMap.entrySet()) {
            ArrayList<Integer> idUEEC= new ArrayList<>();
            ArrayList<ElementConstitutifDto> nomEC = new ArrayList<>();
            ArrayList<String> codeEC = new ArrayList<>();
            ArrayList<Integer> volumeHoraireET = new ArrayList<>();
            ArrayList<Integer> volumeHoraireED = new ArrayList<>();
            ArrayList<Integer> volumeHoraireTP = new ArrayList<>();
            ArrayList<BigDecimal> creditEC = new ArrayList<>();
            ArrayList<ResponsableECDto> responsableEC = new ArrayList<>();
            ArrayList<ResponsableECDto> responsableUE = new ArrayList<>();
            Integer idUe = entry.getKey();
            List<UeEc> ueEcsByUe = entry.getValue();

            BigDecimal sumCredits = ueEcsByUe.stream().map(UeEc::getCreditEc).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal sumIds = ueEcsByUe.stream().map(UeEc::getId).map(BigDecimal::new).reduce(BigDecimal.ZERO, BigDecimal::add);

            List<Integer> sumId = ueEcsByUe.stream().map(UeEc::getId).collect(Collectors.toList());

            nomUE.add(uniteenseignementRepository.findById(idUe).orElse(null).getNomUe());
            codeUE.add(uniteenseignementRepository.findById(idUe).orElse(null).getCodeUe());
            idUE.add(idUe);
            ueEcsByUe.stream().map(UeEc::getIdEc).forEach(ec -> {

                nomEC.add(new ElementConstitutifDto(
                        ec.getId(),
                        ec.getCodeEc(),
                        ec.getNomEc()
                ));

            });
            ueEcsByUe.stream().map(UeEc::getIdEc).forEach(ec -> codeEC.add(ec.getCodeEc()));
            ueEcsByUe.stream().map(UeEc::getVolumeHoraireEt).forEach(et ->volumeHoraireET.add(et) );
            ueEcsByUe.stream().map(UeEc::getVolumeHoraireEd).forEach(ed ->volumeHoraireED.add(ed) );
            ueEcsByUe.stream().map(UeEc::getVolumeHoraireTp).forEach(tp ->volumeHoraireTP.add(tp) );

            ueEcsByUe.stream().map(UeEc::getCreditEc).forEach(credit ->creditEC.add(credit) );

            ueEcsByUe.stream().map(UeEc::getIdEnseignant).forEach(enseignant -> {
                if (enseignant==null){
                    responsableEC.add(null);
                }else{
                    responsableEC.add(new ResponsableECDto(
                            enseignant.getId(),
                            enseignant.getIdPersonne().getNom()+" "+ enseignant.getIdPersonne().getPrenoms())
                    );
                }
            });

            sumId.stream().forEach(integer -> {
                idUEEC.add(integer);
                System.out.println("UEEC ID = "+integer);});

            BigDecimal average = BigDecimal.ZERO;
            if (sumCredits.compareTo(BigDecimal.ZERO) != 0) {
                average = sumIds.divide(sumCredits, 2, RoundingMode.HALF_UP);
            }

            ArrayList<String> ueList = new ArrayList<>();
            ueList.add(nomUE.get(ueMapCompteur));

            ArrayList<String> codeUeList = new ArrayList<>();
            codeUeList.add(codeUE.get(ueMapCompteur));

            ArrayList<Integer> idUEList = new ArrayList<>();
            idUEList.add(idUE.get(ueMapCompteur));

            responsableUE.add(new ResponsableECDto(
                    uniteenseignementRepository.findById(idUe).orElse(null).getIdEnseignant().getId(),
                    uniteenseignementRepository.findById(idUe).orElse(null).getIdEnseignant().getIdPersonne().getNom()+" "+
                            uniteenseignementRepository.findById(idUe).orElse(null).getIdEnseignant().getIdPersonne().getPrenoms()
            ));

            programmeDtos.add(new ProgrammeGetDto(
                    idUEList,
                    codeUeList,
                    ueList,
                    idUEEC,
                    nomEC,
                    codeEC,
                    volumeHoraireET,
                    volumeHoraireED,
                    volumeHoraireTP,
                    creditEC,
                    responsableEC,
                    responsableUE
            ));

            ueMapCompteur+=1;
        }

        return programmeDtos;
    }
    public List<UeEc> findUeEcByDpList(Definitionparcour dp) {
        List<Programmeenseignement> programmeenseignements = programmeenseignementRepository.findAllByIdDp(dp);

        List<UeEc> ueEcs = new ArrayList<>();
        for (Programmeenseignement pe : programmeenseignements) {
            ueEcs.add(pe.getIdUeEc());
        }
        return ueEcs;
    }

    public List<UeEc> findUeEcByDpMap(Definitionparcour idDp) {
        List<Programmeenseignement> programmeenseignements = programmeenseignementRepository.findAllByIdDp(idDp);
        List<UeEc> ueEcs = programmeenseignements.stream()
                .map(Programmeenseignement::getIdUeEc)
                .collect(Collectors.toList());
        return ueEcs;

    }

    public UeEc findUeEcByDp(Definitionparcour dp) {
        return programmeenseignementRepository.findAllByIdDp(dp)
                .stream()
                .map(Programmeenseignement::getIdUeEc)
                .findFirst()
                .orElse(null);
    }

    public void delete(Integer id) {
        Programmeenseignement programme = programmeenseignementRepository.findById(id).orElseThrow();
        programmeenseignementRepository.delete(programme);
    }

@Transactional
    public void deleteByCodeEc(String codeEc) {
     elementconstitutifRepository.deleteByCodeEc(codeEc);
    }
}
