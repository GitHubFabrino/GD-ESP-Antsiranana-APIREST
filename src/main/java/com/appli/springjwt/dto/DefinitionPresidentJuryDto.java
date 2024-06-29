package com.appli.springjwt.dto;

import com.appli.springjwt.models.Concourstci;

public class DefinitionPresidentJuryDto {


    private Integer idAU;
    private Integer idConcour;

    private Integer idEnseignant;


    public DefinitionPresidentJuryDto() {
    }

    public DefinitionPresidentJuryDto(Integer idAU, Integer idConcour, Integer idEnseignant) {
        this.idAU = idAU;
        this.idConcour = idConcour;
        this.idEnseignant = idEnseignant;
    }

    public Integer getIdAU() {
        return idAU;
    }

    public void setIdAU(Integer idAU) {
        this.idAU = idAU;
    }

    public Integer getIdConcour() {
        return idConcour;
    }

    public void setIdConcour(Integer idConcour) {
        this.idConcour = idConcour;
    }

    public Integer getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(Integer idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    @Override
    public String toString() {
        return "DefinitionPresidentJuryDto{" +
                "idAU=" + idAU +
                ", idConcour=" + idConcour +
                ", idEnseignant=" + idEnseignant +
                '}';
    }
}
