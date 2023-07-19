package com.appli.springjwt.dto;

public class DefinitionMentionDto {
    private Integer id;
    private Integer idAU;
    private Integer idMention;
    private String mention;
    private String acronymeMention;
    Integer idEnseignant;
    String nom;
    String prenoms;

    public DefinitionMentionDto() {
    }

    public DefinitionMentionDto(Integer id) {
        this.id = id;
    }

    public DefinitionMentionDto(Integer id, String acronymeMention) {
        this.id = id;
        this.acronymeMention = acronymeMention;
    }

    public DefinitionMentionDto(Integer id, Integer idMention, String mention, String acronymeMention) {
        this.id = id;
        this.idMention = idMention;
        this.mention = mention;
        this.acronymeMention = acronymeMention;
    }

    public DefinitionMentionDto(Integer id, Integer idAU, Integer idMention, String mention, String acronymeMention, Integer idEnseignant) {
        this.id = id;
        this.idAU = idAU;
        this.idMention = idMention;
        this.mention = mention;
        this.acronymeMention = acronymeMention;
        this.idEnseignant = idEnseignant;
    }

    public DefinitionMentionDto(Integer id, Integer idAU, Integer idMention, String mention, String acronymeMention, Integer idEnseignant, String nom, String prenoms) {
        this.id = id;
        this.idAU = idAU;
        this.idMention = idMention;
        this.mention = mention;
        this.acronymeMention = acronymeMention;
        this.idEnseignant = idEnseignant;
        this.nom = nom;
        this.prenoms = prenoms;
    }

    public DefinitionMentionDto(String acronymeMention, String mention) {
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

    public Integer getIdMention() {
        return idMention;
    }

    public void setIdMention(Integer idMention) {
        this.idMention = idMention;
    }

    public String getMention() {
        return mention;
    }

    public void setMention(String mention) {
        this.mention = mention;
    }
    public String getAcronymeMention() {
        return acronymeMention;
    }

    public void setAcronymeMention(String acronymeMention) {
        this.acronymeMention = acronymeMention;
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
