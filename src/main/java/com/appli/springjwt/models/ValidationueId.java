package com.appli.springjwt.models;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ValidationueId implements Serializable {
    private static final long serialVersionUID = 3525135805935796664L;
    @NotNull
    @Column(name = "id_UE", nullable = false)
    private Integer idUe;

    @NotNull
    @Column(name = "id_cursus", nullable = false)
    private Integer idCursus;

    public Integer getIdUe() {
        return idUe;
    }

    public void setIdUe(Integer idUe) {
        this.idUe = idUe;
    }

    public Integer getIdCursus() {
        return idCursus;
    }

    public void setIdCursus(Integer idCursus) {
        this.idCursus = idCursus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ValidationueId entity = (ValidationueId) o;
        return Objects.equals(this.idCursus, entity.idCursus) &&
                Objects.equals(this.idUe, entity.idUe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCursus, idUe);
    }

}