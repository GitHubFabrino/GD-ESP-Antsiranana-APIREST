package com.appli.springjwt.repository;

import com.appli.springjwt.models.Anneeuniv;
import com.appli.springjwt.models.Offreformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface OffreformationRepository extends JpaRepository<Offreformation, Integer> {
    ArrayList<Offreformation> findAllByIdAu(Anneeuniv idAu);
}