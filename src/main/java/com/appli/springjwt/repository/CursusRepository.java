package com.appli.springjwt.repository;

import com.appli.springjwt.models.Definitionparcour;
import com.appli.springjwt.models.Etudiant;
import com.appli.springjwt.models.Cursus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CursusRepository extends JpaRepository<Cursus, Integer> {

    Optional<Cursus> findByIdEtudiantAndIdDp(Etudiant idEtudiant, Definitionparcour idDp);
    boolean existsByIdEtudiantAndIdDp(Etudiant idEtudiant, Definitionparcour idDp);
    ArrayList<Cursus> findAllByIdDp(Definitionparcour idDp);

    ArrayList<Cursus> findAllByIdDpAndValiditeIp(Definitionparcour idDp,Boolean validiteIp);

    List<Cursus> findByIdDpIn(List<Definitionparcour> asList);

    List<Cursus> findByIdEtudiantAndIdDpIn(Etudiant idEtudiant, List<Definitionparcour> asList);
}