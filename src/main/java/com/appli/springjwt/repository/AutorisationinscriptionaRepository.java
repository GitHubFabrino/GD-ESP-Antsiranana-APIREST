package com.appli.springjwt.repository;

import com.appli.springjwt.models.Anneeuniv;
import com.appli.springjwt.models.Autorisationinscriptiona;
import com.appli.springjwt.models.Niveau;
import com.appli.springjwt.models.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface AutorisationinscriptionaRepository extends JpaRepository<Autorisationinscriptiona, Integer> {

    ArrayList<Autorisationinscriptiona> findAll();

    ArrayList<Autorisationinscriptiona> findAllByIdNiveauAndIdAu(Niveau idNiveau, Anneeuniv idAu);
    ArrayList<Autorisationinscriptiona> findAllByIdNiveau(Niveau idNiveau);
    Autorisationinscriptiona findByIdPersonneAndIdAu(Personne idPersonne, Anneeuniv idAu);
    Boolean existsByIdPersonne(Personne idPersonne);
    Boolean existsByIdPersonneAndIdAuAndIdNiveau(Personne idPersonne,Anneeuniv idAu,Niveau idNiveau);
    Boolean existsByNumeroRecu(String numeroRecu);


}