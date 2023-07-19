package com.appli.springjwt.repository;

import com.appli.springjwt.models.Cursus;
import com.appli.springjwt.models.Relevenote;
import com.appli.springjwt.models.RelevenoteId;
import com.appli.springjwt.models.UeEc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RelevenoteRepository extends JpaRepository<Relevenote, RelevenoteId> {

   // boolean existsByIdCursusAndIdPe(Cursus idCursus, Programmeenseignement idPe);
    boolean existsByIdCursusAndIdUeEc(Cursus idCursus, UeEc idUeEc);

    // Optional<Relevenote> findByIdCursusAndIdPe(Cursus idCursus, Programmeenseignement idPe);
    Optional<Relevenote> findByIdCursusAndIdUeEc(Cursus idCursus, UeEc idUeEc);

    List<Relevenote> findAllByIdUeEc( UeEc idUeEc);
    // List<Relevenote> findAllByIdPe( Programmeenseignement idPe);

    List<Relevenote> findByIdCursus(Cursus idCursus);

    //List<Relevenote> findByIdCursusInAndIdPeIn(List<Cursus> asList, List<Programmeenseignement> asList1);

   List<Relevenote> findByIdCursusAndIdUeEcIn(Cursus idCursus, List<UeEc> asList1);
    List<Relevenote> findByIdCursusInAndIdUeEcIn(List<Cursus> asList, List<UeEc> asList1);

    //List<Relevenote> findByIdCursusAndIdPeIn(List<Cursus> cursusListDp, List<Programmeenseignement> idPeList);

    //List<Relevenote> findByIdCursusAndIdPeIn(Cursus idCursus, List<Programmeenseignement> idPeList);

   // List<Relevenote> findByIdCursusAndIdPeIn(List<Cursus> idCursusList, List<Programmeenseignement> idPeList);
}