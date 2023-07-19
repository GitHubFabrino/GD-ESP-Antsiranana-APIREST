package com.appli.springjwt.repository;

import com.appli.springjwt.models.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DirectionRepository extends JpaRepository<Direction, Integer> {

    Optional<Direction> findByStatusDirection(String statusDirection);
}