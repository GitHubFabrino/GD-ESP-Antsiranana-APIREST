package com.appli.springjwt.dto;

import java.math.BigDecimal;

public class UeEcDto {
    Integer id;
    Integer idEnseignant;
    Integer idUE;
    Integer idEC;
    BigDecimal creditEC;
    BigDecimal coefficientEC;
    Integer volumeHoraireET;
    Integer volumeHoraireED;
    Integer volumeHoraireTP;
    Integer volumeHoraireAutre;
    Integer volumeHoraireTotal;

    public UeEcDto() {
    }

    public UeEcDto(Integer id, Integer idEnseignant, Integer idUE, Integer idEC, BigDecimal creditEC, BigDecimal coefficientEC, Integer volumeHoraireET, Integer volumeHoraireED, Integer volumeHoraireTP, Integer volumeHoraireAutre, Integer volumeHoraireTotal) {
        this.id = id;
        this.idEnseignant = idEnseignant;
        this.idUE = idUE;
        this.idEC = idEC;
        this.creditEC = creditEC;
        this.coefficientEC = coefficientEC;
        this.volumeHoraireET = volumeHoraireET;
        this.volumeHoraireED = volumeHoraireED;
        this.volumeHoraireTP = volumeHoraireTP;
        this.volumeHoraireAutre = volumeHoraireAutre;
        this.volumeHoraireTotal = volumeHoraireTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(Integer idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    public Integer getIdUE() {
        return idUE;
    }

    public void setIdUE(Integer idUE) {
        this.idUE = idUE;
    }

    public Integer getIdEC() {
        return idEC;
    }

    public void setIdEC(Integer idEC) {
        this.idEC = idEC;
    }

    public BigDecimal getCreditEC() {
        return creditEC;
    }

    public void setCreditEC(BigDecimal creditEC) {
        this.creditEC = creditEC;
    }

    public BigDecimal getCoefficientEC() {
        return coefficientEC;
    }

    public void setCoefficientEC(BigDecimal coefficientEC) {
        this.coefficientEC = coefficientEC;
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
