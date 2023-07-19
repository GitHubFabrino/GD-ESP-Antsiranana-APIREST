package com.appli.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "uniteenseignement")
public class Uniteenseignement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_UE", nullable = false)
    private Integer id;
    @Column(name = "code_UE")
    private String codeUe;
    @Size(max = 100)
    @Column(name = "nom_UE", length = 100)
    private String nomUe;
    @Column(name = "credit_UE")
    private BigDecimal creditUe;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_enseignant")
    private Enseignant idEnseignant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_DP")
    private Definitionparcour idDp;

    public Definitionparcour getIdDp() {
        return idDp;
    }

    public void setIdDp(Definitionparcour idDp) {
        this.idDp = idDp;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeUe() {
        return codeUe;
    }

    public void setCodeUe(String codeUe) {
        this.codeUe = codeUe;
    }

    public String getNomUe() {
        return nomUe;
    }

    public void setNomUe(String nomUe) {
        this.nomUe = nomUe;
    }

    public BigDecimal getCreditUe() {
        return creditUe;
    }

    public void setCreditUe(BigDecimal creditUe) {
        this.creditUe = creditUe;
    }

    public Enseignant getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(Enseignant idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

}