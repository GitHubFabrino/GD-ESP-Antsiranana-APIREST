package com.appli.springjwt.service;

import com.appli.springjwt.repository.DefinitiondroitRepository;
import com.appli.springjwt.repository.DroitRepository;
import com.appli.springjwt.repository.NiveauRepository;
import com.appli.springjwt.dto.DefinitionDroitDto;
import com.appli.springjwt.models.Anneeuniv;
import com.appli.springjwt.models.Definitiondroit;
import com.appli.springjwt.models.Droit;
import com.appli.springjwt.models.Niveau;
import com.appli.springjwt.repository.AnneeunivRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Transactional
@Service
public class DefinitionDroitService {
    @Autowired
    DefinitiondroitRepository definitiondroitRepository;
    @Autowired
    DroitRepository droitRepository;
    @Autowired
    AnneeunivRepository anneeunivRepository;
    @Autowired
    NiveauRepository niveauRepository;
    public void save(ArrayList<DefinitionDroitDto> dto) {

        for(DefinitionDroitDto definitionDroitDto:dto){

        Definitiondroit definitiondroit = new Definitiondroit();
        Droit droit = new Droit();
        Long montant = definitionDroitDto.getMontant();
        String nomBanque = definitionDroitDto.getNomBanque();
        String numeroCompte = definitionDroitDto.getNumeroCompte();
        Niveau niveau = niveauRepository.findById(definitionDroitDto.getIdNiveau()).orElseThrow();
        Anneeuniv annee = anneeunivRepository.findById(definitionDroitDto.getIdAU()).orElseThrow();
/*
        if (droitRepository.existsByMontantAndNomBanqueAndNumeroCompte(montant, nomBanque, numeroCompte)){
            droit= droitRepository.findByMontantAndNomBanqueAndNumeroCompte(montant, nomBanque, numeroCompte).orElseThrow();

        } else{
            droit.setMontant(montant);
            droit.setNomBanque(nomBanque);
            droit.setNumeroCompte(numeroCompte);
        }

 */
            droit.setMontant(montant);
            droit.setNomBanque(nomBanque);
            droit.setNumeroCompte(numeroCompte);

        definitiondroit.setIdNiveau(niveau);
        definitiondroit.setIdDroit(droit);
        definitiondroit.setIdAU(annee);

        droitRepository.save(droit);

        if(definitiondroitRepository.existsByIdAUAndIdDroitAndIdNiveau(annee,droit,niveau)){

        }else{
            definitiondroitRepository.save(definitiondroit);
        }

        }

    }

    public void update(ArrayList<DefinitionDroitDto> dto) {

        for(DefinitionDroitDto definitionDroitDto: dto){

            Definitiondroit definitiondroit= definitiondroitRepository.findById(definitionDroitDto.getIdDD()).orElseThrow();
            System.out.println(definitionDroitDto.getIdDD());
            System.out.println(definitionDroitDto.getMontant());

            definitiondroit.getIdDroit().setMontant(definitionDroitDto.getMontant());
            definitiondroit.getIdDroit().setNumeroCompte(definitionDroitDto.getNumeroCompte());
            definitiondroit.getIdDroit().setNomBanque(definitionDroitDto.getNomBanque());
            definitiondroitRepository.save(definitiondroit);

        }
    }

    public ArrayList<DefinitionDroitDto> getDefinitionDroit(Integer id) {
        List<Definitiondroit> Objdefinition = definitiondroitRepository.findAllByIdAU(anneeunivRepository.findById(id).orElseThrow());
        ArrayList<DefinitionDroitDto> definitionDroitDtos = new ArrayList<>();
        String nombre = "000";
        DecimalFormat n = new DecimalFormat("000");

        //n.format(20);

        String date = "2022 - 2023";

        String[] dateSplit = date.split(" - ");
        String dateSplit1 = dateSplit[0];
        String dateSplit2= dateSplit[1];
        String datePrefix =  dateSplit1.substring(dateSplit1.length()-2)+dateSplit2.substring(dateSplit2.length()-2);

        System.out.println(  dateSplit1.substring(dateSplit1.length()-2));
        System.out.println( dateSplit2.substring(dateSplit2.length()-2) );
        for(Definitiondroit definition :Objdefinition){
            Integer i = 0;
            definitionDroitDtos.add(i,new DefinitionDroitDto(
                    definition.getId(),
                    definition.getIdNiveau().getId(),
                    definition.getIdNiveau().getNiveau(),
                    definition.getIdDroit().getId(),
                    definition.getIdDroit().getMontant(),
                    definition.getIdDroit().getNomBanque(),
                    definition.getIdDroit().getNumeroCompte(),
                    definition.getIdAU().getId(),
                    definition.getIdAU().getNomAU()
            ));
            i+=1;
        }
        Collections.reverse(definitionDroitDtos);
        return definitionDroitDtos;
    }

    public void delete(Integer id) {
        definitiondroitRepository.deleteById(id);
    }
}
