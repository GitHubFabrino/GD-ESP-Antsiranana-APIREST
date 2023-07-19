package com.appli.springjwt.dto;


public class ResultatAUDto {
    Integer idEtudiant;
    Integer idPersonne;
    String nom;
    String prenoms;
    Byte codeRedoublement;

    public ResultatAUDto() {
    }



    public ResultatAUDto(Integer idEtudiant, Byte codeRedoublement) {
        this.idEtudiant = idEtudiant;
        this.codeRedoublement = codeRedoublement;
    }

    public ResultatAUDto(Integer idEtudiant, String nom, String prenoms, Byte codeRedoublement) {
        this.idEtudiant = idEtudiant;
        this.nom = nom;
        this.prenoms = prenoms;
        this.codeRedoublement = codeRedoublement;
    }

    public ResultatAUDto(Integer idEtudiant, Integer idPersonne, String nom, String prenoms, Byte codeRedoublement) {
        this.idEtudiant = idEtudiant;
        this.idPersonne = idPersonne;
        this.nom = nom;
        this.prenoms = prenoms;
        this.codeRedoublement = codeRedoublement;
    }

    public Integer getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(Integer idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public Byte getCodeRedoublement() {
        return codeRedoublement;
    }

    public void setCodeRedoublement(Byte codeRedoublement) {
        this.codeRedoublement = codeRedoublement;
    }

    public Integer getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Integer idPersonne) {
        this.idPersonne = idPersonne;
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


}
