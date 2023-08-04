package com.appli.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cursus")
public class Cursus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cursus", nullable = false)
    private Integer id;

    @Column(name = "validite_curcus")
    private Boolean validiteCurcus;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_etudiant", nullable = false)
    private Etudiant idEtudiant;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_DP", nullable = false)
    private Definitionparcour idDp;

    @NotNull
    @Column(name = "validite_IP", nullable = false)
    private Boolean validiteIp = false;

    public Boolean getValiditeIp() {
        return validiteIp;
    }

    public void setValiditeIp(Boolean validiteIp) {
        this.validiteIp = validiteIp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getValiditeCurcus() {
        return validiteCurcus;
    }

    public void setValiditeCurcus(Boolean validiteCurcus) {
        this.validiteCurcus = validiteCurcus;
    }

    public Etudiant getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(Etudiant idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public Definitionparcour getIdDp() {
        return idDp;
    }

    public void setIdDp(Definitionparcour idDp) {
        this.idDp = idDp;
    }

    @Override
    public String toString() {
        return "Cursus{" +
                "id=" + id +
                ", validiteCurcus=" + validiteCurcus +
                ", idEtudiant=" + idEtudiant +
                ", idDp=" + idDp +
                ", validiteIp=" + validiteIp +
                '}';
    }
}