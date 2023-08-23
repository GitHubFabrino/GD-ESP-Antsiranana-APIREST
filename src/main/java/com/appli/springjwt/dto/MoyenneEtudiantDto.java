package com.appli.springjwt.dto;

import java.math.BigDecimal;

public class MoyenneEtudiantDto {
    Integer idCursus;
    Integer idUeEc;
    BigDecimal note;

    Byte codeRedoublement;

    public void setCodeRedoublement(Byte codeRedoublement) {
        this.codeRedoublement = codeRedoublement;
    }

    public MoyenneEtudiantDto() {
    }

    public MoyenneEtudiantDto(Integer idCursus, BigDecimal note) {
        this.idCursus = idCursus;
        this.note = note;
    }

    public MoyenneEtudiantDto(Integer idCursus, Integer idUeEc, BigDecimal note, Byte codeRedoublement) {
        this.idCursus = idCursus;
        this.idUeEc = idUeEc;
        this.note = note;
        this.codeRedoublement = codeRedoublement;
    }

    public MoyenneEtudiantDto(Byte codeRedoublement) {
        this.codeRedoublement = codeRedoublement;
    }

    public Byte getCodeRedoublement() {
        return codeRedoublement;
    }

    public void setCode_redoublement(Byte codeRedoublement) {
        this.codeRedoublement = codeRedoublement;
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

    @Override
    public String toString() {
        return "MoyenneEtudiantDto{" +
                "idCursus=" + idCursus +
                ", idUeEc=" + idUeEc +
                ", note=" + note +
                ", codeRedoublement=" + codeRedoublement +
                '}';
    }
}
