package com.appli.springjwt.repository;

import com.appli.springjwt.models.Elementconstitutif;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ElementconstitutifRepository extends JpaRepository<Elementconstitutif, Integer> {

    boolean existsByCodeEc(String codeEc);
    Optional<Elementconstitutif> findByCodeEc(String codeEc);
    void deleteByCodeEc(String codeEc);
}