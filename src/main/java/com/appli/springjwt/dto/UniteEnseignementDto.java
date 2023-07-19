package com.appli.springjwt.dto;

import java.math.BigDecimal;

public class UniteEnseignementDto {

  Integer id;

  String codeUE;

  String nomUE;

  BigDecimal creditUE;

  Integer idEnseignant;

    public UniteEnseignementDto() {
    }

    public UniteEnseignementDto(Integer id, String nomUE) {
        this.id = id;
        this.nomUE = nomUE;
    }

    public UniteEnseignementDto(Integer id, String codeUE, String nomUE, BigDecimal creditUE) {
        this.id = id;
        this.codeUE = codeUE;
        this.nomUE = nomUE;
        this.creditUE = creditUE;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeUE() {
        return codeUE;
    }

    public void setCodeUE(String codeUE) {
        this.codeUE = codeUE;
    }

    public String getNomUE() {
        return nomUE;
    }

    public void setNomUE(String nomUE) {
        this.nomUE = nomUE;
    }

    public BigDecimal getCreditUE() {
        return creditUE;
    }

    public void setCreditUE(BigDecimal creditUE) {
        this.creditUE = creditUE;
    }

    public Integer getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(Integer idEnseignant) {
        this.idEnseignant = idEnseignant;
    }
}
