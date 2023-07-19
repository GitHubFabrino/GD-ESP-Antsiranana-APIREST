package com.appli.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "matiereconcourstci")
public class Matiereconcourstci {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_MCTCI", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "nom_MCTCI", length = 50)
    private String nomMCTCI;

    @Column(name = "credit_MCTCI")
    private Byte creditMCTCI;
/*
    @OneToMany(mappedBy = "idMctci")
    private Set<Notematiereconcourstci> notematiereconcourstcis = new LinkedHashSet<>();

 */

    @OneToMany(mappedBy = "idMCTCI")
    private Set<Calendrierconcourstci> calendrierconcourstcis = new LinkedHashSet<>();

    public Set<Calendrierconcourstci> getCalendrierconcourstcis() {
        return calendrierconcourstcis;
    }

    public void setCalendrierconcourstcis(Set<Calendrierconcourstci> calendrierconcourstcis) {
        this.calendrierconcourstcis = calendrierconcourstcis;
    }
/*
    public Set<Notematiereconcourstci> getNotematiereconcourstcis() {
        return notematiereconcourstcis;
    }

    public void setNotematiereconcourstcis(Set<Notematiereconcourstci> notematiereconcourstcis) {
        this.notematiereconcourstcis = notematiereconcourstcis;
    }

 */

    public Matiereconcourstci() {
    }

    public Matiereconcourstci(String nomMCTCI, Byte creditMCTCI) {
        this.nomMCTCI = nomMCTCI;
        this.creditMCTCI = creditMCTCI;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getNomMCTCI() {
        return nomMCTCI;
    }

    public void setNomMCTCI(String NomMCTCI) {
        this.nomMCTCI = NomMCTCI;
    }

    public Byte getCreditMCTCI() {
        return creditMCTCI;
    }

    public void setCreditMCTCI(Byte creditMCTCI) {
        this.creditMCTCI = creditMCTCI;
    }

}