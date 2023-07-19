package com.appli.springjwt.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "ue_ec")
public class UeEc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_UE_EC", nullable = false)
    private Integer id;

    @Column(name = "credit_EC")
    private BigDecimal creditEc;

    @Column(name = "coefficient_EC")
    private BigDecimal coefficientEc;

    @Column(name = "volume_horaire_ET")
    private Integer volumeHoraireEt;

    @Column(name = "volume_horaire_ED")
    private Integer volumeHoraireEd;

    @Column(name = "volume_horaire_TP")
    private Integer volumeHoraireTp;

    @Column(name = "volume_horaire_autre")
    private Integer volumeHoraireAutre;

    @Column(name = "volume_horaire_total")
    private Integer volumeHoraireTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_EC")
    private Elementconstitutif idEc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_UE")
    private Uniteenseignement idUe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_enseignant")
    private Enseignant idEnseignant;

    @OneToMany(mappedBy = "idUeEc", orphanRemoval = true)
    private Set<Programmeenseignement> programmeenseignements = new LinkedHashSet<>();


    public UeEc() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getCreditEc() {
        return creditEc;
    }

    public void setCreditEc(BigDecimal creditEc) {
        this.creditEc = creditEc;
    }
    public BigDecimal getCoefficientEc() {
        return coefficientEc;
    }

    public void setCoefficientEc(BigDecimal coefficientEc) {
        this.coefficientEc = coefficientEc;
    }

    public Integer getVolumeHoraireEt() {
        return volumeHoraireEt;
    }

    public void setVolumeHoraireEt(Integer volumeHoraireEt) {
        this.volumeHoraireEt = volumeHoraireEt;
    }

    public Integer getVolumeHoraireEd() {
        return volumeHoraireEd;
    }

    public void setVolumeHoraireEd(Integer volumeHoraireEd) {
        this.volumeHoraireEd = volumeHoraireEd;
    }

    public Integer getVolumeHoraireTp() {
        return volumeHoraireTp;
    }

    public void setVolumeHoraireTp(Integer volumeHoraireTp) {
        this.volumeHoraireTp = volumeHoraireTp;
    }

    public Integer getVolumeHoraireAutre() {
        return volumeHoraireAutre;
    }

    public void setVolumeHoraireAutre(Integer volumeHoraireAutre) {
        this.volumeHoraireAutre = volumeHoraireAutre;
    }

    public Integer getVolumeHoraireTotal() {
        return volumeHoraireTotal;
    }

    public void setVolumeHoraireTotal(Integer volumeHoraireTotal) {
        this.volumeHoraireTotal = volumeHoraireTotal;
    }

    public Elementconstitutif getIdEc() {
        return idEc;
    }

    public void setIdEc(Elementconstitutif idEc) {
        this.idEc = idEc;
    }

    public Uniteenseignement getIdUe() {
        return idUe;
    }
    public void setIdUe(Uniteenseignement idUe) {
        this.idUe = idUe;
    }

    public Enseignant getIdEnseignant() {
        return idEnseignant;
    }
    public void setIdEnseignant(Enseignant idEnseignant) {
        this.idEnseignant = idEnseignant;
    }


}