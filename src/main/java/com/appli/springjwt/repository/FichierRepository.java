package com.appli.springjwt.repository;

import com.appli.springjwt.models.Fichier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FichierRepository extends JpaRepository<Fichier, Integer> {

}