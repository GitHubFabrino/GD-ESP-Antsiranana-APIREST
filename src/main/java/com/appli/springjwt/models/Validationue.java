package com.appli.springjwt.models;

import javax.persistence.*;

@Entity
@Table(name = "validationue")
public class Validationue {
    @EmbeddedId
    private ValidationueId id=new ValidationueId();

    @MapsId("idUe")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_UE", nullable = false)
    private Uniteenseignement idUe;

    @MapsId("idCursus")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cursus", nullable = false)
    private Cursus idCursus;

    @Column(name = "validation_UE")
    private Byte validationUe;

    public ValidationueId getId() {
        return id;
    }

    public void setId(ValidationueId id) {
        this.id = id;
    }

    public Uniteenseignement getIdUe() {
        return idUe;
    }

    public void setIdUe(Uniteenseignement idUe) {
        this.idUe = idUe;
    }

    public Cursus getIdCursus() {
        return idCursus;
    }

    public void setIdCursus(Cursus idCursus) {
        this.idCursus = idCursus;
    }

    public Byte getValidationUe() {
        return validationUe;
    }

    public void setValidationUe(Byte validationUe) {
        this.validationUe = validationUe;
    }

    @Override
    public String toString() {
        return "Validationue{" +
                "id=" + id +
                ", idUe=" + idUe +
                ", idCursus=" + idCursus +
                ", validationUe=" + validationUe +
                '}';
    }
}