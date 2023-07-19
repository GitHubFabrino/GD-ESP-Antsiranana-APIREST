package com.appli.springjwt.repository;

import com.appli.springjwt.models.Uniteenseignement;
import com.appli.springjwt.models.Definitionparcour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UniteenseignementRepository extends JpaRepository<Uniteenseignement, Integer> {

    boolean existsByCodeUe(String codeUe);

    Optional<Uniteenseignement> findByCodeUe(String codeUe);
    List<Uniteenseignement> findByIdDpIn(List<Definitionparcour> asList);

}