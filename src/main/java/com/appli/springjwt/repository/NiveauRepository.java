package com.appli.springjwt.repository;

import com.appli.springjwt.models.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NiveauRepository extends JpaRepository<Niveau, Integer> {

    Optional<Niveau> findByNiveau(String niveau);
}