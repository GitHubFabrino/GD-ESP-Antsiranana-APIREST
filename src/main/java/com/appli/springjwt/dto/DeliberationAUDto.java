package com.appli.springjwt.dto;

import java.util.ArrayList;

public class DeliberationAUDto {
    Float mgv;
    Float ecv;
    Float ece;
    Float uec;
    Float uee;
    ArrayList<ResultatAUDto> listIdEtudiant;

    public DeliberationAUDto() {
    }

    public Float getMgv() {
        return mgv;
    }

    public void setMgv(Float mgv) {
        this.mgv = mgv;
    }

    public Float getEcv() {
        return ecv;
    }

    public void setEcv(Float ecv) {
        this.ecv = ecv;
    }

    public Float getEce() {
        return ece;
    }

    public void setEce(Float ece) {
        this.ece = ece;
    }

    public Float getUec() {
        return uec;
    }

    public void setUec(Float uec) {
        this.uec = uec;
    }

    public Float getUee() {
        return uee;
    }

    public void setUee(Float uee) {
        this.uee = uee;
    }

    public ArrayList<ResultatAUDto> getListIdEtudiant() {
        return listIdEtudiant;
    }

    public void setListIdEtudiant(ArrayList<ResultatAUDto> listIdEtudiant) {
        this.listIdEtudiant = listIdEtudiant;
    }

    @Override
    public String toString() {
        return "DeliberationAUDto{" +
                "mgv=" + mgv +
                ", ecv=" + ecv +
                ", ece=" + ece +
                ", uec=" + uec +
                ", uee=" + uee +
                ", listIdEtudiant=" + listIdEtudiant +
                '}';
    }
}
