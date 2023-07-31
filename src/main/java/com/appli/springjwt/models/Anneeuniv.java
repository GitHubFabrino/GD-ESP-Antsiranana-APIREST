package com.appli.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "anneeuniv")
public class Anneeuniv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_AU", nullable = false)
    private Integer id;
    @Size(max = 50)
    @Column(name = "nom_AU", length = 50)
    private String nomAU;
    @Column(name = "debut_AU", nullable = false)
    private LocalDate debutAU;
    @Column(name = "fin_AU")
    private LocalDate finAU;

    @OneToMany(mappedBy = "idAU", orphanRemoval = true)
    private Set<Definitiondroit> definitiondroits = new LinkedHashSet<>();
    @OneToMany(mappedBy = "idAu", orphanRemoval = true)
    private Set<Definitionparcour> definitionparcours = new LinkedHashSet<>();
    @OneToMany(mappedBy = "idAu", orphanRemoval = true)
    private Set<Inscriptionadministrative> inscriptionadministratives = new LinkedHashSet<>();
    @OneToMany(mappedBy = "idAu", orphanRemoval = true)
    private Set<Definitionmention> definitionmentions = new LinkedHashSet<>();

    @Column(name = "validite_SP")
    private Boolean validiteSp;

    @Column(name = "validite_note")
    private Boolean validiteNote;

    @OneToMany(mappedBy = "idAu", orphanRemoval = true)
    private Set<Heureenseignement> heureenseignements = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idAu", orphanRemoval = true)
    private Set<Autorisationinscriptiona> autorisationinscriptionas = new LinkedHashSet<>();

    public Set<Autorisationinscriptiona> getAutorisationinscriptionas() {
        return autorisationinscriptionas;
    }

    public void setAutorisationinscriptionas(Set<Autorisationinscriptiona> autorisationinscriptionas) {
        this.autorisationinscriptionas = autorisationinscriptionas;
    }

    public Set<Heureenseignement> getHeureenseignements() {
        return heureenseignements;
    }

    public void setHeureenseignements(Set<Heureenseignement> heureenseignements) {
        this.heureenseignements = heureenseignements;
    }

    public Boolean getValiditeNote() {
        return validiteNote;
    }

    public void setValiditeNote(Boolean validiteNote) {
        this.validiteNote = validiteNote;
    }

    public Boolean getValiditeSp() {
        return validiteSp;
    }

    public void setValiditeSp(Boolean validiteSp) {
        this.validiteSp = validiteSp;
    }


    public Set<Definitionmention> getDefinitionmentions() {
        return definitionmentions;
    }

    public void setDefinitionmentions(Set<Definitionmention> definitionmentions) {
        this.definitionmentions = definitionmentions;
    }

    public Set<Inscriptionadministrative> getInscriptionadministratives() {
        return inscriptionadministratives;
    }

    public void setInscriptionadministratives(Set<Inscriptionadministrative> inscriptionadministratives) {
        this.inscriptionadministratives = inscriptionadministratives;
    }

    public Set<Definitionparcour> getDefinitionparcours() {
        return definitionparcours;
    }

    public void setDefinitionparcours(Set<Definitionparcour> definitionparcours) {
        this.definitionparcours = definitionparcours;
    }

    public Set<Definitiondroit> getDefinitiondroits() {
        return definitiondroits;
    }

    public void setDefinitiondroits(Set<Definitiondroit> definitiondroits) {
        this.definitiondroits = definitiondroits;
    }

    public String getNomAU() {
        return nomAU;
    }
    public void setNomAU(String nomAU) {
        this.nomAU = nomAU;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public LocalDate getDebutAU() {
        return debutAU;
    }
    public void setDebutAU(LocalDate debutAU) {
        this.debutAU = debutAU;
    }
    public LocalDate getFinAU() {
        return finAU;
    }
    public void setFinAU(LocalDate finAU) {
        this.finAU = finAU;
    }

    @Override
    public String toString() {
        return "Anneeuniv{" +
                "id=" + id +
                '}';
    }
}