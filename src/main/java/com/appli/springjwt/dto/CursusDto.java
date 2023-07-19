package com.appli.springjwt.dto;

import java.util.ArrayList;

public class CursusDto {
 Integer id;
 Boolean validiteCurcus;
 Boolean validiteIP;
 Integer idEtudiant;
 String nom;
 String prenoms;
 String telephone;
 String email;
 ArrayList<Integer> idDP;

 public CursusDto() {
 }

 public CursusDto(Integer id, Boolean validiteIP, String nom, String prenoms) {
  this.id = id;
  this.validiteIP = validiteIP;
  this.nom = nom;
  this.prenoms = prenoms;
 }

 public CursusDto(Integer id, Boolean validiteIP, String nom, String prenoms, String telephone, String email) {
  this.id = id;
  this.validiteIP = validiteIP;
  this.nom = nom;
  this.prenoms = prenoms;
  this.telephone = telephone;
  this.email = email;
 }

 public CursusDto(Integer id, Boolean validiteIP, Integer idEtudiant, String nom, String prenoms, String telephone, String email) {
  this.id = id;
  this.validiteIP = validiteIP;
  this.idEtudiant = idEtudiant;
  this.nom = nom;
  this.prenoms = prenoms;
  this.telephone = telephone;
  this.email = email;
 }


 public Integer getIdEtudiant() {
  return idEtudiant;
 }

 public void setIdEtudiant(Integer idEtudiant) {
  this.idEtudiant = idEtudiant;
 }

 public String getTelephone() {
  return telephone;
 }

 public void setTelephone(String telephone) {
  this.telephone = telephone;
 }

 public String getEmail() {
  return email;
 }

 public void setEmail(String email) {
  this.email = email;
 }

 public ArrayList<Integer> getIdDP() {
  return idDP;
 }

 public void setIdDP(ArrayList<Integer> idDP) {
  this.idDP = idDP;
 }

 public Boolean getValiditeIP() {
  return validiteIP;
 }

 public void setValiditeIP(Boolean validiteIP) {
  this.validiteIP = validiteIP;
 }

 public Integer getId() {
  return id;
 }

 public void setId(Integer id) {
  this.id = id;
 }

 public Boolean getValiditeCurcus() {
  return validiteCurcus;
 }

 public void setValiditeCurcus(Boolean validiteCurcus) {
  this.validiteCurcus = validiteCurcus;
 }

 public String getNom() {
  return nom;
 }

 public void setNom(String nom) {
  this.nom = nom;
 }

 public String getPrenoms() {
  return prenoms;
 }

 public void setPrenoms(String prenoms) {
  this.prenoms = prenoms;
 }

}

