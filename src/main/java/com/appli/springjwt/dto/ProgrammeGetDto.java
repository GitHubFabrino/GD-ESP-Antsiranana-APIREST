package com.appli.springjwt.dto;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ProgrammeGetDto {
    ArrayList<Integer> idUE;
    ArrayList<String> codeUE;
    ArrayList<String> nomUE;
    ArrayList<Integer> idUEEC;
    ArrayList<ElementConstitutifDto> nomEC;
    ArrayList<String> codeEC;
    ArrayList<Integer> volumeHoraireET;
    ArrayList<Integer> volumeHoraireED;
    ArrayList<Integer> volumeHoraireTP;
    ArrayList<BigDecimal> creditEC;
    ArrayList<ResponsableECDto> responsableEC;
    ArrayList<ResponsableECDto> responsableUE;


    public ProgrammeGetDto() {
    }


    public ProgrammeGetDto(ArrayList<Integer> idUE, ArrayList<String> codeUE, ArrayList<String> nomUE, ArrayList<Integer> idUEEC, ArrayList<ElementConstitutifDto> nomEC, ArrayList<String> codeEC, ArrayList<Integer> volumeHoraireET, ArrayList<Integer> volumeHoraireED, ArrayList<Integer> volumeHoraireTP, ArrayList<BigDecimal> creditEC, ArrayList<ResponsableECDto> responsableEC, ArrayList<ResponsableECDto> responsableUE) {
        this.idUE = idUE;
        this.codeUE = codeUE;
        this.nomUE = nomUE;
        this.idUEEC = idUEEC;
        this.nomEC = nomEC;
        this.codeEC = codeEC;
        this.volumeHoraireET = volumeHoraireET;
        this.volumeHoraireED = volumeHoraireED;
        this.volumeHoraireTP = volumeHoraireTP;
        this.creditEC = creditEC;
        this.responsableEC = responsableEC;
        this.responsableUE = responsableUE;
    }

    public ArrayList<Integer> getIdUEEC() {
        return idUEEC;
    }

    public void setIdUEEC(ArrayList<Integer> idUEEC) {
        this.idUEEC = idUEEC;
    }

    public ArrayList<String> getCodeEC() {
        return codeEC;
    }

    public void setCodeEC(ArrayList<String> codeEC) {
        this.codeEC = codeEC;
    }

    public ArrayList<String> getCodeUE() {
        return codeUE;
    }

    public void setCodeUE(ArrayList<String> codeUE) {
        this.codeUE = codeUE;
    }

    public ArrayList<String> getNomUE() {
        return nomUE;
    }

    public void setNomUE(ArrayList<String> nomUE) {
        this.nomUE = nomUE;
    }

    public ArrayList<ElementConstitutifDto> getNomEC() {
        return nomEC;
    }

    public void setNomEC(ArrayList<ElementConstitutifDto> nomEC) {
        this.nomEC = nomEC;
    }

    public ArrayList<Integer> getVolumeHoraireET() {
        return volumeHoraireET;
    }

    public void setVolumeHoraireET(ArrayList<Integer> volumeHoraireET) {
        this.volumeHoraireET = volumeHoraireET;
    }

    public ArrayList<Integer> getVolumeHoraireED() {
        return volumeHoraireED;
    }

    public void setVolumeHoraireED(ArrayList<Integer> volumeHoraireED) {
        this.volumeHoraireED = volumeHoraireED;
    }

    public ArrayList<Integer> getVolumeHoraireTP() {
        return volumeHoraireTP;
    }

    public void setVolumeHoraireTP(ArrayList<Integer> volumeHoraireTP) {
        this.volumeHoraireTP = volumeHoraireTP;
    }

    public ArrayList<BigDecimal> getCreditEC() {
        return creditEC;
    }

    public void setCreditEC(ArrayList<BigDecimal> creditEC) {
        this.creditEC = creditEC;
    }

    public ArrayList<ResponsableECDto> getResponsableEC() {
        return responsableEC;
    }

    public void setResponsableEC(ArrayList<ResponsableECDto> responsableEC) {
        this.responsableEC = responsableEC;
    }

    public ArrayList<ResponsableECDto> getResponsableUE() {
        return responsableUE;
    }

    public void setResponsableUE(ArrayList<ResponsableECDto> responsableUE) {
        this.responsableUE = responsableUE;
    }

    public ArrayList<Integer> getIdUE() {
        return idUE;
    }

    public void setIdUE(ArrayList<Integer> idUE) {
        this.idUE = idUE;
    }
}
