package com.appli.springjwt.dto;

import java.util.ArrayList;

public class CandidatPassationDto {
    String id;

    public CandidatPassationDto() {
    }

    public CandidatPassationDto(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
