package com.appli.springjwt.repository;

import com.appli.springjwt.models.Calendrierconcourstci;
import com.appli.springjwt.models.CalendrierconcourstciId;
import com.appli.springjwt.models.Concourstci;
import com.appli.springjwt.models.Matiereconcourstci;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface CalendrierconcourstciRepository extends JpaRepository<Calendrierconcourstci, CalendrierconcourstciId> {

    Optional<Calendrierconcourstci> findByIdCTCIAndIdMCTCI(Concourstci idCTCI, Matiereconcourstci idMCTCI);

    ArrayList<Calendrierconcourstci> findAllByIdCTCI(Concourstci idCTCI);

    void deleteByIdCTCIAndIdMCTCI(Concourstci idCTCI, Matiereconcourstci idMCTCI);

}