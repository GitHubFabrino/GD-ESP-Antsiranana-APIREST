package com.appli.springjwt.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "notematiereconcourstci")
public class Notematiereconcourstci {
    @EmbeddedId
    private NotematiereconcourstciId id = new NotematiereconcourstciId();
/*
    @MapsId("idMCTCI")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_MCTCI", nullable = false)
    private Matiereconcourstci idMctci;

 */

    /*
    @MapsId("idCTCI")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_CTCI", nullable = false)
    private Concourstci concourstci;

     */
    @MapsId("idCtci")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_CTCI", nullable = false)
    private Concourstci idCtci;


    @MapsId("idCentrectci")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_centreCTCI", nullable = false)
    private Centreconcourstci idCentrectci;



    /*
    @MapsId("idCandidatCTCI")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_candidatCTCI", nullable = false)
    private Candidatconcourstci idCandidatctci;

     */

    @Column(name = "note_MCTCI")
    private BigDecimal noteMctci;

    @MapsId("idCandidatctci")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_candidatCTCI", nullable = false)
    private Candidatconcourstci idCandidatctci;

    @MapsId("idMctci")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_MCTCI", nullable = false)
    private Matiereconcourstci idMctci;

    public Concourstci getIdCtci() {
        return idCtci;
    }

    public void setIdCtci(Concourstci idCtci) {
        this.idCtci = idCtci;
    }

    public Matiereconcourstci getIdMctci() {
        return idMctci;
    }

    public void setIdMctci(Matiereconcourstci idMctci) {
        this.idMctci = idMctci;
    }


    public Candidatconcourstci getIdCandidatctci() {
        return idCandidatctci;
    }

    public void setIdCandidatctci(Candidatconcourstci idCandidatctci) {
        this.idCandidatctci = idCandidatctci;
    }

    public Notematiereconcourstci() {
    }

    public BigDecimal getNoteMctci() {
        return noteMctci;
    }

    public void setNoteMctci(BigDecimal noteMctci) {
        this.noteMctci = noteMctci;
    }

    public Centreconcourstci getIdCentrectci() {
        return idCentrectci;
    }

    public void setIdCentrectci(Centreconcourstci idCentrectci) {
        this.idCentrectci = idCentrectci;
    }



/*
    public Candidatconcourstci getIdCandidatctci() {
        return idCandidatctci;
    }

    public void setIdCandidatctci(Candidatconcourstci idCandidatctci) {
        this.idCandidatctci = idCandidatctci;
    }

 */

    /*
    public Concourstci getConcourstci() {
        return concourstci;
    }

    public void setConcourstci(Concourstci concourstci) {
        this.concourstci = concourstci;
    }

     */

    public NotematiereconcourstciId getId() {
        return id;
    }

    public void setId(NotematiereconcourstciId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Notematiereconcourstci{" +
                "id=" + id +
                ", idCtci=" + idCtci +
                ", idCentrectci=" + idCentrectci +
                ", noteMctci=" + noteMctci +
                ", idCandidatctci=" + idCandidatctci +
                ", idMctci=" + idMctci +
                '}';
    }
//TODO [JPA Buddy] generate columns from DB
}