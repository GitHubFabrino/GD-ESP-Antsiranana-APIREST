package com.appli.springjwt.repository;

import com.appli.springjwt.models.Anneeuniv;
import com.appli.springjwt.models.Etudiant;
import com.appli.springjwt.models.Inscriptionadministrative;
import com.appli.springjwt.models.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface InscriptionadministrativeRepository extends JpaRepository<Inscriptionadministrative, Integer> {

    boolean existsByIdAuAndIdEtudiant(Anneeuniv idAu, Etudiant idEtudiant);
    boolean existsByIdNiveauAndIdEtudiant(Niveau idNiveau, Etudiant idEtudiant);
    ArrayList<Inscriptionadministrative>  findByIdNiveauAndIdEtudiant(Niveau idNiveau, Etudiant idEtudiant);
    Optional<Inscriptionadministrative> findByIdAuAndIdEtudiant(Anneeuniv idAu, Etudiant idEtudiant);

    ArrayList<Inscriptionadministrative> findByIdAuAndIdNiveau(Anneeuniv idAu, Niveau idNiveau);

}