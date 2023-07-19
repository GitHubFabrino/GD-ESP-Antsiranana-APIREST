package com.appli.springjwt.models;

import javax.persistence.*;

@Entity
@Table(name = "status")
public class Status {
    @EmbeddedId
    private StatusId id = new StatusId();

    @MapsId("idAuthentification")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_authentification", nullable = false)
    private Authentification idAuthentification;

    @MapsId("idFonction")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_fonction", nullable = false)
    private Fonction idFonction;

    public StatusId getId() {
        return id;
    }

    public void setId(StatusId id) {
        this.id = id;
    }

    public Authentification getIdAuthentification() {
        return idAuthentification;
    }

    public void setIdAuthentification(Authentification idAuthentification) {
        this.idAuthentification = idAuthentification;
    }
    public Fonction getIdFonction() {
        return idFonction;
    }

    public void setIdFonction(Fonction idFonction) {
        this.idFonction = idFonction;
    }

}