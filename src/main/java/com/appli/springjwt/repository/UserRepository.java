package com.appli.springjwt.repository;

import java.util.Optional;

import com.appli.springjwt.models.Authentification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  UserRepository extends JpaRepository<Authentification, Long> {
  Optional<Authentification> findByUsername(String username);

  Boolean existsByUsername(String username);


}
