package com.appli.springjwt.dto;

import java.util.Optional;

public class CentreConcoursTCIDto {
    Integer id_centreCTCI;
    String nomCentreCTCI;
    Integer codePostale;
    String nom;
    String prenoms;
    String telephone;

    Integer idPersonne;

    int idCTCI;

    public CentreConcoursTCIDto() {
    }

    public CentreConcoursTCIDto(String nomCentreCTCI, Integer codePostale, String nom, String prenoms, String telephone, Integer idPersonne, int idCTCI) {
        this.nomCentreCTCI = nomCentreCTCI;
        this.codePostale = codePostale;
        this.nom = nom;
        this.prenoms = prenoms;
        this.telephone = telephone;
        this.idPersonne = idPersonne;
        this.idCTCI = idCTCI;
    }

    public CentreConcoursTCIDto(Integer id_centreCTCI, String nomCentreCTCI, Integer codePostale, String nom, String prenoms, String telephone, Integer idPersonne, int idCTCI) {
        this.id_centreCTCI = id_centreCTCI;
        this.nomCentreCTCI = nomCentreCTCI;
        this.codePostale = codePostale;
        this.nom = nom;
        this.prenoms = prenoms;
        this.telephone = telephone;
        this.idPersonne = idPersonne;
        this.idCTCI = idCTCI;
    }

    //public Concourstci getIdCTCI() {
        //return idCTCI;
    //}
    public int getIdCTCI() {
        return idCTCI;
    }

    /*public void setIdCTCI(idCTCI) {
        this.idCTCI = idCTCI;
    }*/

    public void setId_centreCTCI(Integer id_centreCTCI) {
        this.id_centreCTCI = id_centreCTCI;
    }

    public void setIdCTCI(int idCTCI) {
        this.idCTCI = idCTCI;
    }

    public Integer getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Integer idPersonne) {
        this.idPersonne = idPersonne;
    }

    public CentreConcoursTCIDto(Integer id, String nomCentreCTCI, Integer codePostale, String nom, String prenoms, String telephone) {
    }
    public Integer getCodePostale() {
        return codePostale;
    }

    public void setCodePostale(Integer codePostale) {
        this.codePostale = codePostale;
    }


    public Integer getId_centreCTCI() {
        return id_centreCTCI;
    }

    public void setId(Integer id) {
        this.id_centreCTCI = id;
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


/*
    public CentreConcoursTCIDto(String nomCentreCTCI, Integer codePostale, String nom, String prenoms, String telephone) {
        this.nomCentreCTCI = nomCentreCTCI;
        this.codePostale = codePostale;
        this.nom = nom;
        this.prenoms = prenoms;
        this.telephone = telephone;
    }*/
    @Override
    public String toString() {
        return "CentreConcoursTCIDto{" +
                "id=" + id_centreCTCI +
                ", nomCentreCTCI='" + nomCentreCTCI + '\'' +
                ", codePostale=" + codePostale +
                ", nom='" + nom + '\'' +
                ", prenoms='" + prenoms + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }

    public void setIdCTCI(Optional<Object> id_ctci) {
    }
}
