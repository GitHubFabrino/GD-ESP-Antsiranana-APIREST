package com.appli.springjwt.models;

import javax.persistence.*;

@Entity
@Table(name = "definitionpresidentjury")
public class PresidentJuryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_PDJ", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_AU")
    private Anneeuniv idAu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_CTCI")
    /*@Column(name = "id_CTCI")*/
    private Concourstci idCTCI;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_enseignant")
    private Enseignant idEnseignant;

    public PresidentJuryModel() {
    }

    public PresidentJuryModel(Integer id, Anneeuniv idAu, Concourstci id_CTCI, Enseignant idEnseignant) {
        this.id = id;
        this.idAu = idAu;
        this.idCTCI = id_CTCI;
        this.idEnseignant = idEnseignant;
    }

    public PresidentJuryModel(Anneeuniv idAu, Concourstci id_CTCI, Enseignant idEnseignant) {
        this.idAu = idAu;
        this.idCTCI = id_CTCI;
        this.idEnseignant = idEnseignant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Anneeuniv getIdAu() {
        return idAu;
    }

    public void setIdAu(Anneeuniv idAu) {
        this.idAu = idAu;
    }

    public Concourstci getId_CTCI() {
        return idCTCI;
    }

    public void setId_CTCI(Concourstci id_CTCI) {
        this.idCTCI = id_CTCI;
    }

    public Enseignant getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(Enseignant idEnseignant) {
        this.idEnseignant = idEnseignant;
    }
}
