package com.appli.springjwt.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class CalendrierConcoursTCIDto {
    Integer idCTCI;
    Integer idMCTCI;

    @Override
    public String toString() {
        return "CalendrierConcoursTCIDto{" +
                "idCTCI=" + idCTCI +
                ", idMCTCI=" + idMCTCI +
                ", nomMCTCI='" + nomMCTCI + '\'' +
                ", dateCalendrierCTCI=" + dateCalendrierCTCI +
                ", debutHeureCalendrierCTCI=" + debutHeureCalendrierCTCI +
                ", finHeureCalendrierCTCI=" + finHeureCalendrierCTCI +
                '}';
    }

    String nomMCTCI;
    LocalDate dateCalendrierCTCI;

    LocalTime debutHeureCalendrierCTCI;

    LocalTime finHeureCalendrierCTCI;


    public CalendrierConcoursTCIDto() {
    }

    public CalendrierConcoursTCIDto(Integer idCTCI, Integer idMCTCI, String nomMCTCI, LocalDate dateCalendrierCTCI, LocalTime debutHeureCalendrierCTCI, LocalTime finHeureCalendrierCTCI) {
        this.idCTCI = idCTCI;
        this.idMCTCI = idMCTCI;
        this.nomMCTCI = nomMCTCI;
        this.dateCalendrierCTCI = dateCalendrierCTCI;
        this.debutHeureCalendrierCTCI = debutHeureCalendrierCTCI;
        this.finHeureCalendrierCTCI = finHeureCalendrierCTCI;
    }

    public CalendrierConcoursTCIDto(String nomMCTCI, LocalDate dateCalendrierCTCI, LocalTime debutHeureCalendrierCTCI, LocalTime finHeureCalendrierCTCI) {
        this.nomMCTCI = nomMCTCI;
        this.dateCalendrierCTCI = dateCalendrierCTCI;
        this.debutHeureCalendrierCTCI = debutHeureCalendrierCTCI;
        this.finHeureCalendrierCTCI = finHeureCalendrierCTCI;
    }

    public Integer getIdCTCI() {
        return idCTCI;
    }

    public void setIdCTCI(Integer idCTCI) {
        this.idCTCI = idCTCI;
    }

    public Integer getIdMCTCI() {
        return idMCTCI;
    }

    public void setIdMCTCI(Integer idMCTCI) {
        this.idMCTCI = idMCTCI;
    }

    public String getNomMCTCI() {
        return nomMCTCI;
    }

    public void setNomMCTCI(String nomMCTCI) {
        this.nomMCTCI = nomMCTCI;
    }

    public LocalDate getDateCalendrierCTCI() {
        return dateCalendrierCTCI;
    }

    public void setDateCalendrierCTCI(LocalDate dateCalendrierCTCI) {
        this.dateCalendrierCTCI = dateCalendrierCTCI;
    }

    public LocalTime getDebutHeureCalendrierCTCI() {
        return debutHeureCalendrierCTCI;
    }

    public void setDebutHeureCalendrierCTCI(LocalTime debutHeureCalendrierCTCI) {
        this.debutHeureCalendrierCTCI = debutHeureCalendrierCTCI;
    }

    public LocalTime getFinHeureCalendrierCTCI() {
        return finHeureCalendrierCTCI;
    }

    public void setFinHeureCalendrierCTCI(LocalTime finHeureCalendrierCTCI) {
        this.finHeureCalendrierCTCI = finHeureCalendrierCTCI;
    }
}
