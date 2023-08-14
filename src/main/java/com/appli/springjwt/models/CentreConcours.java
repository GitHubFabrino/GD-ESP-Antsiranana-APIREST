package com.appli.springjwt.models;

import javax.persistence.*;

@Entity
@Table(name = "centre_concours")
public class CentreConcours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_centre_concours", nullable = false)
    private Integer id_centre_concours;

    @Column(name = "nom_centre_concours")
    private String nom_centre_concours;

    @Column(name = "code_postal_centre")
    private String code_postal_centre;

    public CentreConcours() {
    }

    public CentreConcours(Integer id_centre_concours, String nom_centre_concours, String code_postal_centre) {
        this.id_centre_concours = id_centre_concours;
        this.nom_centre_concours = nom_centre_concours;
        this.code_postal_centre = code_postal_centre;
    }

    public CentreConcours(String nom_centre_concours, String code_postal_centre) {
        this.nom_centre_concours = nom_centre_concours;
        this.code_postal_centre = code_postal_centre;
    }

    public Integer getId_centre_concours() {
        return id_centre_concours;
    }

    public void setId_centre_concours(Integer id_centre_concours) {
        this.id_centre_concours = id_centre_concours;
    }

    public String getNom_centre_concours() {
        return nom_centre_concours;
    }

    public void setNom_centre_concours(String nom_centre_concours) {
        this.nom_centre_concours = nom_centre_concours;
    }

    public String getCode_postal_centre() {
        return code_postal_centre;
    }

    public void setCode_postal_centre(String code_postal_centre) {
        this.code_postal_centre = code_postal_centre;
    }

    @Override
    public String toString() {
        return "CentreConcours{" +
                "nom_centre_concours='" + nom_centre_concours + '\'' +
                ", code_postal_centre='" + code_postal_centre + '\'' +
                '}';
    }
}
