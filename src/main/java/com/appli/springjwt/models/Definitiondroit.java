package com.appli.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "definitiondroit")
public class Definitiondroit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_DD", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_AU", nullable = false)
    private Anneeuniv idAU;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_droit", nullable = false)
    private Droit idDroit;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_niveau", nullable = false)
    private Niveau idNiveau;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Anneeuniv getIdAU() {
        return idAU;
    }

    public void setIdAU(Anneeuniv idAU) {
        this.idAU = idAU;
    }

    public Droit getIdDroit() {
        return idDroit;
    }

    public void setIdDroit(Droit idDroit) {
        this.idDroit = idDroit;
    }

    public Niveau getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(Niveau idNiveau) {
        this.idNiveau = idNiveau;
    }

}