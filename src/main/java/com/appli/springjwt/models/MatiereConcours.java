package com.appli.springjwt.models;


import javax.persistence.*;

@Entity
@Table(name = "matieresConcours")
public class MatiereConcours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matiere_concour", nullable = false)
    private Integer id_matiereConcour;

    @Column(name = "nom_matiere_concour")
    private String nom_matiere_concour;

    public MatiereConcours() {
    }

    public MatiereConcours(Integer id_matiereConcour, String nom_matiere_concour) {
        this.id_matiereConcour = id_matiereConcour;
        this.nom_matiere_concour = nom_matiere_concour;
    }

    public MatiereConcours(String nom_matiere_concour) {
        this.nom_matiere_concour = nom_matiere_concour;
    }

    public Integer getId_matiereConcour() {
        return id_matiereConcour;
    }

    public void setId_matiereConcour(Integer id_matiereConcour) {
        this.id_matiereConcour = id_matiereConcour;
    }

    public String getNom_matiere_concour() {
        return nom_matiere_concour;
    }

    public void setNom_matiere_concour(String nom_matiere_concour) {
        this.nom_matiere_concour = nom_matiere_concour;
    }

    @Override
    public String toString() {
        return "MatiereConcours{" +
                "nom_matiere_concour='" + nom_matiere_concour + '\'' +
                '}';
    }
}
