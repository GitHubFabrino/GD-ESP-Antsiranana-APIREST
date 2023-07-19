package com.appli.springjwt.repository;

import com.appli.springjwt.models.Anneeuniv;
import com.appli.springjwt.models.Definitiondroit;
import com.appli.springjwt.models.Droit;
import com.appli.springjwt.models.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface DefinitiondroitRepository extends JpaRepository<Definitiondroit, Integer> {

    Boolean existsByIdAUAndIdDroitAndIdNiveau(Anneeuniv idAU, Droit idDroit, Niveau idNiveau);

    ArrayList<Definitiondroit> findAllByIdAU(Anneeuniv idAU);
}