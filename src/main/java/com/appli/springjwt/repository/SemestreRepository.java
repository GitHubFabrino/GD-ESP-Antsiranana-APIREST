package com.appli.springjwt.repository;

import com.appli.springjwt.models.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemestreRepository extends JpaRepository<Semestre, Integer> {
}