package com.appli.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

//@NamedEntityGraph(name="centre.idCtci", attributeNodes = @NamedAttributeNode("idCTCI"))
@Entity
@Table(name = "centreconcourstci")
public class Centreconcourstci {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_centreCTCI", nullable = true)
    private Integer id;

    @Size(max = 50)
    @Column(name = "nom_centreCTCI", length = 50)
    private String nomCentreCTCI;

    @Column(name = "code_postale")
    private Integer codePostale;

    //@NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_personne", nullable = true)
    private Personne idPersonne;

    //@NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_CTCI", nullable = true)
    private Concourstci idCTCI;

    @OneToMany(mappedBy = "idCentreCTCI", orphanRemoval = true)
    private Set<Candidatconcourstci> candidatconcourstcis = new LinkedHashSet<>();

    public Centreconcourstci(String nomCentreCTCI, Integer codePostale, Personne idPersonne, Concourstci idCTCI, Set<Candidatconcourstci> candidatconcourstcis) {
        this.nomCentreCTCI = nomCentreCTCI;
        this.codePostale = codePostale;
        this.idPersonne = idPersonne;
        this.idCTCI = idCTCI;
        this.candidatconcourstcis = candidatconcourstcis;
    }

    public Centreconcourstci(Integer id, String nomCentreCTCI, Integer codePostale, Personne idPersonne, Concourstci idCTCI, Set<Candidatconcourstci> candidatconcourstcis) {
        this.id = id;
        this.nomCentreCTCI = nomCentreCTCI;
        this.codePostale = codePostale;
        this.idPersonne = idPersonne;
        this.idCTCI = idCTCI;
        this.candidatconcourstcis = candidatconcourstcis;
    }

    public Centreconcourstci(
            String nomCentreCTCI,
            Integer codePostale,
            Concourstci concours,
            Integer idPersonne) {
    }
    /*
    public Centreconcourstci(
            String nomCentreCTCI,
            Integer codePostale,
            Personne personne,
            Concourstci concours,
            Integer idPersonne) {
    }
    public Centreconcourstci(
            String nomCentreCTCI,
            Integer codePostale,
            Concourstci concours,
            Integer idPersonne) {
    }*/


    public Personne getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Personne idPersonne) {
        this.idPersonne = idPersonne;
    }


    public Set<Candidatconcourstci> getCandidatconcourstcis() {
        return candidatconcourstcis;
    }

    public void setCandidatconcourstcis(Set<Candidatconcourstci> candidatconcourstcis) {
        this.candidatconcourstcis = candidatconcourstcis;
    }


    public Integer getCodePostale() {
        return codePostale;
    }

    public void setCodePostale(Integer codePostale) {
        this.codePostale = codePostale;
    }

    public Concourstci getIdCTCI() {
        return idCTCI;
    }

    public void setIdCTCI(Concourstci idCTCI) {
        this.idCTCI = idCTCI;
    }

    public Centreconcourstci() {
    }

    public Centreconcourstci(String nomCentreCTCI, Integer codePostale, Personne idPersonne, Concourstci idCTCI) {
        this.nomCentreCTCI = nomCentreCTCI;
        this.codePostale = codePostale;
        this.idPersonne = idPersonne;
        this.idCTCI = idCTCI;
    }
/*
    public Centreconcourstci(String nomCentreCTCI, Integer codePostale, Chefcentreconcourstci idChefCentreCTCI, Concourstci idCTCI) {
        this.nomCentreCTCI = nomCentreCTCI;
        this.idChefCentreCTCI = idChefCentreCTCI;
        this.idCTCI = idCTCI;
        this.codePostale = codePostale;
    }

 */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomCentreCTCI() {
        return nomCentreCTCI;
    }

    public void setNomCentreCTCI(String nomCentrectci) {
        this.nomCentreCTCI = nomCentreCTCI;
    }

}