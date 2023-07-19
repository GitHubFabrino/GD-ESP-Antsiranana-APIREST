package com.appli.springjwt.repository;

import com.appli.springjwt.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HeureenseignementRepository extends JpaRepository<Heureenseignement, HeureenseignementId> {

    Optional<Heureenseignement> findByIdAuAndIdEnseignantAndIdPe(Anneeuniv idAu, Enseignant idEnseignant, Programmeenseignement idPe);
    List<Heureenseignement> findAllByIdAuAndIdPe(Anneeuniv idAu, Programmeenseignement idPe);

    void deleteByIdAuAndIdEnseignantAndIdPe(Anneeuniv idAu, Enseignant idEnseignant, Programmeenseignement idPe);


}