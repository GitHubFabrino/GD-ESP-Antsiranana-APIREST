package com.appli.springjwt.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "authentification")
public class Authentification {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_authentification", nullable = false)
  private Long id;
  @Column(name = "pseudo")
  private String username;

  @Column(name = "MOT_DE_PASSE")
  private String password;

  @OneToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "id_personne")
  private Personne idPersonne;

  @Column(name = "activation")
  private Boolean activation;

  public Personne getIdPersonne() {
    return idPersonne;
  }

  public void setIdPersonne(Personne idPersonne) {
    this.idPersonne = idPersonne;
  }

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "status",
          joinColumns = @JoinColumn(name = "id_authentification"),
          inverseJoinColumns = @JoinColumn(name = "id_fonction"))
  private Set<Fonction> fonctions = new HashSet<>();

  public Boolean getActivation() {
    return activation;
  }

  public void setActivation(Boolean activation) {
    this.activation = activation;
  }

  public Authentification() {
  }

  public Authentification(String username, String password, Personne idPersonne, Set<Fonction> fonctions) {
    this.username = username;
    this.password = password;
    this.idPersonne = idPersonne;
    this.fonctions = fonctions;
  }

  public Authentification(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Fonction> getRoles() {
    return fonctions;
  }

  public void setRoles(Set<Fonction> fonctions) {
    this.fonctions = fonctions;
  }
}
