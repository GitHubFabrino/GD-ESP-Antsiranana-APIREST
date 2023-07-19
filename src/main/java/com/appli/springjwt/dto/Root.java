package com.appli.springjwt.dto;

import java.util.ArrayList;

public class Root {

    // import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
    public class CentreConcoursTCIDto{

            String nom_centreCTCI;

            String nom;

            String prenoms;

            String telephone;
    }

    public class ConcoursTCIDto{

        String session_CTCI;

        String annee_CTCI;

        String description_CTCI;
    }

    public class MatiereDto{

        String nom_MCTCI;

        String credit_MCTCI;
    }

    public class RootDto{

        ConcoursTCIDto concoursTCI;

        ArrayList<CentreConcoursTCIDto> centreConcoursTCI;

        ArrayList<MatiereDto> matiere;
    }


}
