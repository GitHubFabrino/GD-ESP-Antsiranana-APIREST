package com.appli.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "parcours")
public class Parcour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parcours", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "acronyme_parcours", nullable = false )
    private String acronymeParcours;

    @Lob
    @Column(name = "parcours")
    private String parcours;

    public String getParcours() {
        return parcours;
    }

    public void setParcours(String parcours) {
        this.parcours = parcours;
    }

    public String getAcronymeParcours() {
        return acronymeParcours;
    }

    public void setAcronymeParcours(String acronymeParcours) {
        this.acronymeParcours = acronymeParcours;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



}