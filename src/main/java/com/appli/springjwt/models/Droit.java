package com.appli.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "droit")
public class Droit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_droit", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "montant", nullable = false)
    private Long montant;

    @Size(max = 50)
    @NotNull
    @Column(name = "nom_banque", nullable = false, length = 50)
    private String nomBanque;

    @Size(max = 200)
    @Column(name = "numero_compte", length = 200)
    private String numeroCompte;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getMontant() {
        return montant;
    }

    public void setMontant(Long montant) {
        this.montant = montant;
    }

    public String getNomBanque() {
        return nomBanque;
    }

    public void setNomBanque(String nomBanque) {
        this.nomBanque = nomBanque;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

}