package com.appli.springjwt.models;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CalendrierconcourstciId implements Serializable {
    private static final long serialVersionUID = 3292693702568175377L;
    @NotNull
    @Column(name = "id_CTCI", nullable = false)
    private Integer idCTCI;

    @NotNull
    @Column(name = "id_MCTCI", nullable = false)
    private Integer idMCTCI;

    public CalendrierconcourstciId() {
    }

    public CalendrierconcourstciId(Integer idCTCI, Integer idMCTCI) {
        super();
        this.idCTCI = idCTCI;
        this.idMCTCI = idMCTCI;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CalendrierconcourstciId entity = (CalendrierconcourstciId) o;
        return Objects.equals(this.idMCTCI, entity.idMCTCI) &&
                Objects.equals(this.idCTCI, entity.idCTCI);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMCTCI, idCTCI);
    }

}