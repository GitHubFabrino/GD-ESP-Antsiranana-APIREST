package com.appli.springjwt.dto;

import java.time.LocalDate;

public class InscriptionAdministrativeDto {
    Integer id;
    Boolean validiteIA;
    Integer idEtudiant;
    String numeroMatricule;
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

    String etude1;
    String etude2;
    String etude3;

    String annee1;
    String annee2;
    String annee3;



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

    public InscriptionAdministrativeDto(Integer id, Boolean validiteIA, Integer idEtudiant, Integer idPersonne, String nom, String prenoms, String etude1, String etude2, String etude3, String annee1, String annee2, String annee3) {
        this.id = id;
        this.validiteIA = validiteIA;
        this.idEtudiant = idEtudiant;
        this.idPersonne = idPersonne;
        this.nom = nom;
        this.prenoms = prenoms;
        this.etude1 = etude1;
        this.etude2 = etude2;
        this.etude3 = etude3;
        this.annee1 = annee1;
        this.annee2 = annee2;
        this.annee3 = annee3;
    }

    public String getEtude1() {
        return etude1;
    }

    public void setEtude1(String etude1) {
        this.etude1 = etude1;
    }

    public String getEtude2() {
        return etude2;
    }

    public void setEtude2(String etude2) {
        this.etude2 = etude2;
    }

    public String getEtude3() {
        return etude3;
    }

    public void setEtude3(String etude3) {
        this.etude3 = etude3;
    }

    public String getAnnee1() {
        return annee1;
    }

    public void setAnnee1(String annee1) {
        this.annee1 = annee1;
    }

    public String getAnnee2() {
        return annee2;
    }

    public void setAnnee2(String annee2) {
        this.annee2 = annee2;
    }

    public String getAnnee3() {
        return annee3;
    }

    public void setAnnee3(String annee3) {
        this.annee3 = annee3;
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

    public String getNumeroMatricule() {
        return numeroMatricule;
    }

    public void setNumeroMatricule(String numeroMatricule) {
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

    @Override
    public String toString() {
        return "InscriptionAdministrativeDto{" +
                "id=" + id +
                ", validiteIA=" + validiteIA +
                ", idEtudiant=" + idEtudiant +
                ", numeroMatricule=" + numeroMatricule +
                ", idPersonne=" + idPersonne +
                ", nom='" + nom + '\'' +
                ", prenoms='" + prenoms + '\'' +
                ", sexe='" + sexe + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", lieuNaissance='" + lieuNaissance + '\'' +
                ", villeNaissance='" + villeNaissance + '\'' +
                ", paysNaissance='" + paysNaissance + '\'' +
                ", nationalite='" + nationalite + '\'' +
                ", adresse='" + adresse + '\'' +
                ", numeroCIN='" + numeroCIN + '\'' +
                ", dateDelivreCIN=" + dateDelivreCIN +
                ", villeDelivreCIN='" + villeDelivreCIN + '\'' +
                ", affiliation1='" + affiliation1 + '\'' +
                ", affiliation2='" + affiliation2 + '\'' +
                ", telephone='" + telephone + '\'' +
                ", idEmail=" + idEmail +
                ", email='" + email + '\'' +
                ", idBacc=" + idBacc +
                ", Bacc='" + Bacc + '\'' +
                ", anneeBacc=" + anneeBacc +
                ", idNiveau=" + idNiveau +
                ", idAU=" + idAU +
                ", etude1='" + etude1 + '\'' +
                ", etude2='" + etude2 + '\'' +
                ", etude3='" + etude3 + '\'' +
                ", annee1='" + annee1 + '\'' +
                ", annee2='" + annee2 + '\'' +
                ", annee3='" + annee3 + '\'' +
                '}';
    }
}
