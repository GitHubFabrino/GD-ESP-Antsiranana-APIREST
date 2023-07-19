package com.appli.springjwt.repository;

import com.appli.springjwt.models.Bacc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BaccRepository extends JpaRepository<Bacc, Integer> {

    Boolean existsByBacc(String bacc);

    Optional<Bacc> findByBacc(String bacc);

}