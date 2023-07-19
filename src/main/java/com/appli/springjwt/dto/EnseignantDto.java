package com.appli.springjwt.dto;

public class EnseignantDto {
    Integer id;
    String nom;
    String prenoms;
    String numeroMatricule;
    String grade;
    String specialite;
    String status;
    String email;

    public EnseignantDto() {
    }

    public EnseignantDto(Integer id, String nom, String prenoms, String grade, String specialite, String status, String email) {
        this.id = id;
        this.nom = nom;
        this.prenoms = prenoms;
        this.grade = grade;
        this.specialite = specialite;
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

    public String getNumeroMatricule() {
        return numeroMatricule;
    }

    public void setNumeroMatricule(String numeroMatricule) {
        this.numeroMatricule = numeroMatricule;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
