package com.appli.springjwt.dto;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Candidats {
    Integer idCandidatCTCI;

    String nom;

    String prenoms;

    ArrayList<BigDecimal> notes;


    public Candidats() {
    }

    public Candidats(String nom, String prenoms) {
        this.nom = nom;
        this.prenoms = prenoms;
    }

    public Candidats(String nom, String prenoms, ArrayList<BigDecimal> notes) {
        this.nom = nom;
        this.prenoms = prenoms;
        this.notes = notes;
    }

    public Candidats(Integer idCandidatCTCI, String nom, String prenoms, ArrayList<BigDecimal> notes) {
        this.idCandidatCTCI = idCandidatCTCI;
        this.nom = nom;
        this.prenoms = prenoms;
        this.notes = notes;
    }

    public Candidats(Integer idCandidatCTCI, ArrayList<BigDecimal> notes) {
        this.idCandidatCTCI = idCandidatCTCI;
        this.notes = notes;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }

    public Integer getIdCandidatCTCI() {
        return idCandidatCTCI;
    }

    public void setIdCandidatCTCI(Integer idCandidatCTCI) {
        this.idCandidatCTCI = idCandidatCTCI;
    }

    public ArrayList<BigDecimal> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<BigDecimal> notes) {
        this.notes = notes;
    }
}
