package com.appli.springjwt.dto;

public class HeureenseignementDto {
    Integer idAU;
    Integer idEnseignant;
    Integer idPE;
    Integer comptageHeureET;
    Integer comptageHeureED;
    Integer comptageHeureTP;
    Integer comptageHeureAutre;
    Integer comptageHeureTotal;

    public HeureenseignementDto() {
    }

    public HeureenseignementDto(Integer idEnseignant, Integer idPE) {
        this.idEnseignant = idEnseignant;
        this.idPE = idPE;
    }

    public HeureenseignementDto(Integer idAU, Integer idEnseignant, Integer idPE, Integer comptageHeureET, Integer comptageHeureED, Integer comptageHeureTP, Integer comptageHeureAutre, Integer comptageHeureTotal) {
        this.idAU = idAU;
        this.idEnseignant = idEnseignant;
        this.idPE = idPE;
        this.comptageHeureET = comptageHeureET;
        this.comptageHeureED = comptageHeureED;
        this.comptageHeureTP = comptageHeureTP;
        this.comptageHeureAutre = comptageHeureAutre;
        this.comptageHeureTotal = comptageHeureTotal;
    }

    public Integer getIdAU() {
        return idAU;
    }

    public void setIdAU(Integer idAU) {
        this.idAU = idAU;
    }

    public Integer getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(Integer idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    public Integer getIdPE() {
        return idPE;
    }

    public void setIdPE(Integer idPE) {
        this.idPE = idPE;
    }

    public Integer getComptageHeureET() {
        return comptageHeureET;
    }

    public void setComptageHeureET(Integer comptageHeureET) {
        this.comptageHeureET = comptageHeureET;
    }

    public Integer getComptageHeureED() {
        return comptageHeureED;
    }

    public void setComptageHeureED(Integer comptageHeureED) {
        this.comptageHeureED = comptageHeureED;
    }

    public Integer getComptageHeureTP() {
        return comptageHeureTP;
    }

    public void setComptageHeureTP(Integer comptageHeureTP) {
        this.comptageHeureTP = comptageHeureTP;
    }

    public Integer getComptageHeureAutre() {
        return comptageHeureAutre;
    }

    public void setComptageHeureAutre(Integer comptageHeureAutre) {
        this.comptageHeureAutre = comptageHeureAutre;
    }

    public Integer getComptageHeureTotal() {
        return comptageHeureTotal;
    }

    public void setComptageHeureTotal(Integer comptageHeureTotal) {
        this.comptageHeureTotal = comptageHeureTotal;
    }


}
