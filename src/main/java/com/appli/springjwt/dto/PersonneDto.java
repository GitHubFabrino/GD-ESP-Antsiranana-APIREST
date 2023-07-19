package com.appli.springjwt.dto;

import java.time.LocalDate;

public class PersonneDto {
    Integer id;
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
    Short anneeEntree;
    String telephone;
    String email;

    public PersonneDto() {
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

    public Short getAnneeEntree() {
        return anneeEntree;
    }

    public void setAnneeEntree(Short anneeEntree) {
        this.anneeEntree = anneeEntree;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PersonneDto(Integer id, String nom, String prenoms, String sexe, LocalDate dateNaissance, String lieuNaissance, String villeNaissance, String paysNaissance, String nationalite, String adresse, String numeroCIN, LocalDate dateDelivreCIN, String villeDelivreCIN, String affiliation1, String affiliation2, Short anneeEntree, String telephone) {
        this.id = id;
        this.nom = nom;
        this.prenoms = prenoms;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.villeNaissance = villeNaissance;
        this.paysNaissance = paysNaissance;
        this.nationalite = nationalite;
        this.adresse = adresse;
        this.numeroCIN = numeroCIN;
        this.dateDelivreCIN = dateDelivreCIN;
        this.villeDelivreCIN = villeDelivreCIN;
        this.affiliation1 = affiliation1;
        this.affiliation2 = affiliation2;
        this.anneeEntree = anneeEntree;
        this.telephone = telephone;
    }

    public PersonneDto(Integer id, String nom, String prenoms, String sexe, LocalDate dateNaissance, String lieuNaissance, String villeNaissance, String paysNaissance, String nationalite, String adresse, String numeroCIN, LocalDate dateDelivreCIN, String villeDelivreCIN, String affiliation1, String affiliation2, Short anneeEntree, String telephone, String email) {
        this.id = id;
        this.nom = nom;
        this.prenoms = prenoms;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.villeNaissance = villeNaissance;
        this.paysNaissance = paysNaissance;
        this.nationalite = nationalite;
        this.adresse = adresse;
        this.numeroCIN = numeroCIN;
        this.dateDelivreCIN = dateDelivreCIN;
        this.villeDelivreCIN = villeDelivreCIN;
        this.affiliation1 = affiliation1;
        this.affiliation2 = affiliation2;
        this.anneeEntree = anneeEntree;
        this.telephone = telephone;
        this.email=email;
    }
    public PersonneDto(String email) {
        this.email = email;
    }
}
