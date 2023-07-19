package com.appli.springjwt.repository;

import com.appli.springjwt.models.Elementconstitutif;
import com.appli.springjwt.models.UeEc;
import com.appli.springjwt.models.Uniteenseignement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UeEcRepository extends JpaRepository<UeEc, Integer> {

    UeEc findById(Long id_UE);
    List<UeEc> findByIdIn(List<Integer> asList);
    List<UeEc> findAllByIdUe(Uniteenseignement idUe);
    boolean existsByIdEcAndIdUe(Elementconstitutif idEc, Uniteenseignement idUe);
    Optional<UeEc> findByIdEcAndIdUe(Elementconstitutif idEc, Uniteenseignement idUe);
/*
    @Query("SELECT u.idUE, e.idEC FROM UeEc ue JOIN ue.ue u JOIN ue.ec e GROUP BY u.idUE, e.idEC")
    List<Object[]> findIdEcGroupedByUe();

 */


}