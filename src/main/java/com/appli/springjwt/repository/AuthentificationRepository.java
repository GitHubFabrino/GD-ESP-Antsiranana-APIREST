package com.appli.springjwt.repository;

import com.appli.springjwt.models.Authentification;
import com.appli.springjwt.models.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthentificationRepository extends JpaRepository<Authentification, Long> {


    Authentification findByIdPersonne(Personne idPersonne);

    //Authentification findByPseudo(String username);

    Authentification findByUsername(String username);
}