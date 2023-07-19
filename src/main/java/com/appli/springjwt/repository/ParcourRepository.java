package com.appli.springjwt.repository;

import com.appli.springjwt.models.Parcour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParcourRepository extends JpaRepository<Parcour, Integer> {
    Boolean existsByParcours(String parcours);
    Optional<Parcour> findByParcours(String parcours);
}