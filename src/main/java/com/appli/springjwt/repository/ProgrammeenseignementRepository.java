package com.appli.springjwt.repository;

import com.appli.springjwt.models.Programmeenseignement;
import com.appli.springjwt.models.UeEc;
import com.appli.springjwt.models.Definitionparcour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgrammeenseignementRepository extends JpaRepository<Programmeenseignement, Integer> {
    List<Programmeenseignement> findAllByIdDp(Definitionparcour idDp);

    List<Programmeenseignement> findByIdDpIn(List<Definitionparcour> asList);

    Optional<Programmeenseignement> findById(Integer integer);

    boolean existsByIdUeEcAndIdDp(UeEc idUeEc, Definitionparcour idDp);

    Optional<Programmeenseignement> findByIdUeEcAndIdDp(UeEc idUeEc, Definitionparcour idDp);


    //List<UeEc> findIdUeEcByIdDp(Definitionparcour idDp);

}