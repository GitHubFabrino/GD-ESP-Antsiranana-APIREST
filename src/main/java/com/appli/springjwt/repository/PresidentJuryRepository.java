package com.appli.springjwt.repository;

import com.appli.springjwt.models.Enseignant;
import com.appli.springjwt.models.PresidentJuryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PresidentJuryRepository extends JpaRepository<PresidentJuryModel, Integer> {

    /*boolean existsById_CTCI(int idCTCI);*/

    boolean existsByIdCTCI(int id_concours);

    List<PresidentJuryModel> findByIdEnseignant(Enseignant id_enseignant);

    Optional<PresidentJuryModel> findByIdCTCI(Integer idConcour);
}
