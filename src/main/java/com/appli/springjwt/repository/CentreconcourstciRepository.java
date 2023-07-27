package com.appli.springjwt.repository;

import com.appli.springjwt.models.Centreconcourstci;
import com.appli.springjwt.models.Concourstci;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface CentreconcourstciRepository extends JpaRepository<Centreconcourstci, Integer> {
   // @EntityGraph(value = "centre.idCtci",type = EntityGraph.EntityGraphType.FETCH)
    List<Centreconcourstci> findAll();

    // Mande filtrage par nom centre
  /*  @EntityGraph(value = "centre.idCtci",type = EntityGraph.EntityGraphType.FETCH)
    List<Centreconcourstci> findByNomCentrectci(String nomCentrectci);

   */
    // Mande filtrage par code postale centre
   /* @EntityGraph(value = "centre.idCtci",type = EntityGraph.EntityGraphType.FETCH)
    List<Centreconcourstci> findByCodePostale(Integer codePostale);

    */
    //@EntityGraph(value = "centre.idCtci",type = EntityGraph.EntityGraphType.FETCH)
    List<Centreconcourstci> findByIdCTCI(Concourstci idCTCI);

    // Optional<Centreconcourstci> findbyIdCtci(Concourstci idCtci);
   ArrayList<Centreconcourstci> findAllByIdCTCI(Concourstci idCTCI);

    void deleteByIdAndIdCTCI(Integer id, Concourstci idCTCI);
}