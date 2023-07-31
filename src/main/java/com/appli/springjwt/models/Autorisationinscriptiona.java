package com.appli.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "autorisationinscriptiona")
public class Autorisationinscriptiona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autorisationIA", nullable = false)
    private Integer id;

    @Column(name = "autorisation")
    private Boolean autorisation;

    @Size(max = 50)
    @Column(name = "numero_recu", length = 50)
    private String numeroRecu;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_personne", nullable = false)
    private Personne idPersonne;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_niveau", nullable = false)
    private Niveau idNiveau;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_AU")
    private Anneeuniv idAu;

    public Anneeuniv getIdAu() {
        return idAu;
    }

    public void setIdAu(Anneeuniv idAu) {
        this.idAu = idAu;
    }

    public Autorisationinscriptiona() {
    }

    public Autorisationinscriptiona(Boolean autorisation, Personne idPersonne, Niveau idNiveau, Anneeuniv idAu) {
        this.autorisation = autorisation;
        this.idPersonne = idPersonne;
        this.idNiveau = idNiveau;
        this.idAu = idAu;
    }

    public Autorisationinscriptiona(Boolean autorisation, Personne idPersonne, Niveau idNiveau) {
        this.autorisation = autorisation;
        this.idPersonne = idPersonne;
        this.idNiveau = idNiveau;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getAutorisation() {
        return autorisation;
    }

    public void setAutorisation(Boolean autorisation) {
        this.autorisation = autorisation;
    }

    public String getNumeroRecu() {
        return numeroRecu;
    }

    public void setNumeroRecu(String numeroRecu) {
        this.numeroRecu = numeroRecu;
    }

    public Personne getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Personne idPersonne) {
        this.idPersonne = idPersonne;
    }

    public Niveau getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(Niveau idNiveau) {
        this.idNiveau = idNiveau;
    }

}