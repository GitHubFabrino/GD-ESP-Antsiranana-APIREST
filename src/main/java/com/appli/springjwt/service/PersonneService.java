package com.appli.springjwt.service;

import com.appli.springjwt.dto.PersonneDto;
import com.appli.springjwt.models.Personne;
import com.appli.springjwt.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonneService {
@Autowired
    private PersonneRepository personneRepository;

PersonneDto dto = null;

public Personne creerPersonne(Personne dto){

    Personne personne =new Personne();
    personne.setId(dto.getId());
    personne.setNom(dto.getNom());
    personne.setPrenoms(dto.getPrenoms());
    personne.setSexe(dto.getSexe());
    personne.setDateNaissance(dto.getDateNaissance());
    personne.setLieuNaissance(dto.getLieuNaissance());
    personne.setVilleNaissance(dto.getVilleNaissance());
    personne.setPaysNaissance(dto.getPaysNaissance());
    personne.setNationalite(dto.getNationalite());
    personne.setAdresse(dto.getAdresse());
    personne.setNumeroCIN(dto.getNumeroCIN());
    personne.setDateDelivreCIN(dto.getDateDelivreCIN());
    personne.setVilleDelivreCIN(dto.getVilleDelivreCIN());
    personne.setAffiliation1(dto.getAffiliation1());
    personne.setAffiliation2(dto.getAffiliation2());
    personne.setAnneeEntree(dto.getAnneeEntree());
    personne.setTelephone(dto.getTelephone());

    personne.setEmail(dto.getEmail());

    personneRepository.save(personne);

return null;
}

    public PersonneDto getPersonneById(Integer id){

   Personne personne = personneRepository.findById(id).orElseThrow(); // Recuperation Entité
        try {
            dto = new PersonneDto(  // Valorisation toutes ses Propriétés
                    personne.getId(),
                    personne.getNom(),
                    personne.getPrenoms(),
                    personne.getSexe(),
                    personne.getDateNaissance(),
                    personne.getLieuNaissance(),
                    personne.getVilleNaissance(),
                    personne.getPaysNaissance(),
                    personne.getNationalite(),
                    personne.getAdresse(),
                    personne.getNumeroCIN(),
                    personne.getDateDelivreCIN(),
                    personne.getVilleDelivreCIN(),
                    personne.getAffiliation1(),
                    personne.getAffiliation2(),
                    personne.getAnneeEntree(),
                    personne.getTelephone(),
                    personne.getEmail());
        }catch(Exception e){
            dto = new PersonneDto(  // Valorisation toutes ses Propriétés
                    personne.getId(),
                    personne.getNom(),
                    personne.getPrenoms(),
                    personne.getSexe(),
                    personne.getDateNaissance(),
                    personne.getLieuNaissance(),
                    personne.getVilleNaissance(),
                    personne.getPaysNaissance(),
                    personne.getNationalite(),
                    personne.getAdresse(),
                    personne.getNumeroCIN(),
                    personne.getDateDelivreCIN(),
                    personne.getVilleDelivreCIN(),
                    personne.getAffiliation1(),
                    personne.getAffiliation2(),
                    personne.getAnneeEntree(),
                    personne.getTelephone(),
                    null);
        }
    return dto;
}
public void modifierPersonne(Integer id,Personne dto) {
    Personne personne = personneRepository.findById(id).orElseThrow(); // Recuperation Entité

            personne.setId(dto.getId());
            personne.setNom(dto.getNom());
            personne.setPrenoms(dto.getPrenoms());
            personne.setSexe(dto.getSexe());
            personne.setDateNaissance(dto.getDateNaissance());
            personne.setLieuNaissance(dto.getLieuNaissance());
            personne.setVilleNaissance(dto.getVilleNaissance());
            personne.setPaysNaissance(dto.getPaysNaissance());
            personne.setNationalite(dto.getNationalite());
            personne.setAdresse(dto.getAdresse());
            personne.setNumeroCIN(dto.getNumeroCIN());
            personne.setDateDelivreCIN(dto.getDateDelivreCIN());
            personne.setVilleDelivreCIN(dto.getVilleDelivreCIN());
            personne.setAffiliation1(dto.getAffiliation1());
            personne.setAffiliation2(dto.getAffiliation2());
            personne.setAnneeEntree(dto.getAnneeEntree());
            personne.setTelephone(dto.getTelephone());
            personne.setEmail(dto.getEmail());
            personneRepository.save(personne);
}
    public void deletePersonne(Integer id) {
            personneRepository.deleteById(id);
    }
}
