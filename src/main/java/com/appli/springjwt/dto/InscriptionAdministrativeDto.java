package com.appli.springjwt.dto;

import java.time.LocalDate;

public class InscriptionAdministrativeDto {
    Integer id;
    Boolean validiteIA;
    Integer idEtudiant;
    Integer numeroMatricule;
    Integer idPersonne;

    String nom;
    String prenoms;
    String sexe;
    LocalDate dateNaissance;
    String lieuNaissance;
    String villeNaissance;
    String paysNaissance;
    String nationalite;
    String adresse;
    String numeroCIN;
    LocalDate dateDelivreCIN;
    String villeDelivreCIN;
    String affiliation1;
    String affiliation2;

    String telephone;
    Integer idEmail;
    String email;
    Integer idBacc;
    String Bacc;
    Short anneeBacc;
    Integer idNiveau;
    Integer idAU;

    public InscriptionAdministrativeDto() {
    }

    public InscriptionAdministrativeDto(Integer id, Boolean validiteIA, Integer idEtudiant, Integer idPersonne, String nom, String prenoms) {
        this.id = id;
        this.validiteIA = validiteIA;
        this.idEtudiant = idEtudiant;
        this.idPersonne = idPersonne;
        this.nom = nom;
        this.prenoms = prenoms;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getValiditeIA() {
        return validiteIA;
    }

    public void setValiditeIA(Boolean validiteIA) {
        this.validiteIA = validiteIA;
    }

    public Integer getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(Integer idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public Integer getNumeroMatricule() {
        return numeroMatricule;
    }

    public void setNumeroMatricule(Integer numeroMatricule) {
        this.numeroMatricule = numeroMatricule;
    }

    public Integer getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Integer idPersonne) {
        this.idPersonne = idPersonne;
    }

    public Integer getIdBacc() {
        return idBacc;
    }
    public void setIdBacc(Integer idBacc) {
        this.idBacc = idBacc;
    }

    public String getBacc() {
        return Bacc;
    }
    public void setBacc(String bacc) {
        this.Bacc = bacc;
    }

    public Short getAnneeBacc() {
        return anneeBacc;
    }

    public void setAnneeBacc(Short anneeBacc) {
        this.anneeBacc = anneeBacc;
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

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getVilleNaissance() {
        return villeNaissance;
    }

    public void setVilleNaissance(String villeNaissance) {
        this.villeNaissance = villeNaissance;
    }

    public String getPaysNaissance() {
        return paysNaissance;
    }

    public void setPaysNaissance(String paysNaissance) {
        this.paysNaissance = paysNaissance;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumeroCIN() {
        return numeroCIN;
    }

    public void setNumeroCIN(String numeroCIN) {
        this.numeroCIN = numeroCIN;
    }

    public LocalDate getDateDelivreCIN() {
        return dateDelivreCIN;
    }

    public void setDateDelivreCIN(LocalDate dateDelivreCIN) {
        this.dateDelivreCIN = dateDelivreCIN;
    }

    public String getVilleDelivreCIN() {
        return villeDelivreCIN;
    }

    public void setVilleDelivreCIN(String villeDelivreCIN) {
        this.villeDelivreCIN = villeDelivreCIN;
    }

    public String getAffiliation1() {
        return affiliation1;
    }

    public void setAffiliation1(String affiliation1) {
        this.affiliation1 = affiliation1;
    }

    public String getAffiliation2() {
        return affiliation2;
    }

    public void setAffiliation2(String affiliation2) {
        this.affiliation2 = affiliation2;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getIdEmail() {
        return idEmail;
    }

    public void setIdEmail(Integer idEmail) {
        this.idEmail = idEmail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
