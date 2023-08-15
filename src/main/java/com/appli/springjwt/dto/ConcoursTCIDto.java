package com.appli.springjwt.dto;

import java.time.LocalDate;

public class ConcoursTCIDto {
    Integer id;
    String sessionCTCI;
    String anneeCTCI;

    String descriptionCTCI;

    LocalDate dateDebutConcours;

    LocalDate dateFinConcours;

    public ConcoursTCIDto() {
    }

    public ConcoursTCIDto(Integer id, String sessionCTCI, String anneeCTCI, String descriptionCTCI) {
        this.id = id;
        this.sessionCTCI = sessionCTCI;
        this.anneeCTCI = anneeCTCI;
        this.descriptionCTCI = descriptionCTCI;
    }

    public ConcoursTCIDto(Integer id, String sessionCTCI, String anneeCTCI, String descriptionCTCI, LocalDate dateDebutConcours, LocalDate dateFinConcours) {
        this.id = id;
        this.sessionCTCI = sessionCTCI;
        this.anneeCTCI = anneeCTCI;
        this.descriptionCTCI = descriptionCTCI;
        this.dateDebutConcours = dateDebutConcours;
        this.dateFinConcours = dateFinConcours;
    }

    public ConcoursTCIDto(String sessionCTCI, String anneeCTCI, String descriptionCTCI) {
        this.sessionCTCI = sessionCTCI;
        this.anneeCTCI = anneeCTCI;
        this.descriptionCTCI = descriptionCTCI;
    }

    public ConcoursTCIDto(String sessionCTCI, String anneeCTCI, String descriptionCTCI, LocalDate dateDebutConcours, LocalDate dateFinConcours) {
        this.sessionCTCI = sessionCTCI;
        this.anneeCTCI = anneeCTCI;
        this.descriptionCTCI = descriptionCTCI;
        this.dateDebutConcours = dateDebutConcours;
        this.dateFinConcours = dateFinConcours;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSessionCTCI() {
        return sessionCTCI;
    }

    public void setSessionCTCI(String sessionCTCI) {
        this.sessionCTCI = sessionCTCI;
    }

    public void setAnneeCTCI(String anneeCTCI) {
        this.anneeCTCI = anneeCTCI;
    }

    public String getDescriptionCTCI() {
        return descriptionCTCI;
    }

    public void setDescriptionCTCI(String descriptionCTCI) {
        this.descriptionCTCI = descriptionCTCI;
    }

    public String getAnneeCTCI() {
        return anneeCTCI;
    }

    public LocalDate getDateDebutConcours() {
        return dateDebutConcours;
    }

    public void setDateDebutConcours(LocalDate dateDebutConcours) {
        this.dateDebutConcours = dateDebutConcours;
    }

    public LocalDate getDateFinConcours() {
        return dateFinConcours;
    }

    public void setDateFinConcours(LocalDate dateFinConcours) {
        this.dateFinConcours = dateFinConcours;
    }

    @Override
    public String toString() {
        return "ConcoursTCIDto{" +
                "sessionCTCI='" + sessionCTCI + '\'' +
                ", dateDebutConcours=" + dateDebutConcours +
                ", dateFinConcours=" + dateFinConcours +
                '}';
    }
}
