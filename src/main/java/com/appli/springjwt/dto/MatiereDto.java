package com.appli.springjwt.dto;

import java.math.BigDecimal;
import java.util.Arrays;

public class MatiereDto {
    Integer id;
    String nomMCTCI;
    BigDecimal creditMCTCI;

    @Override
    public String toString() {
        return "MatiereDto{" +
                "id=" + id +
                ", nomMCTCI='" + nomMCTCI + '\'' +
                ", creditMCTCI=" + creditMCTCI +
                '}';
    }

    public MatiereDto() {
    }

    public MatiereDto(Integer id) {
        this.id = id;
    }

    public MatiereDto(Integer id, String nomMCTCI, BigDecimal creditMCTCI) {
        this.id = id;
        this.nomMCTCI = nomMCTCI;
        this.creditMCTCI = creditMCTCI;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomMCTCI() {
        return nomMCTCI;
    }

    public void setNomMCTCI(String nomMCTCI) {
        this.nomMCTCI = nomMCTCI;
    }

    public BigDecimal getCreditMCTCI() {
        return creditMCTCI;
    }

    public void setCreditMCTCI(BigDecimal creditMCTCI) {
        this.creditMCTCI = creditMCTCI;
    }
}
