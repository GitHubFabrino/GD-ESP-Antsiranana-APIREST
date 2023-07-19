package com.appli.springjwt.dto;

import java.util.ArrayList;

public class ReleveNoteDto {
    String nomUE;
    ArrayList<Integer> idUEEC;
    ArrayList<String> nomEC;
    ArrayList<Float> noteEC;
    Float moyenneUE;
    Float creditUE;
    Byte validationUE;

    public ReleveNoteDto(String nomUE, ArrayList<String> nomEC, ArrayList<Float> noteEC, Float moyenneUE, Float creditUE, Byte validationUE) {
        this.nomUE = nomUE;
        this.nomEC = nomEC;
        this.noteEC = noteEC;
        this.moyenneUE = moyenneUE;
        this.creditUE = creditUE;
        this.validationUE = validationUE;
    }

    public ReleveNoteDto(String nomUE, ArrayList<Integer> idUEEC, ArrayList<String> nomEC, ArrayList<Float> noteEC, Float moyenneUE, Float creditUE, Byte validationUE) {
        this.nomUE = nomUE;
        this.idUEEC = idUEEC;
        this.nomEC = nomEC;
        this.noteEC = noteEC;
        this.moyenneUE = moyenneUE;
        this.creditUE = creditUE;
        this.validationUE = validationUE;
    }

    public String getNomUE() {
        return nomUE;
    }

    public void setNomUE(String nomUE) {
        this.nomUE = nomUE;
    }

    public ArrayList<String> getNomEC() {
        return nomEC;
    }

    public void setNomEC(ArrayList<String> nomEC) {
        this.nomEC = nomEC;
    }

    public ArrayList<Float> getNoteEC() {
        return noteEC;
    }

    public void setNoteEC(ArrayList<Float> noteEC) {
        this.noteEC = noteEC;
    }

    public Float getMoyenneUE() {
        return moyenneUE;
    }

    public void setMoyenneUE(Float moyenneUE) {
        this.moyenneUE = moyenneUE;
    }

    public Float getCreditUE() {
        return creditUE;
    }

    public void setCreditUE(Float creditUE) {
        this.creditUE = creditUE;
    }

    public Byte getValidationUE() {
        return validationUE;
    }

    public void setValidationUE(Byte validationUE) {
        this.validationUE = validationUE;
    }

    public ArrayList<Integer> getIdUEEC() {
        return idUEEC;
    }
    public void setIdUEEC(ArrayList<Integer> idUEEC) {
        this.idUEEC = idUEEC;
    }
}
