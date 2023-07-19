package com.appli.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "direction")
public class Direction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direction", nullable = false)
    private Integer id;

    @Column(name = "gestion_utilisateur")
    private Boolean gestionUtilisateur;

    @Column(name = "gestion_seminaire_pedagogiques")
    private Boolean gestionSeminairePedagogiques;

    @Size(max = 100)
    @Column(name = "status_direction", length = 100)
    private String statusDirection;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_personne", nullable = false)
    private Personne idPersonne;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getGestionUtilisateur() {
        return gestionUtilisateur;
    }

    public void setGestionUtilisateur(Boolean gestionUtilisateur) {
        this.gestionUtilisateur = gestionUtilisateur;
    }

    public Boolean getGestionSeminairePedagogiques() {
        return gestionSeminairePedagogiques;
    }

    public void setGestionSeminairePedagogiques(Boolean gestionSeminairePedagogiques) {
        this.gestionSeminairePedagogiques = gestionSeminairePedagogiques;
    }

    public String getStatusDirection() {
        return statusDirection;
    }

    public void setStatusDirection(String statusDirection) {
        this.statusDirection = statusDirection;
    }

    public Personne getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Personne idPersonne) {
        this.idPersonne = idPersonne;
    }

}