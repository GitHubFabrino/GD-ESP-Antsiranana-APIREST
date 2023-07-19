package com.appli.springjwt.repository;

import com.appli.springjwt.models.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Integer> {
}