package com.appli.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "candidatconcourstci")
public class Candidatconcourstci {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_candidatCTCI", nullable = false)
    private Integer id;

    @Column(name = "numero_candidatCTCI")
    private Long numeroCandidatCTCI;


    @Column(name = "passation_candidatCTCI")
    private Boolean passationCandidatCTCI;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_personne", nullable = false)
    private Personne idPersonne;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_centreCTCI", nullable = false)
    private Centreconcourstci idCentreCTCI;

    @OneToMany(mappedBy = "idCandidatctci", orphanRemoval = true)
    private Set<Notematiereconcourstci> notematiereconcourstcis = new LinkedHashSet<>();

    //todo ajout attente
    @Column(name = "passation_candidatCTCIAttente")
    private Boolean passationCandidatCTCIAttente;



    public Candidatconcourstci() {
    }

    public Candidatconcourstci(Long numeroCandidatCTCI, Boolean passationCandidatCTCI, Personne idPersonne, Centreconcourstci idCentreCTCI) {
        this.numeroCandidatCTCI = numeroCandidatCTCI;
        this.passationCandidatCTCI = passationCandidatCTCI;
        this.idPersonne = idPersonne;
        this.idCentreCTCI = idCentreCTCI;
    }

    public Candidatconcourstci(Long numeroCandidatCTCI, Personne idPersonne, Centreconcourstci idCentreCTCI) {
        this.numeroCandidatCTCI = numeroCandidatCTCI;
        this.idPersonne = idPersonne;
        this.idCentreCTCI = idCentreCTCI;
    }

    public Candidatconcourstci(Integer id, Long numeroCandidatCTCI, Boolean passationCandidatCTCI, Personne idPersonne, Centreconcourstci idCentreCTCI, Set<Notematiereconcourstci> notematiereconcourstcis, Boolean passationCandidatCTCIAttente) {
        this.id = id;
        this.numeroCandidatCTCI = numeroCandidatCTCI;
        this.passationCandidatCTCI = passationCandidatCTCI;
        this.idPersonne = idPersonne;
        this.idCentreCTCI = idCentreCTCI;
        this.notematiereconcourstcis = notematiereconcourstcis;
        this.passationCandidatCTCIAttente = passationCandidatCTCIAttente;
    }

    public Candidatconcourstci(Long numeroCandidatCTCI, Boolean passationCandidatCTCI, Personne idPersonne, Centreconcourstci idCentreCTCI, Set<Notematiereconcourstci> notematiereconcourstcis, Boolean passationCandidatCTCIAttente) {
        this.numeroCandidatCTCI = numeroCandidatCTCI;
        this.passationCandidatCTCI = passationCandidatCTCI;
        this.idPersonne = idPersonne;
        this.idCentreCTCI = idCentreCTCI;
        this.notematiereconcourstcis = notematiereconcourstcis;
        this.passationCandidatCTCIAttente = passationCandidatCTCIAttente;
    }

    public Boolean getPassationCandidatCTCIAttente() {
        return passationCandidatCTCIAttente;
    }

    public void setPassationCandidatCTCIAttente(Boolean passationCandidatCTCIAttente) {
        this.passationCandidatCTCIAttente = passationCandidatCTCIAttente;
    }

    public Set<Notematiereconcourstci> getNotematiereconcourstcis() {
    return notematiereconcourstcis;
}

    public void setNotematiereconcourstcis(Set<Notematiereconcourstci> notematiereconcourstcis) {
        this.notematiereconcourstcis = notematiereconcourstcis;
    }

    public Centreconcourstci getIdCentreCTCI() {
        return idCentreCTCI;
    }

    public void setIdCentreCTCI(Centreconcourstci idCentreCTCI) {
        this.idCentreCTCI = idCentreCTCI;
    }

    public Personne getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Personne idPersonne) {
        this.idPersonne = idPersonne;
    }

    public Boolean getPassationCandidatCTCI() {
        return passationCandidatCTCI;
    }

    public void setPassationCandidatCTCI(Boolean passationCandidatCTCI) {
        this.passationCandidatCTCI = passationCandidatCTCI;
    }


    public Long getNumeroCandidatCTCI() {
        return numeroCandidatCTCI;
    }

    public void setNumeroCandidatCTCI(Long numeroCandidatCTCI) {
        this.numeroCandidatCTCI = numeroCandidatCTCI;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    //TODO [JPA Buddy] generate columns from DB
}