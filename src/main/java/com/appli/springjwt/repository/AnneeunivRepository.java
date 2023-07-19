package com.appli.springjwt.repository;

import com.appli.springjwt.models.Anneeuniv;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnneeunivRepository extends JpaRepository<Anneeuniv, Integer> {

   // Optional<Anneeuniv> findLast();
   Optional<Anneeuniv> findByNomAU(String nomAU);
   Optional<Anneeuniv> findTopByOrderByIdDesc();
}