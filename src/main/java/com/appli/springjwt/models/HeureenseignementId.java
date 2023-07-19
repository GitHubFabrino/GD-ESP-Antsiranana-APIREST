package com.appli.springjwt.models;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class HeureenseignementId implements Serializable {
    private static final long serialVersionUID = 3695765749322881788L;
    @NotNull
    @Column(name = "id_AU", nullable = false)
    private Integer idAu;

    @NotNull
    @Column(name = "id_enseignant", nullable = false)
    private Integer idEnseignant;

    @NotNull
    @Column(name = "id_PE", nullable = false)
    private Integer idPe;

    public Integer getIdAu() {
        return idAu;
    }

    public void setIdAu(Integer idAu) {
        this.idAu = idAu;
    }

    public Integer getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(Integer idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    public Integer getIdPe() {
        return idPe;
    }

    public void setIdPe(Integer idPe) {
        this.idPe = idPe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        HeureenseignementId entity = (HeureenseignementId) o;
        return Objects.equals(this.idPe, entity.idPe) &&
                Objects.equals(this.idEnseignant, entity.idEnseignant) &&
                Objects.equals(this.idAu, entity.idAu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPe, idEnseignant, idAu);
    }

}