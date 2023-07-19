package com.appli.springjwt.dto;

public class CentreConcoursTCIDto {
    Integer id;
    String nomCentreCTCI;
    Integer codePostale;
    String nom;
    String prenoms;
    String telephone;

    public CentreConcoursTCIDto() {
    }
    public Integer getCodePostale() {
        return codePostale;
    }

    public void setCodePostale(Integer codePostale) {
        this.codePostale = codePostale;
    }

    public CentreConcoursTCIDto(Integer id, String nomCentreCTCI, Integer codePostale, String nom, String prenoms, String telephone) {
        this.id = id;
        this.nomCentreCTCI = nomCentreCTCI;
        this.codePostale = codePostale;
        this.nom = nom;
        this.prenoms = prenoms;
        this.telephone = telephone;
    }

    public CentreConcoursTCIDto(String nomCentreCTCI, Integer codePostale, String nom, String prenoms, String telephone) {
        this.nomCentreCTCI = nomCentreCTCI;
        this.codePostale = codePostale;
        this.nom = nom;
        this.prenoms = prenoms;
        this.telephone = telephone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomCentreCTCI() {
        return nomCentreCTCI;
    }

    public void setNomCentreCTCI(String nom_centreCTCI) {
        this.nomCentreCTCI = nom_centreCTCI;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
