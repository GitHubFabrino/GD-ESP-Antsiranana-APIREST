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

    private Integer idCentreCTCI;

    public CandidatConcoursDto(String nom, String prenoms, String telephone) {
        this.nom = nom;
        this.prenoms = prenoms;
        this.telephone = telephone;
    }

    public CandidatConcoursDto() {
    }

    public CandidatConcoursDto(Boolean passationCandidatCTCI) {
        this.passationCandidatCTCI = passationCandidatCTCI;
    }

    public CandidatConcoursDto(Integer id, String nom, String prenoms, String telephone, Long numeroCandidatCTCI, BigDecimal moyenneCandidatCTCI, Boolean passationCandidatCTCI) {
        this.id = id;
        this.nom = nom;
        this.prenoms = prenoms;
        this.telephone = telephone;
        this.numeroCandidatCTCI = numeroCandidatCTCI;
        this.moyenneCandidatCTCI = moyenneCandidatCTCI;
        this.passationCandidatCTCI = passationCandidatCTCI;
    }

    public CandidatConcoursDto(Integer id, String nom, String prenoms, String telephone, Long numeroCandidatCTCI, Integer idCentreCTCI) {
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
