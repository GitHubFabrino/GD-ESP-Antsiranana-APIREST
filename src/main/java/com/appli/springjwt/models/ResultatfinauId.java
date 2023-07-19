package com.appli.springjwt.models;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ResultatfinauId implements Serializable {
    private static final long serialVersionUID = -167171980435801656L;
    @NotNull
    @Column(name = "id_etudiant", nullable = false)
    private Integer idEtudiant;

    @NotNull
    @Column(name = "id_DP", nullable = false)
    private Integer idDp;

    public Integer getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(Integer idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public Integer getIdDp() {
        return idDp;
    }

    public void setIdDp(Integer idDp) {
        this.idDp = idDp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ResultatfinauId entity = (ResultatfinauId) o;
        return Objects.equals(this.idDp, entity.idDp) &&
                Objects.equals(this.idEtudiant, entity.idEtudiant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDp, idEtudiant);
    }

}