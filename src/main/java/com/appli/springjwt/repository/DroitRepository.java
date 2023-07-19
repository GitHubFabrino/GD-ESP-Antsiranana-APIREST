package com.appli.springjwt.repository;

import com.appli.springjwt.models.Droit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DroitRepository extends JpaRepository<Droit, Integer> {
    Boolean existsByMontantAndNomBanqueAndNumeroCompte(Long montant,String nomBanque,String numeroCompte);
    Optional<Droit> findByMontantAndNomBanqueAndNumeroCompte(Long montant,String nomBanque,String numeroCompte);

}