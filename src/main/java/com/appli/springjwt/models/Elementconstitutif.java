package com.appli.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "elementconstitutif")
public class Elementconstitutif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_EC", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "code_EC", length = 50)
    private String codeEc;

    @Size(max = 100)
    @Column(name = "nom_EC", length = 100)
    private String nomEc;

    @OneToMany(mappedBy = "idEc", orphanRemoval = true)
    private Set<UeEc> ueEcs = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idEc", orphanRemoval = true)
    private Set<Emploidutemp> emploidutemps = new LinkedHashSet<>();

    public Set<Emploidutemp> getEmploidutemps() {
        return emploidutemps;
    }

    public void setEmploidutemps(Set<Emploidutemp> emploidutemps) {
        this.emploidutemps = emploidutemps;
    }

    public Set<UeEc> getUeEcs() {
        return ueEcs;
    }

    public void setUeEcs(Set<UeEc> ueEcs) {
        this.ueEcs = ueEcs;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeEc() {
        return codeEc;
    }

    public void setCodeEc(String codeEc) {
        this.codeEc = codeEc;
    }

    public String getNomEc() {
        return nomEc;
    }

    public void setNomEc(String nomEc) {
        this.nomEc = nomEc;
    }

}