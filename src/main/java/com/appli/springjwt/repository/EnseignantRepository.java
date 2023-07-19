package com.appli.springjwt.repository;

import com.appli.springjwt.models.Personne;
import com.appli.springjwt.models.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnseignantRepository extends JpaRepository<Enseignant, Integer> {

    Optional<Enseignant> findById(Long id);

    Optional<Enseignant> findTopByOrderByIdDesc();
    Optional<Enseignant> findByIdPersonne(Personne idPersonne);
}