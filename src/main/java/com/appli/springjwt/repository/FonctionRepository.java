package com.appli.springjwt.repository;

import com.appli.springjwt.models.ERole;
import com.appli.springjwt.models.Fonction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FonctionRepository extends JpaRepository<Fonction, Integer> {

    Optional<Fonction> findByName(ERole name);
}