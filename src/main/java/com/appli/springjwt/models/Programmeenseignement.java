package com.appli.springjwt.models;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "programmeenseignement")
public class Programmeenseignement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_PE", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_UE_EC")
    private UeEc idUeEc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_DP")
    private Definitionparcour idDp;

    @OneToMany(mappedBy = "idPe", orphanRemoval = true)
    private Set<Heureenseignement> heureenseignements = new LinkedHashSet<>();
    

    public Set<Heureenseignement> getHeureenseignements() {
        return heureenseignements;
    }

    public void setHeureenseignements(Set<Heureenseignement> heureenseignements) {
        this.heureenseignements = heureenseignements;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Definitionparcour getIdDp() {
        return idDp;
    }

    public void setIdDp(Definitionparcour idDp) {
        this.idDp = idDp;
    }

    public UeEc getIdUeEc() {
        return idUeEc;
    }

    public void setIdUeEc(UeEc idUeEc) {
        this.idUeEc = idUeEc;
    }

}