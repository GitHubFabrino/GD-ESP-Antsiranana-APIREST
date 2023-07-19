package com.appli.springjwt.models;

import javax.persistence.*;

@Entity
@Table(name = "inscriptionadministrative")
public class Inscriptionadministrative {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_IA", nullable = false)
    private Integer id;

    @Column(name = "validite_IA")
    private Boolean validiteIa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_etudiant")
    private Etudiant idEtudiant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_niveau")
    private Niveau idNiveau;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_AU")
    private Anneeuniv idAu;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getValiditeIa() {
        return validiteIa;
    }

    public void setValiditeIa(Boolean validiteIa) {
        this.validiteIa = validiteIa;
    }

    public Etudiant getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(Etudiant idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public Niveau getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(Niveau idNiveau) {
        this.idNiveau = idNiveau;
    }

    public Anneeuniv getIdAu() {
        return idAu;
    }

    public void setIdAu(Anneeuniv idAu) {
        this.idAu = idAu;
    }

}