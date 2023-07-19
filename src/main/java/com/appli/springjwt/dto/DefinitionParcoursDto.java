package com.appli.springjwt.dto;

public class DefinitionParcoursDto {
    Integer id;
    Integer idEnseignant;
    Integer idAU;
    Integer idNiveau;
    Integer idDM;
    Integer idSemestre;
    Integer idParcours;
    String acronymeParcours;
    String parcours;
    String nom;
    String prenoms;

    public DefinitionParcoursDto() {
    }
    public DefinitionParcoursDto(Integer id, Integer idEnseignant, Integer idAU, Integer idNiveau, Integer idDM, Integer idSemestre, Integer idParcours, String acronymeParcours, String parcours) {
        this.id = id;
        this.idEnseignant = idEnseignant;
        this.idAU = idAU;
        this.idNiveau = idNiveau;
        this.idDM = idDM;
        this.idSemestre = idSemestre;
        this.idParcours = idParcours;
        this.acronymeParcours = acronymeParcours;
        this.parcours = parcours;
    }


    public DefinitionParcoursDto(Integer id, Integer idEnseignant, Integer idAU, Integer idSemestre, Integer idParcours, String acronymeParcours, String parcours, String nom, String prenoms) {
        this.id = id;
        this.idEnseignant = idEnseignant;
        this.idAU = idAU;
        this.idSemestre = idSemestre;
        this.idParcours = idParcours;
        this.acronymeParcours = acronymeParcours;
        this.parcours = parcours;
        this.nom = nom;
        this.prenoms = prenoms;
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

    public Integer getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(Integer idNiveau) {
        this.idNiveau = idNiveau;
    }

    public Integer getIdDM() {
        return idDM;
    }

    public void setIdDM(Integer idDM) {
        this.idDM = idDM;
    }

    public Integer getIdSemestre() {
        return idSemestre;
    }

    public void setIdSemestre(Integer idSemestre) {
        this.idSemestre = idSemestre;
    }

    public Integer getIdParcours() {
        return idParcours;
    }

    public void setIdParcours(Integer idParcours) {
        this.idParcours = idParcours;
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

    public Integer getIdEnseignant() {
        return idEnseignant;
    }
    public void setIdEnseignant(Integer idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }
}
