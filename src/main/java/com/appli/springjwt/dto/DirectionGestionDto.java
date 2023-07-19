package com.appli.springjwt.dto;

public class DirectionGestionDto {
     Integer id;
     String nom;
     String prenoms;
     Boolean gestionUtilisateur;
     Boolean gestionSeminairePedagogiques;
     String status;
     String email;
     String statusEnseignant;

    public DirectionGestionDto() {
    }

    public DirectionGestionDto(String nom, String prenoms) {
        this.nom = nom;
        this.prenoms = prenoms;
    }

    public DirectionGestionDto(Integer id, String nom, String prenoms, Boolean gestionUtilisateur, Boolean gestionSeminairePedagogiques, String status) {
        this.id = id;
        this.nom = nom;
        this.prenoms = prenoms;
        this.gestionUtilisateur = gestionUtilisateur;
        this.gestionSeminairePedagogiques = gestionSeminairePedagogiques;
        this.status = status;
    }

    public DirectionGestionDto(Integer id, String nom, String prenoms, Boolean gestionUtilisateur, Boolean gestionSeminairePedagogiques, String status, String email) {
        this.id = id;
        this.nom = nom;
        this.prenoms = prenoms;
        this.gestionUtilisateur = gestionUtilisateur;
        this.gestionSeminairePedagogiques = gestionSeminairePedagogiques;
        this.status = status;
        this.email = email;
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

    public Boolean getGestionUtilisateur() {
        return gestionUtilisateur;
    }

    public void setGestionUtilisateur(Boolean gestionUtilisateur) {
        this.gestionUtilisateur = gestionUtilisateur;
    }

    public Boolean getGestionSeminairePedagogiques() {
        return gestionSeminairePedagogiques;
    }

    public void setGestionSeminairePedagogiques(Boolean gestionSeminairePedagogiques) {
        this.gestionSeminairePedagogiques = gestionSeminairePedagogiques;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatusEnseignant() {
        return statusEnseignant;
    }

    public void setStatusEnseignant(String statusEnseignant) {
        this.statusEnseignant = statusEnseignant;
    }
}
