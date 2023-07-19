package com.appli.springjwt.models;

import javax.persistence.*;

@Entity
@Table(name = "resultatfinau")
public class Resultatfinau {
    @EmbeddedId
    private ResultatfinauId id = new ResultatfinauId();

    @MapsId("idEtudiant")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_etudiant", nullable = false)
    private Etudiant idEtudiant;

    @MapsId("idDp")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_DP", nullable = false)
    private Definitionparcour idDp;

    @Column(name = "code_redoublement")
    private Byte codeRedoublement;

    public Byte getCodeRedoublement() {
        return codeRedoublement;
    }

    public void setCodeRedoublement(Byte codeRedoublement) {
        this.codeRedoublement = codeRedoublement;
    }


    public Resultatfinau() {
    }

    public Resultatfinau(Etudiant idEtudiant, Definitionparcour idDp, Byte codeRedoublement) {
        this.idEtudiant = idEtudiant;
        this.idDp = idDp;
        this.codeRedoublement = codeRedoublement;
    }

    public ResultatfinauId getId() {
        return id;
    }

    public void setId(ResultatfinauId id) {
        this.id = id;
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



}