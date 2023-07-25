package com.appli.springjwt.dto;

import java.util.ArrayList;

public class ConcoursDto {


    ConcoursTCIDto concoursTCI;
    ArrayList<CentreConcoursTCIDto> centreConcoursTCI;
    ArrayList<MatiereDto> matiereConcoursTCI;
    ArrayList<CalendrierConcoursTCIDto> calendrierConcoursTCI;

    public ConcoursDto() {
    }


    public ConcoursDto(ConcoursTCIDto concoursTCI, ArrayList<CentreConcoursTCIDto> centreConcoursTCI, ArrayList<MatiereDto> matiereConcoursTCI, ArrayList<CalendrierConcoursTCIDto> calendrierConcoursTCI) {
        this.concoursTCI = concoursTCI;
        this.centreConcoursTCI = centreConcoursTCI;
        this.matiereConcoursTCI = matiereConcoursTCI;
        this.calendrierConcoursTCI = calendrierConcoursTCI;
        System.out.println(concoursTCI);
        System.out.println(centreConcoursTCI);
        System.out.println(matiereConcoursTCI);
        System.out.println(calendrierConcoursTCI);
    }

    public ArrayList<CalendrierConcoursTCIDto> getCalendrierConcoursTCI() {
        return calendrierConcoursTCI;
    }

    public void setCalendrierConcoursTCIDtos(ArrayList<CalendrierConcoursTCIDto> calendrierConcoursTCIDtos) {
        this.calendrierConcoursTCI = calendrierConcoursTCIDtos;
    }

    public ConcoursTCIDto getConcoursTCI() {
        return concoursTCI;
    }

    public void setConcoursTCI(ConcoursTCIDto concoursTCI) {
        this.concoursTCI = concoursTCI;
    }

    public ArrayList<CentreConcoursTCIDto> getCentreConcoursTCI() {
        return centreConcoursTCI;
    }

    public void setCentreConcoursTCI(ArrayList<CentreConcoursTCIDto> centreConcoursTCI) {
        this.centreConcoursTCI = centreConcoursTCI;
    }

    public ArrayList<MatiereDto> getMatiereConcoursTCI() {
        return matiereConcoursTCI;
    }

    public void setMatiereConcoursTCI(ArrayList<MatiereDto> matiereConcoursTCI) {
        this.matiereConcoursTCI = matiereConcoursTCI;
    }
  /*  @Override
    public String toString() {
        return "CalendrierConcoursTCIDto{" +
                "attribut1='" + concoursTCI + '\'' +
                ", attribut2='" + centreConcoursTCI + '\'' +
                ", attribut2='" + matiereConcoursTCI + '\'' +
                ", attribut2='" + calendrierConcoursTCI + '\'' +
                '}';
    }
*/

    @Override
    public String toString() {
        return "ConcoursDto{" +
                "concoursTCI=" + concoursTCI +
                ", centreConcoursTCI=" + centreConcoursTCI +
                ", matiereConcoursTCI=" + matiereConcoursTCI +
                ", calendrierConcoursTCI=" + calendrierConcoursTCI +
                '}';
    }
}
