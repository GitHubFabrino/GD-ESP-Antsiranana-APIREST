package com.appli.springjwt.models;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class NotematiereconcourstciId implements Serializable {
    private static final long serialVersionUID = 7257855775408414430L;

    @Column(name = "id_CTCI", nullable = false)
    private Integer idCtci;

    @Column(name = "id_centreCTCI", nullable = false)
    private Integer idCentrectci;

    @Column(name = "id_candidatCTCI", nullable = false)
    private Integer idCandidatctci;

    @Column(name = "id_MCTCI", nullable = false)
    private Integer idMctci;

    public Integer getIdCentrectci() {
        return idCentrectci;
    }

    public void setIdCentrectci(Integer idCentrectci) {
        this.idCentrectci = idCentrectci;
    }

    public Integer getIdCandidatctci() {
        return idCandidatctci;
    }

    public void setIdCandidatctci(Integer idCandidatctci) {
        this.idCandidatctci = idCandidatctci;
    }

    public Integer getIdMctci() {
        return idMctci;
    }

    public void setIdMctci(Integer idMctci) {
        this.idMctci = idMctci;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        NotematiereconcourstciId entity = (NotematiereconcourstciId) o;
        return Objects.equals(this.idMctci, entity.idMctci) &&
                Objects.equals(this.idCentrectci, entity.idCentrectci) &&
                Objects.equals(this.idCandidatctci, entity.idCandidatctci);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMctci, idCentrectci, idCandidatctci);
    }

}