package com.appli.springjwt.models;

import javax.persistence.*;

@Entity
@Table(name = "heureenseignement")
public class Heureenseignement {
    @EmbeddedId
    private HeureenseignementId id = new HeureenseignementId();

    @MapsId("idAu")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_AU", nullable = false)
    private Anneeuniv idAu;

    @MapsId("idEnseignant")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_enseignant", nullable = false)
    private Enseignant idEnseignant;

    @MapsId("idPe")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_PE", nullable = false)
    private Programmeenseignement idPe;

    @Column(name = "comptage_heure_ET")
    private Integer comptageHeureEt;

    @Column(name = "comptage_heure_ED")
    private Integer comptageHeureEd;

    @Column(name = "comptage_heure_TP")
    private Integer comptageHeureTp;

    @Column(name = "comptage_heure_autre")
    private Integer comptageHeureAutre;

    @Column(name = "comptage_heure_total")
    private Integer comptageHeureTotal;

    public HeureenseignementId getId() {
        return id;
    }

    public void setId(HeureenseignementId id) {
        this.id = id;
    }

    public Anneeuniv getIdAu() {
        return idAu;
    }

    public void setIdAu(Anneeuniv idAu) {
        this.idAu = idAu;
    }

    public Enseignant getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(Enseignant idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    public Programmeenseignement getIdPe() {
        return idPe;
    }

    public void setIdPe(Programmeenseignement idPe) {
        this.idPe = idPe;
    }

    public Integer getComptageHeureEt() {
        return comptageHeureEt;
    }

    public void setComptageHeureEt(Integer comptageHeureEt) {
        this.comptageHeureEt = comptageHeureEt;
    }

    public Integer getComptageHeureEd() {
        return comptageHeureEd;
    }

    public void setComptageHeureEd(Integer comptageHeureEd) {
        this.comptageHeureEd = comptageHeureEd;
    }

    public Integer getComptageHeureTp() {
        return comptageHeureTp;
    }

    public void setComptageHeureTp(Integer comptageHeureTp) {
        this.comptageHeureTp = comptageHeureTp;
    }

    public Integer getComptageHeureAutre() {
        return comptageHeureAutre;
    }

    public void setComptageHeureAutre(Integer comptageHeureAutre) {
        this.comptageHeureAutre = comptageHeureAutre;
    }

    public Integer getComptageHeureTotal() {
        return comptageHeureTotal;
    }

    public void setComptageHeureTotal(Integer comptageHeureTotal) {
        this.comptageHeureTotal = comptageHeureTotal;
    }

}