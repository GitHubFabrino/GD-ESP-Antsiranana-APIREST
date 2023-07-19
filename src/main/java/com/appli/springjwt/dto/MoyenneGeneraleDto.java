package com.appli.springjwt.dto;

import java.math.BigDecimal;
import java.util.List;

public class MoyenneGeneraleDto {
    Integer idEtudiant;
    String nom;
    String prenoms;
    BigDecimal Moyenne;
    Byte codeRedoublement;

    private List<UniteEnseignementDto> uniteEnseignements;

    public MoyenneGeneraleDto() {
    }

    public MoyenneGeneraleDto(Integer idEtudiant, String nom, String prenoms, BigDecimal moyenne) {
        this.idEtudiant = idEtudiant;
        this.nom = nom;
        this.prenoms = prenoms;
        Moyenne = moyenne;
    }

    public MoyenneGeneraleDto(Integer idEtudiant, String nom, String prenoms, BigDecimal moyenne, Byte codeRedoublement) {
        this.idEtudiant = idEtudiant;
        this.nom = nom;
        this.prenoms = prenoms;
        Moyenne = moyenne;
        this.codeRedoublement = codeRedoublement;
    }

    public Integer getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(Integer idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public BigDecimal getMoyenne() {
        return Moyenne;
    }

    public void setMoyenne(BigDecimal moyenne) {
        Moyenne = moyenne;
    }


    public List<UniteEnseignementDto> getUniteEnseignements() {
        return uniteEnseignements;
    }

    public void setUniteEnseignements(List<UniteEnseignementDto> uniteEnseignements) {
        this.uniteEnseignements = uniteEnseignements;
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

    public Byte getCodeRedoublement() {
        return codeRedoublement;
    }

    public void setCodeRedoublement(Byte codeRedoublement) {
        this.codeRedoublement = codeRedoublement;
    }
}
