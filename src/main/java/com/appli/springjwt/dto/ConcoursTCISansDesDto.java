package com.appli.springjwt.dto;

public class ConcoursTCISansDesDto {

    String session_CTCI;

    String annee_CTCI;


    public ConcoursTCISansDesDto() {
    }

    public ConcoursTCISansDesDto(String session_CTCI, String annee_CTCI) {
        this.session_CTCI = session_CTCI;
        this.annee_CTCI = annee_CTCI;
    }

    public String getSession_CTCI() {
        return session_CTCI;
    }

    public void setSession_CTCI(String session_CTCI) {
        this.session_CTCI = session_CTCI;
    }

    public String getAnnee_CTCI() {
        return annee_CTCI;
    }

    public void setAnnee_CTCI(String annee_CTCI) {
        this.annee_CTCI = annee_CTCI;
    }
}
