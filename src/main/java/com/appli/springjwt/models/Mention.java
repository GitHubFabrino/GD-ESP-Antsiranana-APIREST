package com.appli.springjwt.models;

import javax.persistence.*;

@Entity
@Table(name = "mention")
public class Mention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mention", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "acronyme_mention", nullable = false)
    private String acronymeMention;

    @Lob
    @Column(name = "mention")
    private String mention;

    @Lob
    @Column(name = "email")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMention() {
        return mention;
    }

    public void setMention(String mention) {
        this.mention = mention;
    }

    public String getAcronymeMention() {
        return acronymeMention;
    }

    public void setAcronymeMention(String acronymeMention) {
        this.acronymeMention = acronymeMention;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Mention{" +
                "id=" + id +
                ", acronymeMention='" + acronymeMention + '\'' +
                ", mention='" + mention + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}