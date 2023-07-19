package com.appli.springjwt.dto;

public class ScolariteDto {
    Integer id;
    String nom;
    String prenoms;
    Boolean gestionConcoursTCI;
    Boolean gestionInscription;
    Boolean gestionAccesTache;
    String status;
    String email;

    public ScolariteDto() {
    }


    public ScolariteDto(Integer id, String nom, String prenoms, Boolean gestionConcoursTCI, Boolean gestionInscription, Boolean gestionAccesTache, String status, String email) {
        this.id = id;
        this.nom = nom;
        this.prenoms = prenoms;
        this.gestionConcoursTCI = gestionConcoursTCI;
        this.gestionInscription = gestionInscription;
        this.gestionAccesTache = gestionAccesTache;
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

    public Boolean getGestionConcoursTCI() {
        return gestionConcoursTCI;
    }

    public void setGestionConcoursTCI(Boolean gestionConcoursTCI) {
        this.gestionConcoursTCI = gestionConcoursTCI;
    }

    public Boolean getGestionInscription() {
        return gestionInscription;
    }

    public void setGestionInscription(Boolean gestionInscription) {
        this.gestionInscription = gestionInscription;
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

    public Boolean getGestionAccesTache() {
        return gestionAccesTache;
    }
    public void setGestionAccesTache(Boolean gestionAccesTache) {
        this.gestionAccesTache = gestionAccesTache;
    }
}
