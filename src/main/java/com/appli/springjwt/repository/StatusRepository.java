package com.appli.springjwt.repository;

import com.appli.springjwt.models.Authentification;
import com.appli.springjwt.models.Fonction;
import com.appli.springjwt.models.Status;
import com.appli.springjwt.models.StatusId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, StatusId> {

    Optional<Status> findByIdAuthentificationAndIdFonction(Authentification idAuthentification, Fonction idFonction);


}