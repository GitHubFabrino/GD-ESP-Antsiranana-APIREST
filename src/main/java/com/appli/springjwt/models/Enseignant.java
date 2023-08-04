package com.appli.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "enseignant")
public class Enseignant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_enseignant", nullable = false)
    private Integer id;
    @Size(max = 50)
    @Column(name = "numero_matricule", length = 50)
    private String numeroMatricule;
    @Size(max = 50)
    @Column(name = "grade_enseignant", length = 50)
    private String gradeEnseignant;
    @Size(max = 50)
    @Column(name = "status_enseignant", length = 50)
    private String statusEnseignant;
    @Lob
    @Column(name = "specialite")
    private String specialite;
    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_personne", nullable = false)
    private Personne idPersonne;

    @OneToMany(mappedBy = "idEnseignant", orphanRemoval = true)
    private Set<Heureenseignement> heureenseignements = new LinkedHashSet<>();


    public Set<Heureenseignement> getHeureenseignements() {
        return heureenseignements;
    }

    public void setHeureenseignements(Set<Heureenseignement> heureenseignements) {
        this.heureenseignements = heureenseignements;
    }


    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getStatusEnseignant() {
        return statusEnseignant;
    }

    public void setStatusEnseignant(String statusEnseignant) {
        this.statusEnseignant = statusEnseignant;
    }

    public String getGradeEnseignant() {
        return gradeEnseignant;
    }

    public void setGradeEnseignant(String gradeEnseignant) {
        this.gradeEnseignant = gradeEnseignant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroMatricule() {
        return numeroMatricule;
    }

    public void setNumeroMatricule(String numeroMatricule) {
        this.numeroMatricule = numeroMatricule;
    }

    public Personne getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Personne idPersonne) {
        this.idPersonne = idPersonne;
    }

    @Override
    public String toString() {
        return "Enseignant{" +
                "id=" + id +
                ", numeroMatricule='" + numeroMatricule + '\'' +

                '}';
    }
}