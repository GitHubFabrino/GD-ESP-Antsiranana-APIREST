package com.appli.springjwt.dto;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ProgrammeDto {
    Integer id;
    Integer idDP;
    Integer idUEEC;

    Integer idEnseignant;
    Integer idUE;
    Short codeUE;
    String nomUE;

    Integer idEC;
    BigDecimal creditEC;
    String codeEC;
    String nomEC;
    BigDecimal coefficientEC;
    Integer volumeHoraireET;
    Integer volumeHoraireED;
    Integer volumeHoraireTP;
    Integer volumeHoraireAutre;
    Integer volumeHoraireTotal;

    public ProgrammeDto(Integer id, Integer idDP, Integer idUEEC) {
        this.id = id;
        this.idDP = idDP;
        this.idUEEC = idUEEC;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdDP() {
        return idDP;
    }
    public void setIdDP(Integer idDP) {
        this.idDP = idDP;
    }
    public Integer getIdUEEC() {
        return idUEEC;
    }
    public void setIdUEEC(Integer idUEEC) {
        this.idUEEC = idUEEC;
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

    public Short getCodeUE() {
        return codeUE;
    }

    public void setCodeUE(Short codeUE) {
        this.codeUE = codeUE;
    }

    public String getNomUE() {
        return nomUE;
    }

    public void setNomUE(String nomUE) {
        this.nomUE = nomUE;
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
