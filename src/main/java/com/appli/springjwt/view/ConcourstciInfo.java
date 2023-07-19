package com.appli.springjwt.view;

import com.appli.springjwt.models.Concourstci;

/**
 * A Projection for the {@link Concourstci} entity
 */
public interface ConcourstciInfo {
    Integer getId();

    String getSessionCTCI();

   // String getAnneeCTCI();

    String getDescriptionCTCI();
}