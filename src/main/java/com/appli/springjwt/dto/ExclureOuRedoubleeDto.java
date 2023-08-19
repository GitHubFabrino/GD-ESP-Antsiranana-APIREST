package com.appli.springjwt.dto;

public class ExclureOuRedoubleeDto {



    private Integer id_etudiant;

    private  Integer id_DP1;

    private  Integer id_DP2;

    private  Byte code_redoublement;

    public ExclureOuRedoubleeDto() {
    }

    public ExclureOuRedoubleeDto(Integer id_etudiant, Integer id_DP1, Integer id_DP2, Byte code_redoublement) {
        this.id_etudiant = id_etudiant;
        this.id_DP1 = id_DP1;
        this.id_DP2 = id_DP2;
        this.code_redoublement = code_redoublement;
    }

    public ExclureOuRedoubleeDto(Byte code_redoublement) {
        this.code_redoublement = code_redoublement;
    }

    public Integer getId_etudiant() {
        return id_etudiant;
    }

    public void setId_etudiant(Integer id_etudiant) {
        this.id_etudiant = id_etudiant;
    }

    public Integer getId_DP1() {
        return id_DP1;
    }

    public void setId_DP1(Integer id_DP1) {
        this.id_DP1 = id_DP1;
    }

    public Integer getId_DP2() {
        return id_DP2;
    }

    public void setId_DP2(Integer id_DP2) {
        this.id_DP2 = id_DP2;
    }

    public Byte getCode_redoublement() {
        return code_redoublement;
    }

    public void setCode_redoublement(Byte code_redoublement) {
        this.code_redoublement = code_redoublement;
    }

    @Override
    public String toString() {
        return "ExclureOuRedoubleeDto{" +
                "id_etudiant=" + id_etudiant +
                ", id_DP1=" + id_DP1 +
                ", id_DP2=" + id_DP2 +
                ", code_redoublement=" + code_redoublement +
                '}';
    }
}
