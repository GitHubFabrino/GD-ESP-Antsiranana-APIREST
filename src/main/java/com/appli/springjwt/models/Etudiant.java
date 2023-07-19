package com.appli.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "etudiant")
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_etudiant", nullable = false)
    private Integer id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_personne", nullable = false)
    private Personne idPersonne;

    @Column(name = "numero_matricule")
    private Integer numeroMatricule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bacc")
    private Bacc idBacc;

    @Column(name = "annee_bacc")
    private Short anneeBacc;

    @Size(max = 50)
    @Column(name = "status_etudiant", length = 50)
    private String statusEtudiant;

    @OneToMany(mappedBy = "idEtudiant")
    private Set<Cursus> cursus = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idEtudiant")
    private Set<Inscriptionadministrative> inscriptionadministratives = new LinkedHashSet<>();

    public Set<Inscriptionadministrative> getInscriptionadministratives() {
        return inscriptionadministratives;
    }

    public void setInscriptionadministratives(Set<Inscriptionadministrative> inscriptionadministratives) {
        this.inscriptionadministratives = inscriptionadministratives;
    }


    public Set<Cursus> getCursus() {
        return cursus;
    }

    public void setCursus(Set<Cursus> cursus) {
        this.cursus = cursus;
    }


    public String getStatusEtudiant() {
        return statusEtudiant;
    }

    public void setStatusEtudiant(String statusEtudiant) {
        this.statusEtudiant = statusEtudiant;
    }


    public Bacc getIdBacc() {
        return idBacc;
    }

    public Short getAnneeBacc() {
        return anneeBacc;
    }

    public void setAnneeBacc(Short anneeBacc) {
        this.anneeBacc = anneeBacc;
    }


    public Integer getNumeroMatricule() {
        return numeroMatricule;
    }

    public void setNumeroMatricule(Integer numeroMatricule) {
        this.numeroMatricule = numeroMatricule;
    }

    public void setIdBacc(Bacc idBacc) {
        this.idBacc = idBacc;
    }

    public Personne getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Personne idPersonne) {
        this.idPersonne = idPersonne;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //TODO [JPA Buddy] generate columns from DB
}