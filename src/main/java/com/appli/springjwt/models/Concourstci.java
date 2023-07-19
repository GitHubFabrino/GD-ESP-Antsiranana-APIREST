package com.appli.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

/*@NamedEntityGraph(
        name="Concours.sansDes",
        attributeNodes={
                @NamedAttributeNode("id_CTCI"),
                @NamedAttributeNode("session_CTCI"),
                @NamedAttributeNode("annee_CTCI")
        }
)

 */
//@NamedEntityGraph(name="concours.sansDes", attributeNodes = {@NamedAttributeNode("session_CTCI"),@NamedAttributeNode("annee_CTCI")})
@Entity
@Table(name = "concourstci")
public class Concourstci {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_CTCI", nullable = false)
    private Integer id;
    @Size(max = 200)
    @Column(name = "session_CTCI", length = 50)
    private String sessionCTCI;
    @Size(max = 200)
    @Column(name = "description_CTCI", length = 200)
    private String descriptionCTCI;

    /*@OneToMany(mappedBy = "idCTCI")
    private Set<Candidatconcourstci> candidatconcourstcis = new LinkedHashSet<>();

     */
/*
    @OneToMany(mappedBy = "concourstci")
    private Set<Notematiereconcourstci> notematiereconcourstcis = new LinkedHashSet<>();

 */

    @OneToMany(mappedBy = "idCTCI")
    private Set<Calendrierconcourstci> calendrierconcourstcis = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idCTCI")
    private Set<Centreconcourstci> centreconcourstcis = new LinkedHashSet<>();

    public Set<Centreconcourstci> getCentreconcourstcis() {
        return centreconcourstcis;
    }

    public void setCentreconcourstcis(Set<Centreconcourstci> centreconcourstcis) {
        this.centreconcourstcis = centreconcourstcis;
    }

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

    public Concourstci() {
    }

    public Concourstci(String sessionCTCI, String descriptionCTCI) {
        this.sessionCTCI = sessionCTCI;
        this.descriptionCTCI = descriptionCTCI;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSessionCTCI() {
        return sessionCTCI;
    }
    public void setSessionCTCI(String sessionCTCI) {
        this.sessionCTCI = sessionCTCI;
    }
    public String getDescriptionCTCI() {
        return descriptionCTCI;
    }
    public void setDescriptionCTCI(String descriptionCTCI) {
        this.descriptionCTCI = descriptionCTCI;
    }

}