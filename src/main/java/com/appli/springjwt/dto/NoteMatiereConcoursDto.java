package com.appli.springjwt.dto;

import java.util.ArrayList;

public class NoteMatiereConcoursDto {

    Integer idCTCI;

    Integer idCentreCTCI;

    ArrayList<Integer> matiere;

    ArrayList<Candidats> candidats;

    public NoteMatiereConcoursDto() {
    }

    public NoteMatiereConcoursDto(Integer idCTCI, Integer idCentreCTCI, ArrayList<Integer> matiere) {
        this.idCTCI = idCTCI;
        this.idCentreCTCI = idCentreCTCI;
        this.matiere = matiere;
    }

    public NoteMatiereConcoursDto(Integer idCTCI, Integer idCentreCTCI, ArrayList<Integer> matiere, ArrayList<Candidats> candidats) {
        this.idCTCI = idCTCI;
        this.idCentreCTCI = idCentreCTCI;
        this.matiere = matiere;
        this.candidats = candidats;
    }

    public Integer getIdCTCI() {
        return idCTCI;
    }

    public void setIdCTCI(Integer idCTCI) {
        this.idCTCI = idCTCI;
    }

    public Integer getIdCentreCTCI() {
        return idCentreCTCI;
    }

    public void setIdCentreCTCI(Integer idCentreCTCI) {
        this.idCentreCTCI = idCentreCTCI;
    }

    public ArrayList<Integer> getMatiere() {
        return matiere;
    }

    public void setMatiere(ArrayList<Integer> matiere) {
        this.matiere = matiere;
    }

    public ArrayList<Candidats> getCandidats() {
        return candidats;
    }

    public void setCandidatNotesDto(ArrayList<Candidats> candidats) {
        this.candidats = candidats;
    }
}
