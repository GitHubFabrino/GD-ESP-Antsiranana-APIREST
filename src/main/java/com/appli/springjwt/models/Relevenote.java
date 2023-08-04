package com.appli.springjwt.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "relevenote")
public class Relevenote {
    @EmbeddedId
    private RelevenoteId id = new RelevenoteId();
    @MapsId("idCursus")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cursus", nullable = false)
    private Cursus idCursus;

    @Column(name = "note", precision = 7, scale = 2)
    private BigDecimal note;

    @MapsId("idUeEc")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_UE_EC", nullable = false)
    private UeEc idUeEc;

    @Column(name = "cloturer")
    private Boolean cloturer;

    public Boolean getCloturer() {
        return cloturer;
    }

    public void setCloturer(Boolean cloturer) {
        this.cloturer = cloturer;
    }

    public UeEc getIdUeEc() {
        return idUeEc;
    }

    public void setIdUeEc(UeEc idUeEc) {
        this.idUeEc = idUeEc;
    }


    public Relevenote() {
    }

    public Relevenote(Cursus idCursus, BigDecimal note, UeEc idUeEc) {
        this.idCursus = idCursus;
        this.note = note;
        this.idUeEc = idUeEc;
    }

    public RelevenoteId getId() {
        return id;
    }

    public void setId(RelevenoteId id) {
        this.id = id;
    }

    public Cursus getIdCursus() {
        return idCursus;
    }

    public void setIdCursus(Cursus idCursus) {
        this.idCursus = idCursus;
    }

    public BigDecimal getNote() {
        return note;
    }

    public void setNote(BigDecimal note) {
        this.note = note;
    }


    @Override
    public String toString() {
        return "Relevenote{" +
                "id=" + id +
                ", idCursus=" + idCursus +
                ", note=" + note +
                ", idUeEc=" + idUeEc +
                ", cloturer=" + cloturer +
                '}';
    }
}