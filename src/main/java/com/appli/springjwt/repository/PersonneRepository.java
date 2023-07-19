package com.appli.springjwt.repository;

import com.appli.springjwt.models.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonneRepository extends JpaRepository<Personne, Integer> {

   //@EntityGraph(value = "personne.idEmail",type = EntityGraph.EntityGraphType.FETCH)
    Optional<Personne> findById(Integer id);
    Boolean existsByNomAndPrenoms(String nom,String prenoms);

    Optional<Personne> findByNomAndPrenoms(String nom,String prenoms);
}