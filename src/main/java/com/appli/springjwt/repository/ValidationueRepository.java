package com.appli.springjwt.repository;

import com.appli.springjwt.models.Cursus;
import com.appli.springjwt.models.Uniteenseignement;
import com.appli.springjwt.models.Validationue;
import com.appli.springjwt.models.ValidationueId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ValidationueRepository extends JpaRepository<Validationue, ValidationueId> {


    boolean existsByIdUeAndIdCursus(Uniteenseignement idUe, Cursus idCursus);


    Optional<Validationue> findByIdUeAndIdCursus(Uniteenseignement idUe, Cursus idCursus);

    List<Validationue> findByIdUeAndIdCursusIn(Uniteenseignement idUe, List<Cursus> asList);

    Optional<Validationue> findByIdCursus(Map.Entry<Integer, List<Cursus>> idCursus);
}