package com.appli.springjwt.repository;

import com.appli.springjwt.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface DefinitionparcourRepository extends JpaRepository<Definitionparcour, Integer> {
    boolean existsByIdAuAndIdNiveauAndIdDmAndIdSemestreAndIdParcoursAndIdEnseignant(Anneeuniv idAu, Niveau idNiveau, Definitionmention idDm, Semestre idSemestre, Parcour idParcours, Enseignant idEnseignant);
    ArrayList<Definitionparcour> findAllByIdAu(Anneeuniv idAu);

    boolean existsByIdAu(Anneeuniv idAu);


}