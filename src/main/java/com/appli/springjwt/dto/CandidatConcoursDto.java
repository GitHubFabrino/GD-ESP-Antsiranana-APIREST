package com.appli.springjwt.dto;

import java.math.BigDecimal;

public class CandidatConcoursDto {
    private Integer id;

    private String nom;

    private String prenoms;

    private String telephone;

    private Long numeroCandidatCTCI;

    private BigDecimal moyenneCandidatCTCI;

    private Boolean passationCandidatCTCI;

    //todo ajout passationCanditatCTCIAttente
    private Boolean passationCandidatCTCIAttente;

    private Integer idCentreCTCI;

    private String status_etudiant;
    
    private String nomCentreConcours;

    public CandidatConcoursDto(String nom, String prenoms, String telephone) {
        this.nom = nom;
        this.prenoms = prenoms;
        this.telephone = telephone;
    }

    public CandidatConcoursDto() {
    }

    public CandidatConcoursDto(Integer id, String nom, String prenoms, String telephone, Long numeroCandidatCTCI, BigDecimal moyenneCandidatCTCI, Boolean passationCandidatCTCI, Boolean passationCandidatCTCIAttente, Integer idCentreCTCI, String status_etudiant, String nomCentreConcours) {
        this.id = id;
        this.nom = nom;
        this.prenoms = prenoms;
        this.telephone = telephone;
        this.numeroCandidatCTCI = numeroCandidatCTCI;
        this.moyenneCandidatCTCI = moyenneCandidatCTCI;
        this.passationCandidatCTCI = passationCandidatCTCI;
        this.passationCandidatCTCIAttente = passationCandidatCTCIAttente;
        this.idCentreCTCI = idCentreCTCI;
        this.status_etudiant = status_etudiant;
        this.nomCentreConcours = nomCentreConcours;
    }

    public CandidatConcoursDto(Boolean passationCandidatCTCI) {
        this.passationCandidatCTCI = passationCandidatCTCI;
    }

    //todo ajout attente
    public CandidatConcoursDto(Integer id, String nom, String prenoms, String telephone, Long numeroCandidatCTCI, BigDecimal moyenneCandidatCTCI, Boolean passationCandidatCTCI , Boolean passationCandidatCTCIAttente , Integer idCentreCTCI , String nomCentreConcours) {
        this.id = id;
        this.nom = nom;
        this.prenoms = prenoms;
        this.telephone = telephone;
        this.numeroCandidatCTCI = numeroCandidatCTCI;
        this.moyenneCandidatCTCI = moyenneCandidatCTCI;
        this.passationCandidatCTCI = passationCandidatCTCI;
        this.passationCandidatCTCIAttente = passationCandidatCTCIAttente;
        this.idCentreCTCI = idCentreCTCI;
        this.nomCentreConcours = nomCentreConcours;
    }

    public CandidatConcoursDto(Integer id, String nom, String prenoms, String telephone, Long numeroCandidatCTCI, Integer id1, String nomCentreCTCI) {
        this.id = id;
        this.nom = nom;
        this.prenoms = prenoms;
        this.telephone = telephone;
        this.numeroCandidatCTCI = numeroCandidatCTCI;
        this.idCentreCTCI = id1;
        this.nomCentreConcours = nomCentreCTCI;
    }

    public Boolean getPassationCandidatCTCIAttente() {
        return passationCandidatCTCIAttente;
    }

    public void setPassationCandidatCTCIAttente(Boolean passationCandidatCTCIAttente) {
        this.passationCandidatCTCIAttente = passationCandidatCTCIAttente;
    }

    public String getNomCentreConcours() {
        return nomCentreConcours;
    }

    public void setNomCentreConcours(String nomCentreConcours) {
        this.nomCentreConcours = nomCentreConcours;
    }

    public CandidatConcoursDto(Integer id, String nom, String prenoms, String telephone, Long numeroCandidatCTCI, Integer idCentreCTCI ) {
        this.id = id;
        this.nom = nom;
        this.prenoms = prenoms;
        this.telephone = telephone;
        this.numeroCandidatCTCI = numeroCandidatCTCI;
        this.idCentreCTCI = idCentreCTCI;

    }

    public CandidatConcoursDto(String nom, String prenoms, String telephone, Long numeroCandidatCTCI, Integer idCentreCTCI) {
        this.nom = nom;
        this.prenoms = prenoms;
        this.telephone = telephone;
        this.numeroCandidatCTCI = numeroCandidatCTCI;
        this.idCentreCTCI = idCentreCTCI;
    }

    public CandidatConcoursDto(String nom, String prenoms, String telephone, Integer idCentreCTCI) {
        this.nom = nom;
        this.prenoms = prenoms;
        this.telephone = telephone;
        this.idCentreCTCI = idCentreCTCI;
    }

    public CandidatConcoursDto(Integer id, String nom, String prenoms, String telephone, Long numeroCandidatCTCI, BigDecimal moyenneCandidatCTCI, Boolean passationCandidatCTCI, Integer idCentreCTCI, String status_etudiant,String nomCentreConcours) {
        this.id = id;
        this.nom = nom;
        this.prenoms = prenoms;
        this.telephone = telephone;
        this.numeroCandidatCTCI = numeroCandidatCTCI;
        this.moyenneCandidatCTCI = moyenneCandidatCTCI;
        this.passationCandidatCTCI = passationCandidatCTCI;
        this.idCentreCTCI = idCentreCTCI;
        this.status_etudiant = status_etudiant;
        this.nomCentreConcours = nomCentreConcours;

    }

    public CandidatConcoursDto(Integer id, String nom, String prenoms, Boolean passationCandidatCTCI, String status_etudiant) {
        this.id = id;
        this.nom = nom;
        this.prenoms = prenoms;
        this.passationCandidatCTCI = passationCandidatCTCI;
        this.status_etudiant = status_etudiant;
    }

    public String getStatus_etudiant() {
        return status_etudiant;
    }

    public void setStatus_etudiant(String status_etudiant) {
        this.status_etudiant = status_etudiant;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Long getNumeroCandidatCTCI() {
        return numeroCandidatCTCI;
    }

    public void setNumeroCandidatCTCI(Long numeroCandidatCTCI) {
        this.numeroCandidatCTCI = numeroCandidatCTCI;
    }

    public BigDecimal getMoyenneCandidatCTCI() {
        return moyenneCandidatCTCI;
    }

    public void setMoyenneCandidatCTCI(BigDecimal moyenneCandidatCTCI) {
        this.moyenneCandidatCTCI = moyenneCandidatCTCI;
    }

    public Boolean getPassationCandidatCTCI() {
        return passationCandidatCTCI;
    }

    public void setPassationCandidatCTCI(Boolean passationCandidatCTCI) {
        this.passationCandidatCTCI = passationCandidatCTCI;
    }

    public Integer getIdCentreCTCI() {
        return idCentreCTCI;
    }

    public void setIdCentreCTCI(Integer idCentreCTCI) {
        this.idCentreCTCI = idCentreCTCI;
    }
}
