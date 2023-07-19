package com.appli.springjwt.repository;

import com.appli.springjwt.models.Authentification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthentificationRepository extends JpaRepository<Authentification, Long> {


}