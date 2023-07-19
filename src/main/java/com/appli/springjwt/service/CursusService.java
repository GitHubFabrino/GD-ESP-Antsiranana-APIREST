package com.appli.springjwt.service;

import com.appli.springjwt.dto.CursusDto;
import com.appli.springjwt.dto.ReleveNoteDto;
import com.appli.springjwt.models.*;
import com.appli.springjwt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class CursusService {
    @Autowired
    CursusRepository cursusRepository;
    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    DefinitionparcourRepository definitionparcourRepository;
    @Autowired
    ReleveNoteService releveNoteService;
    @Autowired
    UeEcRepository ueEcRepository;
    @Autowired
    RelevenoteRepository relevenoteRepository;

    @Autowired
    ProgrammeenseignementRepository programmeenseignementRepository;
    @Autowired
    ProgrammeService programmeService;

@Transactional
    public void save(CursusDto cursusDto) {

        Etudiant etudiant = etudiantRepository.findById(cursusDto.getIdEtudiant()).orElseThrow();
        etudiant.getIdPersonne().setTelephone(cursusDto.getTelephone());
        etudiant.getIdPersonne().setEmail(cursusDto.getEmail());
        etudiantRepository.save(etudiant);

        List<Definitionparcour> iddpList = etudiant.getCursus().stream().collect(Collectors.toList()).subList(0, 1).stream().map(Cursus::getIdDp).collect(Collectors.toList());
        ArrayList<BigDecimal> noteList = new ArrayList<>();

        for(Integer idDP: cursusDto.getIdDP()){
            Cursus cursus = new Cursus();
            Definitionparcour dp = definitionparcourRepository.findById(idDP).orElseThrow();

            if(cursusRepository.existsByIdEtudiantAndIdDp(etudiant,dp)){

            }else {
                if (etudiant.getStatusEtudiant().equalsIgnoreCase("REDOUBLANT")) {

                    for (Definitionparcour idDPLast : iddpList) {
                        ArrayList<ReleveNoteDto> releveList = releveNoteService.getReleveEtudiant(cursusDto.getIdEtudiant(), idDPLast.getId());

                        for (ReleveNoteDto releve : releveList) {
                            if (releve.getValidationUE() == 1 || releve.getValidationUE() == 0) {

                                for (int i = 0; i < releve.getNomEC().size(); i++) {
                                    Integer idUeEC = releve.getIdUEEC().get(i);
                                    UeEc ueEc = ueEcRepository.findById(idUeEC).orElseThrow();
                                    List<UeEc> ueecList = programmeService.findUeEcByDpMap(dp);

                                    if (ueecList.contains(ueEc)) {
                                        Relevenote releveNew = new Relevenote();
                                        releveNew.setIdUeEc(ueEc);
                                        releveNew.setIdCursus(cursus);
                                        releveNew.setNote(BigDecimal.valueOf(releve.getNoteEC().get(i)));

                                        cursusRepository.save(cursus);
                                        relevenoteRepository.save(releveNew);
                                    }
                                        /*
                                    for (UeEc ueecMap : ueecList) {
                                        if (ueecMap.getIdEc().getCodeEc().equalsIgnoreCase(ueEc.getIdEc().getCodeEc())) {
                                            releveNew.setIdUeEc(ueecMap);
                                            releveNew.setIdCursus(cursus);

                                        }
                                    }
                                         */
                                }
                                // Cursus cursus = cursusRepository.findById(dto.getIdCursus()).orElseThrow();
                            }
                        }
                    }
                } else {
                    cursus.setValiditeCurcus(false);
                    cursus.setIdEtudiant(etudiant);
                    cursus.setIdDp(dp);
                    cursusRepository.save(cursus);
                }
            }
        }
    }


    public void update(ArrayList<CursusDto> cursusDto) {
        for(CursusDto dto: cursusDto){
            Cursus cursus = cursusRepository.findById(dto.getId()).orElseThrow();

            cursus.setValiditeIp(dto.getValiditeIP());
            cursusRepository.save(cursus);
        }
    }

    public ArrayList<CursusDto> getById(Integer id) {

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

        return cursusDto;
    }
}
