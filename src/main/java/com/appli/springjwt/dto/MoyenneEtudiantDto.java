package com.appli.springjwt.dto;

import java.math.BigDecimal;

public class MoyenneEtudiantDto {
    Integer idCursus;
    Integer idUeEc;
    BigDecimal note;

    public MoyenneEtudiantDto() {
    }

    public MoyenneEtudiantDto(Integer idCursus, BigDecimal note) {
        this.idCursus = idCursus;
        this.note = note;
    }

    public Integer getIdCursus() {
        return idCursus;
    }

    public void setIdCursus(Integer idCursus) {
        this.idCursus = idCursus;
    }

    public Integer getIdUeEc() {
        return idUeEc;
    }

    public void setIdUeEc(Integer idUeEc) {
        this.idUeEc = idUeEc;
    }

    public BigDecimal getNote() {
        return note;
    }

    public void setNote(BigDecimal note) {
        this.note = note;
    }

}
