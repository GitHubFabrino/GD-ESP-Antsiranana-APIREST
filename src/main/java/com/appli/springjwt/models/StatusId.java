package com.appli.springjwt.models;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StatusId implements Serializable {
    private static final long serialVersionUID = 7828215987075987229L;
    @NotNull
    @Column(name = "id_authentification", nullable = false)
    private Long idAuthentification;

    @NotNull
    @Column(name = "id_fonction", nullable = false)
    private Integer idFonction;

    public Long getIdAuthentification() {
        return idAuthentification;
    }

    public void setIdAuthentification(Long idAuthentification) {
        this.idAuthentification = idAuthentification;
    }

    public Integer getIdFonction() {
        return idFonction;
    }

    public void setIdFonction(Integer idFonction) {
        this.idFonction = idFonction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StatusId entity = (StatusId) o;
        return Objects.equals(this.idFonction, entity.idFonction) &&
                Objects.equals(this.idAuthentification, entity.idAuthentification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFonction, idAuthentification);
    }

}