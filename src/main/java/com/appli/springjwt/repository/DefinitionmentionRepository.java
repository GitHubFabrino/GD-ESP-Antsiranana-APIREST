package com.appli.springjwt.repository;

import com.appli.springjwt.models.Anneeuniv;
import com.appli.springjwt.models.Definitionmention;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface DefinitionmentionRepository extends JpaRepository<Definitionmention, Integer> {

    ArrayList<Definitionmention> findAllByIdAu(Anneeuniv idAu);
    ArrayList<Definitionmention> findAll();
}