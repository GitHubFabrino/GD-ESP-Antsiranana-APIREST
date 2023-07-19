package com.appli.springjwt.dto;

public class ResponsableECDto {
    Integer idEnseignant;
    String nom;

    public ResponsableECDto(Integer idEnseignant, String nom) {
        this.idEnseignant = idEnseignant;
        this.nom = nom;
    }

    public Integer getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(Integer idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
