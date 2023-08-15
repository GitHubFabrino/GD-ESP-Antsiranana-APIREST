package com.appli.springjwt.repository;

import com.appli.springjwt.models.Matiereconcourstci;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public interface MatiereconcourstciRepository extends JpaRepository<Matiereconcourstci, Integer> {

    Optional<Matiereconcourstci> findByNomMCTCI(String nomMCTCI);

    Boolean existsByNomMCTCI(String nomMCTCI);
    Boolean existsByNomMCTCIAndCreditMCTCI(String nomMCTCI, BigDecimal creditMCTCI);
    Optional<Matiereconcourstci> findByNomMCTCIAndCreditMCTCI(String nomMCTCI, BigDecimal creditMCTCI);

    ArrayList<Matiereconcourstci> findAllById(Integer id);

    ArrayList<Matiereconcourstci> findAll();
@Query(value = "SELECT DISTINCT e.nomMCTCI FROM Matiereconcourstci e")
    List<Object[]> findAllDistinctData();

}