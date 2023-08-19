package com.appli.springjwt.repository;

import com.appli.springjwt.models.Resultatfinau;
import com.appli.springjwt.models.ResultatfinauId;
import org.springframework.data.jpa.repository.JpaRepository;
public interface Resultat extends JpaRepository<Resultatfinau, ResultatfinauId> {
   
    Resultatfinau findByIdEtudiantAndIdDp(Integer idEtudiant, Integer idDp);

    Resultatfinau findByIdDp(Long pdefinitive);

    //Resultatfinau findByIdDp(int i);

    // Resultatfinau findByIDp(Long pdefinitive);
}
