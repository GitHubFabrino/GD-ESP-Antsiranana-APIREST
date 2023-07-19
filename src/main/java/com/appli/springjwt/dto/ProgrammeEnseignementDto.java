package com.appli.springjwt.dto;

import java.util.ArrayList;

public class ProgrammeEnseignementDto {


    ArrayList<UniteEnseignementDto> ue;
    ArrayList<ElementConstitutifDto> ec;

    public ProgrammeEnseignementDto() {
    }


    public ArrayList<UniteEnseignementDto> getUe() {
        return ue;
    }

    public void setUE(ArrayList<UniteEnseignementDto> ue) {
        this.ue = ue;
    }

    public ArrayList<ElementConstitutifDto> getEc() {
        return ec;
    }

    public void setEC(ArrayList<ElementConstitutifDto> ec) {
        this.ec = ec;
    }

}
