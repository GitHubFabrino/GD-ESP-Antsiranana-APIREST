package com.appli.springjwt.dto;

import java.time.LocalDate;

public class EtudiantDto {

    Integer id;
    Integer numeroMatricule;
    Short anneeBacc;
    String statusEtudiant;
    Integer idBacc;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroMatricule() {
        return numeroMatricule;
    }

    public void setNumeroMatricule(Integer numeroMatricule) {
        this.numeroMatricule = numeroMatricule;
    }

    public Short getAnneeBacc() {
        return anneeBacc;
    }

    public void setAnneeBacc(Short anneeBacc) {
        this.anneeBacc = anneeBacc;
    }

    public String getStatusEtudiant() {
        return statusEtudiant;
    }

    public void setStatusEtudiant(String statusEtudiant) {
        this.statusEtudiant = statusEtudiant;
    }

    public Integer getIdBacc() {
        return idBacc;
    }

    public void setIdBacc(Integer idBacc) {
        this.idBacc = idBacc;
    }
}
