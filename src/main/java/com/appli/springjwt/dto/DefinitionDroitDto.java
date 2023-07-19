package com.appli.springjwt.dto;

public class DefinitionDroitDto {
    Integer idDD;
    Integer idNiveau;
    String niveau;
    Integer idDroit;
    Long montant;
    String nomBanque;
    String numeroCompte;
    Integer idAU;
    String designationAU;

    public DefinitionDroitDto() {
    }

    public DefinitionDroitDto(Long montant) {
        this.montant = montant;
    }

    public DefinitionDroitDto(Integer idDD, Integer idNiveau, String niveau, Integer idDroit, Long montant, String nomBanque, String numeroCompte, Integer idAU, String designationAU) {
        this.idDD = idDD;
        this.idNiveau = idNiveau;
        this.niveau = niveau;
        this.idDroit = idDroit;
        this.montant = montant;
        this.nomBanque = nomBanque;
        this.numeroCompte = numeroCompte;
        this.idAU = idAU;
        this.designationAU = designationAU;
    }
    public Integer getIdDD() {
        return idDD;
    }

    public void setIdDD(Integer idDD) {
        this.idDD = idDD;
    }

    public Integer getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(Integer idNiveau) {
        this.idNiveau = idNiveau;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public Long getMontant() {
        return montant;
    }

    public void setMontant(Long montant) {
        this.montant = montant;
    }

    public String getNomBanque() {
        return nomBanque;
    }

    public void setNomBanque(String nomBanque) {
        this.nomBanque = nomBanque;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public Integer getIdAU() {
        return idAU;
    }

    public void setIdAU(Integer idAU) {
        this.idAU = idAU;
    }

    public String getDesignationAU() {
        return designationAU;
    }

    public void setDesignationAU(String designationAU) {
        this.designationAU = designationAU;
    }

    public Integer getIdDroit() {
        return idDroit;
    }

    public void setIdDroit(Integer idDroit) {
        this.idDroit = idDroit;
    }
}
