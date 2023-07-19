package com.appli.springjwt.repository;

import com.appli.springjwt.models.Mention;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MentionRepository extends JpaRepository<Mention, Integer> {
    Boolean existsByMention(String mention);
    Optional<Mention> findByMention(String mention);
}