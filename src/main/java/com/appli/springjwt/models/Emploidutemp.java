package com.appli.springjwt.models;

import javax.persistence.*;

@Entity
@Table(name = "emploidutemps")
public class Emploidutemp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_EDT", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_EC")
    private Elementconstitutif idEc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Elementconstitutif getIdEc() {
        return idEc;
    }

    public void setIdEc(Elementconstitutif idEc) {
        this.idEc = idEc;
    }

    //TODO [JPA Buddy] generate columns from DB
}