package com.appli.springjwt.models;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RelevenoteId implements Serializable {
    private static final long serialVersionUID = -4641461361162443691L;
    @NotNull
    @Column(name = "id_cursus", nullable = false)
    private Integer idCursus;

    @NotNull
    @Column(name = "id_UE_EC", nullable = false)
    private Integer idUeEc;

    public Integer getIdCursus() {
        return idCursus;
    }

    public void setIdCursus(Integer idCursus) {
        this.idCursus = idCursus;
    }

    public Integer getIdUeEc() {
        return idUeEc;
    }

    public void setIdUeEc(Integer idUeEc) {
        this.idUeEc = idUeEc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RelevenoteId entity = (RelevenoteId) o;
        return Objects.equals(this.idUeEc, entity.idUeEc) &&
                Objects.equals(this.idCursus, entity.idCursus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUeEc, idCursus);
    }

    @Override
    public String toString() {
        return "RelevenoteId{" +
                "idCursus=" + idCursus +
                ", idUeEc=" + idUeEc +
                '}';
    }
}