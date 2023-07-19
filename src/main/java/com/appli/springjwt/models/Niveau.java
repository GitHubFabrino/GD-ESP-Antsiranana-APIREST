package com.appli.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "niveau")
public class Niveau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_niveau", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "niveau", length = 50)
    private String niveau;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

}