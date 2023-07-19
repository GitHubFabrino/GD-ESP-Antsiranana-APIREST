package com.appli.springjwt.dto;

import java.math.BigDecimal;

public class ElementConstitutifDto {
    Integer id;
    String codeEC;
    String nomEC;

    
    Integer idEnseignant;
    BigDecimal creditEC;

    Integer volumeHoraireET;
    Integer volumeHoraireED;
    Integer volumeHoraireTP;
    Integer volumeHoraireAutre;
    Integer volumeHoraireTotal;


    public ElementConstitutifDto() {
    }

    public ElementConstitutifDto(Integer id, String codeEC, String nomEC) {
        this.id = id;
        this.codeEC = codeEC;
        this.nomEC = nomEC;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeEC() {
        return codeEC;
    }

    public void setCodeEC(String codeEC) {
        this.codeEC = codeEC;
    }

    public String getNomEC() {
        return nomEC;
    }

    public void setNomEC(String nomEC) {
        this.nomEC = nomEC;
    }

    public BigDecimal getCreditEC() {
        return creditEC;
    }

    public void setCreditEC(BigDecimal creditEC) {
        this.creditEC = creditEC;
    }

    public Integer getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(Integer idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    public Integer getVolumeHoraireET() {
        return volumeHoraireET;
    }

    public void setVolumeHoraireET(Integer volumeHoraireET) {
        this.volumeHoraireET = volumeHoraireET;
    }

    public Integer getVolumeHoraireED() {
        return volumeHoraireED;
    }

    public void setVolumeHoraireED(Integer volumeHoraireED) {
        this.volumeHoraireED = volumeHoraireED;
    }

    public Integer getVolumeHoraireTP() {
        return volumeHoraireTP;
    }

    public void setVolumeHoraireTP(Integer volumeHoraireTP) {
        this.volumeHoraireTP = volumeHoraireTP;
    }

    public Integer getVolumeHoraireAutre() {
        return volumeHoraireAutre;
    }

    public void setVolumeHoraireAutre(Integer volumeHoraireAutre) {
        this.volumeHoraireAutre = volumeHoraireAutre;
    }

    public Integer getVolumeHoraireTotal() {
        return volumeHoraireTotal;
    }

    public void setVolumeHoraireTotal(Integer volumeHoraireTotal) {
        this.volumeHoraireTotal = volumeHoraireTotal;
    }
}
