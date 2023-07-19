package com.appli.springjwt.view;

import com.appli.springjwt.models.Personne;

/**
 * A Projection for the {@link Personne} entity
 */
public interface PersonneInfo {
    String getNom();

    String getPrenoms();

    String getTelephone();
}