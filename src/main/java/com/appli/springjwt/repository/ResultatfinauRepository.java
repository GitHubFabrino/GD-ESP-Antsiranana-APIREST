package com.appli.springjwt.repository;

import com.appli.springjwt.models.Definitionparcour;
import com.appli.springjwt.models.Etudiant;
import com.appli.springjwt.models.Resultatfinau;
import com.appli.springjwt.models.ResultatfinauId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResultatfinauRepository extends JpaRepository<Resultatfinau, ResultatfinauId> {

    boolean existsByIdEtudiantAndIdDp(Etudiant idEtudiant, Definitionparcour idDp);
    boolean existsByIdEtudiantAndIdDpIn(Etudiant idEtudiant, List<Definitionparcour> asList);

    Optional<Resultatfinau> findByIdEtudiantAndIdDp(Etudiant idEtudiant,Definitionparcour idDp);

    Optional<Resultatfinau> findByIdEtudiantAndIdDpAndCodeRedoublement(Etudiant idEtudiant,Definitionparcour idDp,Byte codeRedoublement);
    List<Resultatfinau> findAllByIdDp(Definitionparcour idDp);
}