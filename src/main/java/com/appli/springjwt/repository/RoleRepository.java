package com.appli.springjwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appli.springjwt.models.ERole;
import com.appli.springjwt.models.Fonction;

@Repository
public interface RoleRepository extends JpaRepository<Fonction, Long> {
  Optional<Fonction> findByName(ERole name);

}
