package com.appli.springjwt.dto;

import java.util.ArrayList;

public class ProgrammeGetDtoEC {

    Integer idUE;
    String codeUE;
    String nomUE;

    public ProgrammeGetDtoEC() {
    }

    public ProgrammeGetDtoEC(Integer idUE, String codeUE, String nomUE) {
        this.idUE = idUE;
        this.codeUE = codeUE;
        this.nomUE = nomUE;
    }

    public Integer getIdUE() {
        return idUE;
    }

    public void setIdUE(Integer idUE) {
        this.idUE = idUE;
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
}
