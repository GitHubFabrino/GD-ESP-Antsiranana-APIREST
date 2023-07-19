package com.appli.springjwt.dto;

import java.util.ArrayList;

public class CandidatDto {
    ArrayList<CandidatConcoursDto> candidatConcoursTCI;

    public CandidatDto() {
    }

    public CandidatDto(ArrayList<CandidatConcoursDto> candidatConcoursTCI) {
        this.candidatConcoursTCI = candidatConcoursTCI;
    }

    public ArrayList<CandidatConcoursDto> getCandidatConcoursTCI() {
        return candidatConcoursTCI;
    }

    public void setCandidatConcoursTCI(ArrayList<CandidatConcoursDto> candidatConcoursTCI) {
        this.candidatConcoursTCI = candidatConcoursTCI;
    }
}
