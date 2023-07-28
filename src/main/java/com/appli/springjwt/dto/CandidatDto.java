package com.appli.springjwt.dto;

import java.util.ArrayList;

public class CandidatDto {
    ArrayList<CandidatConcoursDto> candidatConcoursTCI;

    public CandidatDto() {
        this.candidatConcoursTCI = new ArrayList<>();
    }

    public CandidatDto(ArrayList<CandidatConcoursDto> candidatConcoursTCI) {
        this.candidatConcoursTCI = candidatConcoursTCI;
    }

    public ArrayList<CandidatConcoursDto> getCandidatConcoursTCI() {
        System.out.println("Get candidat concour  TCI fonction " + candidatConcoursTCI);
        return candidatConcoursTCI;
    }

    public void setCandidatConcoursTCI(ArrayList<CandidatConcoursDto> candidatConcoursTCI) {
        this.candidatConcoursTCI = candidatConcoursTCI;
    }
}
