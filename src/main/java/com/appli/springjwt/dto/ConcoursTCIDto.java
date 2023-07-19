package com.appli.springjwt.dto;

public class ConcoursTCIDto {
    Integer id;
    String sessionCTCI;
    String anneeCTCI;
    String descriptionCTCI;

    public ConcoursTCIDto() {
    }

    public ConcoursTCIDto(Integer id, String sessionCTCI, String anneeCTCI, String descriptionCTCI) {
        this.id = id;
        this.sessionCTCI = sessionCTCI;
        this.anneeCTCI = anneeCTCI;
        this.descriptionCTCI = descriptionCTCI;
    }

    public ConcoursTCIDto(String sessionCTCI, String anneeCTCI, String descriptionCTCI) {
        this.sessionCTCI = sessionCTCI;
        this.anneeCTCI = anneeCTCI;
        this.descriptionCTCI = descriptionCTCI;
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
}
