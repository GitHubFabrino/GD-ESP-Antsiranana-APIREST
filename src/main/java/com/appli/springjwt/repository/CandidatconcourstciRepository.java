package com.appli.springjwt.repository;

import com.appli.springjwt.models.Candidatconcourstci;
import com.appli.springjwt.models.Centreconcourstci;
import com.appli.springjwt.models.Personne;
import com.appli.springjwt.view.CandidatconcourstciInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CandidatconcourstciRepository extends JpaRepository<Candidatconcourstci, Integer> {

    List<CandidatconcourstciInfo> findAllBy();

    @Override
    ArrayList<Candidatconcourstci> findAll();

    ArrayList<Candidatconcourstci> findAllByIdCentreCTCI(Centreconcourstci idCentreCTCI);
  //  ArrayList<Candidatconcourstci> findByIdPersonne(Personne idPersonne);

    //ArrayList<Candidatconcourstci> findAllByIdCentreCTCIOrderByMoyenneCandidatCTCIAsc(Centreconcourstci idCentreCTCI);

    ArrayList<Candidatconcourstci> findAllByPassationCandidatCTCIAndIdCentreCTCI(Boolean passationCandidatCTCI, Centreconcourstci idCentreCTCI);


    ArrayList<Candidatconcourstci> findInfoById(Candidatconcourstci id);

    ArrayList<Candidatconcourstci> findByIdPersonne(Integer idPersonne);

    Optional<Candidatconcourstci> findByidPersonne(Integer id);

    ArrayList<Candidatconcourstci> findAllByPassationCandidatCTCIAttenteAndIdCentreCTCI(boolean b, Centreconcourstci centreconcours);
}