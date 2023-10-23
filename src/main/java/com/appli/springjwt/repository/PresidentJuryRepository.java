package com.appli.springjwt.repository;

import com.appli.springjwt.models.Concourstci;
import com.appli.springjwt.models.Definitionmention;
import com.appli.springjwt.models.PresidentJuryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresidentJuryRepository extends JpaRepository<PresidentJuryModel, Integer> {

    /*boolean existsById_CTCI(int idCTCI);*/

    boolean existsByIdCTCI(int id_concours);
}
