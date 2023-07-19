package com.appli.springjwt.models;

import javax.persistence.*;

@Entity
@Table(name = "offreformation")
public class Offreformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_OF", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_niveau")
    private Niveau idNiveau;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_AU")
    private Anneeuniv idAu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_semestre")
    private Semestre idSemestre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_parcours")
    private Parcour idParcours;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mention")
    private Mention idMention;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Niveau getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(Niveau idNiveau) {
        this.idNiveau = idNiveau;
    }

    public Anneeuniv getIdAu() {
        return idAu;
    }

    public void setIdAu(Anneeuniv idAu) {
        this.idAu = idAu;
    }

    public Semestre getIdSemestre() {
        return idSemestre;
    }

    public void setIdSemestre(Semestre idSemestre) {
        this.idSemestre = idSemestre;
    }

    public Parcour getIdParcours() {
        return idParcours;
    }

    public void setIdParcours(Parcour idParcours) {
        this.idParcours = idParcours;
    }

    public Mention getIdMention() {
        return idMention;
    }

    public void setIdMention(Mention idMention) {
        this.idMention = idMention;
    }

}