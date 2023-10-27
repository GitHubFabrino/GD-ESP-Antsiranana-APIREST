package com.appli.springjwt.models;

import javax.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_PersonneAdjoit", nullable = true)
    private Personne idPersonneAdjoit;

    //@NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_CTCI", nullable = true)
    private Concourstci idCTCI;

    @OneToMany(mappedBy = "idCentreCTCI", orphanRemoval = true)
    private Set<Candidatconcourstci> candidatconcourstcis = new LinkedHashSet<>();

    public Centreconcourstci() {
    }

    public Centreconcourstci(String nomCentreCTCI, Integer codePostale, Personne idPersonne, Personne idPersonneAdjoit, Concourstci idCTCI, Set<Candidatconcourstci> candidatconcourstcis) {
        this.nomCentreCTCI = nomCentreCTCI;
        this.codePostale = codePostale;
        this.idPersonne = idPersonne;
        this.idPersonneAdjoit = idPersonneAdjoit;
        this.idCTCI = idCTCI;
        this.candidatconcourstcis = candidatconcourstcis;
    }

    public Centreconcourstci(Integer id, String nomCentreCTCI, Integer codePostale, Personne idPersonne, Personne idPersonneAdjoit, Concourstci idCTCI, Set<Candidatconcourstci> candidatconcourstcis) {
        this.id = id;
        this.nomCentreCTCI = nomCentreCTCI;
        this.codePostale = codePostale;
        this.idPersonne = idPersonne;
        this.idPersonneAdjoit = idPersonneAdjoit;
        this.idCTCI = idCTCI;
        this.candidatconcourstcis = candidatconcourstcis;
    }

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

    public Personne getIdPersonneAdjoit() {
        return idPersonneAdjoit;
    }

    public void setIdPersonneAdjoit(Personne idPersonneAdjoit) {
        this.idPersonneAdjoit = idPersonneAdjoit;
    }

    public Centreconcourstci(String nomCentreCTCI, Integer codePostale, Concourstci concours, Personne idPersonne) {
    }

    public Personne getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Personne idPersonne) {
        this.idPersonne = idPersonne;
    }

    public void setIdPersonneID(Integer idpersonne) {
        // Créez un nouvel objet Concourstci en utilisant l'ID passé en argument
        Personne personne = new Personne();
        personne.setId(idpersonne);
        // Affectez l'objet Concourstci à la propriété idCTCI
        this.idPersonne = personne;
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

    public void setIdCTCIINT(Integer idCTCI) {
        // Créez un nouvel objet Concourstci en utilisant l'ID passé en argument
        Concourstci concours = new Concourstci();
        concours.setId(idCTCI);
        // Affectez l'objet Concourstci à la propriété idCTCI
        this.idCTCI = concours;
    }

    public Centreconcourstci(String nomCentreCTCI, Integer codePostale, Concourstci concours, Personne idPersonne, Personne id_PersonneAdjoit) {
    }

    public Centreconcourstci(String nomCentreCTCI, Integer codePostale, Personne idPersonne, Concourstci idCTCI) {
        this.nomCentreCTCI = nomCentreCTCI;
        this.codePostale = codePostale;
        this.idPersonne = idPersonne;
        this.idCTCI = idCTCI;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomCentreCTCI() {
        return nomCentreCTCI;
    }

    /*public void setNomCentreCTCI(String nomCentrectci) {
        this.nomCentreCTCI = nomCentreCTCI;
    }*/
    public void setNomCentreCTCI(String nomCentrectci) {
        this.nomCentreCTCI = nomCentrectci;
    }

    public void setIdCTCI(Concourstci idCTCI) {
        this.idCTCI = idCTCI;
    }
}