package com.appli.springjwt.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "calendrierconcourstci")
public class Calendrierconcourstci {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    private CalendrierconcourstciId id = new CalendrierconcourstciId();

    @MapsId("idCTCI")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_CTCI", nullable = false)
    private Concourstci idCTCI;
    @MapsId("idMCTCI")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_MCTCI", nullable = false)
    private Matiereconcourstci idMCTCI;
    @Column(name = "date_calendrierCTCI")
    private LocalDate dateCalendrierCTCI;

    @Column(name = "debut_heure_calendrierCTCI")
    private LocalTime debutHeureCalendrierCTCI;

    @Column(name = "fin_heure_calendrierCTCI")
    private LocalTime finHeureCalendrierCTCI;

    public Calendrierconcourstci() {
    }

    public Calendrierconcourstci(Concourstci idCTCI, Matiereconcourstci idMCTCI, LocalDate dateCalendrierCTCI, LocalTime debutHeureCalendrierCTCI, LocalTime finHeureCalendrierCTCI) {
        this.idCTCI = idCTCI;
        this.idMCTCI = idMCTCI;
        this.dateCalendrierCTCI = dateCalendrierCTCI;
        this.debutHeureCalendrierCTCI = debutHeureCalendrierCTCI;
        this.finHeureCalendrierCTCI = finHeureCalendrierCTCI;
    }

    public CalendrierconcourstciId getId() {
        return id;
    }

    public void setId(CalendrierconcourstciId id) {
        this.id = id;
    }

    public Concourstci getIdCTCI() {
        return idCTCI;
    }

    public void setIdCTCI(Concourstci idCTCI) {
        this.idCTCI = idCTCI;
    }

    public Matiereconcourstci getIdMCTCI() {
        return idMCTCI;
    }

    public void setIdMCTCI(Matiereconcourstci idMCTCI) {
        this.idMCTCI = idMCTCI;
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