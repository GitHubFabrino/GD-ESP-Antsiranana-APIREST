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
    ProgrammeService programmeService;

@Transactional
    public void save(CursusDto cursusDto) {

        Etudiant etudiant = etudiantRepository.findById(cursusDto.getIdEtudiant()).orElseThrow();
        etudiant.getIdPersonne().setTelephone(cursusDto.getTelephone());
        etudiant.getIdPersonne().setEmail(cursusDto.getEmail());
        etudiant.setStatusEtudiant(cursusDto.getStatus_etudiant());

        etudiantRepository.save(etudiant);
    List<Definitionparcour> iddpList = new ArrayList<>();
    if (!cursusDto.getIdDP().isEmpty()) {
        for (Integer idDP : cursusDto.getIdDP()) {
            Definitionparcour dp = definitionparcourRepository.findById(idDP).orElseThrow();
            iddpList.add(dp);
        }
    }
    ArrayList<BigDecimal> noteList = new ArrayList<>();
    System.out.println("ici 1");

        for(Integer idDP: cursusDto.getIdDP()){
            Cursus cursus = new Cursus();
            Definitionparcour dp = definitionparcourRepository.findById(idDP).orElseThrow();
            System.out.println("ici 2");

            if(cursusRepository.existsByIdEtudiantAndIdDp(etudiant,dp)){
                System.out.println("ici cursus miexiste");
            }
            else {
                System.out.println("ici 3");
                if (etudiant.getStatusEtudiant().equalsIgnoreCase("REDOUBLANT")) {
                    System.out.println("ici 4");
                    for (Definitionparcour idDPLast : iddpList) {
                        System.out.println(idDPLast.getId());
                        System.out.println("ici 6");

                        ArrayList<ReleveNoteDto> releveList = releveNoteService.getReleveEtudiant(cursusDto.getIdEtudiant(), idDPLast.getId());
                        System.out.println("ici 7");
                        for (ReleveNoteDto releve : releveList) {
                            System.out.println("ici 8");
                            if (releve.getValidationUE() == 1 || releve.getValidationUE() == 0) {
                                System.out.println("ici 9");
                                for (int i = 0; i < releve.getNomEC().size(); i++) {
                                    System.out.println("ici 10");
                                    Integer idUeEC = releve.getIdUEEC().get(i);
                                    UeEc ueEc = ueEcRepository.findById(idUeEC).orElseThrow();
                                    System.out.println("ici 11");
                                    List<UeEc> ueecList = programmeService.findUeEcByDpMap(dp);
                                    System.out.println("ici 12");
                                    if (ueecList.contains(ueEc)) {
                                        Relevenote releveNew = new Relevenote();
                                        releveNew.setIdUeEc(ueEc);
                                        releveNew.setIdCursus(cursus);
                                        releveNew.setNote(BigDecimal.valueOf(releve.getNoteEC().get(i)));
                                        cursusRepository.save(cursus);
                                        relevenoteRepository.save(releveNew);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    System.out.println("ici 5");
                    cursus.setValiditeCurcus(false);
                    cursus.setValiditeIp(true);
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
