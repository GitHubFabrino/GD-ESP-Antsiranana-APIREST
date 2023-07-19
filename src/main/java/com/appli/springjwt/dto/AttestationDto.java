package com.appli.springjwt.dto;
import java.lang.String;

public class AttestationDto {
    MentionDto mention;
    String parcours;
    PersonneDto personne;
    Integer anneeFin;
    public AttestationDto() {
    }
    public MentionDto getMention() {
        return mention;
    }
    public void setMention(MentionDto mention) {
        this.mention = mention;
    }
    public String getParcours() {
        return parcours;
    }
    public void setParcours(String parcours) {
        this.parcours = parcours;
    }

    public PersonneDto getPersonne() {
        return personne;
    }

    public void setPersonne(PersonneDto personne) {
        this.personne = personne;
    }
    public Integer getAnneeFin() {
        return anneeFin;
    }
    public void setAnneeFin(Integer anneeFin) {
        this.anneeFin = anneeFin;
    }
}
