package com.appli.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "scolarite")
public class Scolarite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scolarite", nullable = false)
    private Integer id;

    @Column(name = "gestion_concours_TCI")
    private Boolean gestionConcoursTci;

    @Column(name = "gestion_inscription")
    private Boolean gestionInscription;

    @NotNull
    @Column(name = "gestion_acces_tache", nullable = false)
    private Boolean gestionAccesTache = false;

    @Size(max = 50)
    @Column(name = "status_scolarite", length = 50)
    private String statusScolarite;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_personne", nullable = false)
    private Personne idPersonne;



    public Boolean getGestionAccesTache() {
        return gestionAccesTache;
    }

    public void setGestionAccesTache(Boolean gestionAccesTache) {
        this.gestionAccesTache = gestionAccesTache;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getGestionConcoursTci() {
        return gestionConcoursTci;
    }

    public void setGestionConcoursTci(Boolean gestionConcoursTci) {
        this.gestionConcoursTci = gestionConcoursTci;
    }

    public Boolean getGestionInscription() {
        return gestionInscription;
    }

    public void setGestionInscription(Boolean gestionInscription) {
        this.gestionInscription = gestionInscription;
    }

    public String getStatusScolarite() {
        return statusScolarite;
    }

    public void setStatusScolarite(String statusScolarite) {
        this.statusScolarite = statusScolarite;
    }

    public Personne getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Personne idPersonne) {
        this.idPersonne = idPersonne;
    }

}