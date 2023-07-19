package com.appli.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "bacc")
public class Bacc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bacc", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "bacc", length = 50)
    private String bacc;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBacc() {
        return bacc;
    }
    public void setBacc(String bacc) {
        this.bacc = bacc;
    }

    //TODO [JPA Buddy] generate columns from DB
}