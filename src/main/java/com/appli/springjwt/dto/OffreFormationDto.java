package com.appli.springjwt.dto;

public class OffreFormationDto {
    Integer id;
    Integer idAU;
    Integer idSemestre;
    Integer idMention;
    Integer idNiveau;
    Integer IdParcours;
    String acronymeParcours;
    String parcours;

    public OffreFormationDto() {
    }

    public OffreFormationDto(Integer id,Integer idAU, Integer idSemestre, Integer idMention, Integer idNiveau,Integer IdParcours, String acronymeParcours,String parcours) {
        this.id = id;
        this.idAU = idAU;
        this.idSemestre = idSemestre;
        this.idMention = idMention;
        this.idNiveau = idNiveau;
        this.IdParcours = IdParcours;
        this.acronymeParcours = acronymeParcours;
        this.parcours = parcours;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdAU() {
        return idAU;
    }

    public void setIdAU(Integer idAU) {
        this.idAU = idAU;
    }

    public Integer getIdSemestre() {
        return idSemestre;
    }

    public void setIdSemestre(Integer idSemestre) {
        this.idSemestre = idSemestre;
    }

    public Integer getIdMention() {
        return idMention;
    }

    public void setIdMention(Integer idMention) {
        this.idMention = idMention;
    }

    public Integer getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(Integer idNiveau) {
        this.idNiveau = idNiveau;
    }

    public Integer getIdParcours() {
        return IdParcours;
    }

    public void setIdParcours(Integer idParcours) {
        IdParcours = idParcours;
    }
    public String getAcronymeParcours() {
        return acronymeParcours;
    }

    public void setAcronymeParcours(String acronymeParcours) {
        this.acronymeParcours = acronymeParcours;
    }
    public String getParcours() {
        return parcours;
    }

    public void setParcours(String parcours) {
        this.parcours = parcours;
    }
}

