package com.appli.springjwt.repository;

import com.appli.springjwt.models.Concourstci;
import com.appli.springjwt.models.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConcourstciRepository extends JpaRepository<Concourstci, Integer> {


   // Optional<Concourstci> findBySessionAndDescr(String sessionCTCI, String descriptionCTCI);

   // void findBySessionAndDescr(String sessionCTCI, String descriptionCTCI);
}