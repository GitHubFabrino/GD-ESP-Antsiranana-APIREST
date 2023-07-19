package com.appli.springjwt.view;

import com.appli.springjwt.models.Candidatconcourstci;

/**
 * A Projection for the {@link Candidatconcourstci} entity
 */
public interface CandidatconcourstciInfo {

    CentreconcourstciInfo getIdCentreCTCI();
    PersonneInfo getIdPersonne();
    Long getNumeroCandidatCTCI();
    Integer getId();
}