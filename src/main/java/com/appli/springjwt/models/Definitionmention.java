package com.appli.springjwt.models;

import javax.persistence.*;

@Entity
@Table(name = "definitionmention")
public class Definitionmention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_DM", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_AU")
    private Anneeuniv idAu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mention")
    private Mention idMention;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_enseignant")
    private Enseignant idEnseignant;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Anneeuniv getIdAu() {
        return idAu;
    }

    public void setIdAu(Anneeuniv idAu) {
        this.idAu = idAu;
    }

    public Mention getIdMention() {
        return idMention;
    }

    public void setIdMention(Mention idMention) {
        this.idMention = idMention;
    }

    public Enseignant getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(Enseignant idEnseignant) {
        this.idEnseignant = idEnseignant;
    }


}