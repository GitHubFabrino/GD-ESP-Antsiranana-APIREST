package com.appli.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "fonction")
public class Fonction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_fonction", nullable = false)
  private Integer id;

  @Size(max = 50)
  @Enumerated(EnumType.STRING)
  @Column(name = "fonction", nullable = false)
  private ERole name;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Fonction() {
  }

  public ERole getName() {
    return name;
  }

  public void setName(ERole name) {this.name = name;}
}

