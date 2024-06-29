package com.appli.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

//@NamedEntityGraph(name="personne.idEmail", attributeNodes = @NamedAttributeNode("idEmail"))
@Entity
@Table(name = "personne")
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personne")
    private Integer id;

    @Size(max = 50)
    //@NotNull
    @Column(name = "nom", nullable = true, length = 50)
    private String nom;

    @Size(max = 50)
    @Column(name = "prenoms", length = 50)
    private String prenoms;

    @Size(max = 10)
    @Column(name = "sexe", length = 10)
    private String sexe;

    private LocalDate dateNaissance;

    @Size(max = 50)
    private String lieuNaissance;

    @Size(max = 50)
    private String villeNaissance;

    @Size(max = 50)
    private String paysNaissance;

    @Size(max = 50)
    @Column(name = "nationalite", length = 50)
    private String nationalite;

    @Size(max = 50)
    @Column(name = "adresse", length = 50)
    private String adresse;

    @Size(max = 12)
    @Column(name = "NUMERO_CIN", length = 12)
    private String numeroCIN;

    @Column(name = "DATE_DELIVRE_CIN")
    private LocalDate dateDelivreCIN;

    @Size(max = 50)
    @Column(name = "VILLE_DELIVRE_CIN", length = 50)
    private String villeDelivreCIN;

    @Size(max = 50)
    @Column(name = "affiliation1", length = 50)
    private String affiliation1;

    @Size(max = 50)
    @Column(name = "affiliation2", length = 50)
    private String affiliation2;

    @Column(name = "ANNEE_ENTREE", nullable = false)
    private Short anneeEntree;

    @Size(max = 50)
    @Column(name = "telephone", length = 50)
    private String telephone;

    @OneToOne(mappedBy = "idPersonne")
    private Direction direction;

    @OneToOne(mappedBy = "idPersonne", orphanRemoval = true)
    private Authentification authentification;

    @Size(max = 200)
    @Column(name = "email", length = 200)
    private String email;

    @OneToOne(mappedBy = "idPersonne", orphanRemoval = true)
    private Enseignant enseignant;

    @OneToOne(mappedBy = "idPersonne")
    private Scolarite scolarite;


    @Column(name = "etude1")
    private String etude1;
    @Column(name = "etude2")
    private String etude2;
    @Column(name = "etude3")
    private String etude3;

    @Column(name = "annee1")
    private String annee1;
    @Column(name = "annee2")
    private String annee2;
    @Column(name = "annee3")
    private String annee3;

    public String getEtude1() {
        return etude1;
    }

    public void setEtude1(String etude1) {
        this.etude1 = etude1;
    }

    public String getEtude2() {
        return etude2;
    }

    public void setEtude2(String etude2) {
        this.etude2 = etude2;
    }

    public String getEtude3() {
        return etude3;
    }

    public void setEtude3(String etude3) {
        this.etude3 = etude3;
    }

    public String getAnnee1() {
        return annee1;
    }

    public void setAnnee1(String annee1) {
        this.annee1 = annee1;
    }

    public String getAnnee2() {
        return annee2;
    }

    public void setAnnee2(String annee2) {
        this.annee2 = annee2;
    }

    public String getAnnee3() {
        return annee3;
    }

    public void setAnnee3(String annee3) {
        this.annee3 = annee3;
    }

    public Scolarite getScolarite() {
        return scolarite;
    }

    public void setScolarite(Scolarite scolarite) {
        this.scolarite = scolarite;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public Personne() {
    }

    public Personne(String nom, String prenoms, String telephone) {
        this.nom = nom;
        this.prenoms = prenoms;
        this.telephone = telephone;
    }

    public Personne(Integer id, String nom, String prenoms, String telephone) {
        this.id = id;
        this.nom = nom;
        this.prenoms = prenoms;
        this.telephone = telephone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getVilleNaissance() {
        return villeNaissance;
    }

    public void setVilleNaissance(String villeNaissance) {
        this.villeNaissance = villeNaissance;
    }

    public String getPaysNaissance() {
        return paysNaissance;
    }

    public void setPaysNaissance(String paysNaissance) {
        this.paysNaissance = paysNaissance;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumeroCIN() {
        return numeroCIN;
    }

    public void setNumeroCIN(String numeroCIN) {
        this.numeroCIN = numeroCIN;
    }

    public LocalDate getDateDelivreCIN() {
        return dateDelivreCIN;
    }

    public void setDateDelivreCIN(LocalDate dateDelivreCIN) {
        this.dateDelivreCIN = dateDelivreCIN;
    }

    public String getVilleDelivreCIN() {
        return villeDelivreCIN;
    }

    public void setVilleDelivreCIN(String villeDelivreCIN) {
        this.villeDelivreCIN = villeDelivreCIN;
    }

    public String getAffiliation1() {
        return affiliation1;
    }

    public void setAffiliation1(String affiliation1) {
        this.affiliation1 = affiliation1;
    }

    public String getAffiliation2() {
        return affiliation2;
    }

    public void setAffiliation2(String affiliation2) {
        this.affiliation2 = affiliation2;
    }

    public Short getAnneeEntree() {
        return anneeEntree;
    }

    public void setAnneeEntree(Short anneeEntree) {
        this.anneeEntree = anneeEntree;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Authentification getAuthentification() {
        return authentification;
    }

    public void setAuthentification(Authentification authentification) {
        this.authentification = authentification;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenoms='" + prenoms + '\'' +
                '}';
    }
}