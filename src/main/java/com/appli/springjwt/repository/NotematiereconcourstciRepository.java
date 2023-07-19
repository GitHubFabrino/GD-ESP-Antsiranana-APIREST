package com.appli.springjwt.repository;

import com.appli.springjwt.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface NotematiereconcourstciRepository extends JpaRepository<Notematiereconcourstci, NotematiereconcourstciId> {
    //ArrayList<Notematiereconcourstci> findAllByIdCTCI(Concourstci idCTCI);
    ArrayList<Notematiereconcourstci> findAllByIdCtciAndIdCentrectci(Concourstci idCtci, Centreconcourstci idCentrectci);

    Boolean existsByIdCtciAndIdCentrectciAndIdCandidatctci(Concourstci idCtci, Centreconcourstci idCentrectci, Candidatconcourstci idCandidatctci);
    Boolean existsByIdCtciAndIdCentrectciAndIdCandidatctciAndIdMctci(Concourstci idCtci, Centreconcourstci idCentrectci, Candidatconcourstci idCandidatctci, Matiereconcourstci idMctci);
    Optional<Notematiereconcourstci> findByIdCtciAndIdCentrectciAndIdCandidatctciAndIdMctci(Concourstci idCtci, Centreconcourstci idCentrectci,Candidatconcourstci idCandidatctci,Matiereconcourstci idMctci);
    ArrayList<Notematiereconcourstci> findAllByIdMctci(Matiereconcourstci idMctci);

    ArrayList<Notematiereconcourstci> findAllByIdCandidatctci(Candidatconcourstci idCandidatctci);



}