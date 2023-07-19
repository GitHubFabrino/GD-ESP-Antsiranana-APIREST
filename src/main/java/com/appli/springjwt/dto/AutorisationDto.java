package com.appli.springjwt.dto;

public class AutorisationDto {
    Integer id;
    Integer idPersonne;
    String nom;
    String prenoms;
    String numeroRecu;
    Boolean autorisation;
    Integer idNiveau;
    Integer idAU;
    String niveau;
    Byte codeRedoublement;

    public AutorisationDto() {
    }

    public AutorisationDto(Integer id, String nom, String prenoms, String numeroRecu, Boolean autorisation) {
        this.id = id;
        this.nom = nom;
        this.prenoms = prenoms;
        this.numeroRecu = numeroRecu;
        this.autorisation = autorisation;
    }

    public AutorisationDto(String nom, String prenoms, String numeroRecu, Boolean autorisation) {
        this.nom = nom;
        this.prenoms = prenoms;
        this.numeroRecu = numeroRecu;
        this.autorisation = autorisation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getNumeroRecu() {
        return numeroRecu;
    }

    public void setNumeroRecu(String numeroRecu) {
        this.numeroRecu = numeroRecu;
    }

    public Boolean getAutorisation() {
        return autorisation;
    }

    public void setAutorisation(Boolean autorisation) {
        this.autorisation = autorisation;
    }

    public Integer getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(Integer idNiveau) {
        this.idNiveau = idNiveau;
    }

    public Integer getIdAU() {
        return idAU;
    }

    public void setIdAU(Integer idAU) {
        this.idAU = idAU;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public Integer getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Integer idPersonne) {
        this.idPersonne = idPersonne;
    }

    public Byte getCodeRedoublement() {
        return codeRedoublement;
    }

    public void setCodeRedoublement(Byte codeRedoublement) {
        this.codeRedoublement = codeRedoublement;
    }
}
