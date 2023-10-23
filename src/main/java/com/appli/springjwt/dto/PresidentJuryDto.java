package com.appli.springjwt.dto;

public class PresidentJuryDto {

    private Integer idPDJ;
    private Integer idConcour;
    private String session_CTCI;
    private Integer idEnseignant;
    private String nomPdj;
    private String prenomPdj;

    public PresidentJuryDto() {
    }

    public PresidentJuryDto(Integer idPDJ, Integer idConcour, String session_CTCI, Integer idEnseignant, String nomPdj, String prenomPdj) {
        this.idPDJ = idPDJ;
        this.idConcour = idConcour;
        this.session_CTCI = session_CTCI;
        this.idEnseignant = idEnseignant;
        this.nomPdj = nomPdj;
        this.prenomPdj = prenomPdj;
    }

    public PresidentJuryDto(Integer idConcour, String session_CTCI, Integer idEnseignant, String nomPdj, String prenomPdj) {
        this.idConcour = idConcour;
        this.session_CTCI = session_CTCI;
        this.idEnseignant = idEnseignant;
        this.nomPdj = nomPdj;
        this.prenomPdj = prenomPdj;
    }

    public Integer getIdPDJ() {
        return idPDJ;
    }

    public void setIdPDJ(Integer idPDJ) {
        this.idPDJ = idPDJ;
    }

    public Integer getIdConcour() {
        return idConcour;
    }

    public void setIdConcour(Integer idConcour) {
        this.idConcour = idConcour;
    }

    public String getSession_CTCI() {
        return session_CTCI;
    }

    public void setSession_CTCI(String session_CTCI) {
        this.session_CTCI = session_CTCI;
    }

    public Integer getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(Integer idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    public String getNomPdj() {
        return nomPdj;
    }

    public void setNomPdj(String nomPdj) {
        this.nomPdj = nomPdj;
    }

    public String getPrenomPdj() {
        return prenomPdj;
    }

    public void setPrenomPdj(String prenomPdj) {
        this.prenomPdj = prenomPdj;
    }
}
