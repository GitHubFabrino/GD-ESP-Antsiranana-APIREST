package com.appli.springjwt.view;

import com.appli.springjwt.models.Concourstci;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * A Projection for the {@link Concourstci} entity
 */
public interface ConcourstciInfo {
    Integer getId();

    Date getDateFinConcours();

    Date getDateDebutConcours();


    String getSessionCTCI();

   // String getAnneeCTCI();

    String getDescriptionCTCI();
}