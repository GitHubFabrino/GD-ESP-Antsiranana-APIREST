package com.appli.springjwt.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "concourstci")
public class Concourstci {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_CTCI", nullable = false)
    private Integer id;
    @Size(max = 200)
    @Column(name = "session_CTCI", length = 50)
    private String sessionCTCI;
    @Size(max = 200)
    @Column(name = "description_CTCI", length = 200)
    private String descriptionCTCI;

    @OneToMany(mappedBy = "idCTCI")
    private Set<Calendrierconcourstci> calendrierconcourstcis = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idCTCI")
    private Set<Centreconcourstci> centreconcourstcis = new LinkedHashSet<>();

    @Column(name = "date_debut_concours")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDebutConcours;

    @Column(name = "date_fin_concours")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFinConcours;

    @Override
    public String toString() {
        return "Concourstci{" +
                "id=" + id +
                ", sessionCTCI='" + sessionCTCI + '\'' +
                ", descriptionCTCI='" + descriptionCTCI + '\'' +
                ", calendrierconcourstcis=" + calendrierconcourstcis +
                ", centreconcourstcis=" + centreconcourstcis +
                ", dateDebutConcours=" + dateDebutConcours +
                ", dateFinConcours=" + dateFinConcours +
                '}';
    }


    public Set<Centreconcourstci> getCentreconcourstcis() {
        return centreconcourstcis;
    }

    public void setCentreconcourstcis(Set<Centreconcourstci> centreconcourstcis) {
        this.centreconcourstcis = centreconcourstcis;
    }

    public Set<Calendrierconcourstci> getCalendrierconcourstcis() {
        return calendrierconcourstcis;
    }

    public void setCalendrierconcourstcis(Set<Calendrierconcourstci> calendrierconcourstcis) {
        this.calendrierconcourstcis = calendrierconcourstcis;
    }

    public Concourstci() {
    }

    public Concourstci(String sessionCTCI, String descriptionCTCI) {
        this.sessionCTCI = sessionCTCI;
        this.descriptionCTCI = descriptionCTCI;
    }

    public Concourstci(String sessionCTCI, String descriptionCTCI, LocalDate dateDebutConcours, LocalDate dateFinConcours) {
        this.sessionCTCI = sessionCTCI;
        this.descriptionCTCI = descriptionCTCI;
        this.dateDebutConcours = dateDebutConcours;
        this.dateFinConcours = dateFinConcours;
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
    public String getDescriptionCTCI() {
        return descriptionCTCI;
    }
    public void setDescriptionCTCI(String descriptionCTCI) {
        this.descriptionCTCI = descriptionCTCI;
    }

}