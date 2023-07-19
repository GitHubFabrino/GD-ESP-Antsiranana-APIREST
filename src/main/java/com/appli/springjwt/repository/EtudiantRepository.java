package com.appli.springjwt.repository;

import com.appli.springjwt.models.Personne;
import com.appli.springjwt.models.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {
    Boolean existsByIdPersonne(Personne idPersonne);
    Optional<Etudiant> findByIdPersonne(Personne idPersonne);



}