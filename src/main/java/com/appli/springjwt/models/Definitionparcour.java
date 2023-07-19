package com.appli.springjwt.models;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "definitionparcours")
public class Definitionparcour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_DP", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_niveau")
    private Niveau idNiveau;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_DM")
    private Definitionmention idDm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_semestre")
    private Semestre idSemestre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_parcours")
    private Parcour idParcours;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_AU")
    private Anneeuniv idAu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_enseignant")
    private Enseignant idEnseignant;

    @OneToMany(mappedBy = "idDp")
    private Set<Programmeenseignement> programmeenseignements = new LinkedHashSet<>();

    public Set<Programmeenseignement> getProgrammeenseignements() {
        return programmeenseignements;
    }

    public void setProgrammeenseignements(Set<Programmeenseignement> programmeenseignements) {
        this.programmeenseignements = programmeenseignements;
    }


    public Definitionparcour() {
    }

    public Enseignant getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(Enseignant idEnseignant) {
        this.idEnseignant = idEnseignant;
    }


    public Anneeuniv getIdAu() {
        return idAu;
    }

    public void setIdAu(Anneeuniv idAu) {
        this.idAu = idAu;
    }

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

    public Definitionmention getIdDm() {
        return idDm;
    }

    public void setIdDm(Definitionmention idDm) {
        this.idDm = idDm;
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

}