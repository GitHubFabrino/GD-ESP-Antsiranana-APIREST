package com.appli.springjwt.message;

public class ResponseFile {
  private String name;
  private String url;
  private String type;
  private long size;

  public ResponseFile(String name, String url, String type, long size) {
    this.name = name;
    this.url = url;
    this.type = type;
    this.size = size;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

}
// Cette classe représente un objet de réponse pour les fichiers téléchargés.
// Elle contient des attributs tels que le nom du fichier, l'URL du fichier, le type de fichier et la taille du fichier.
// L'objet ResponseFile est utilisé pour encapsuler les informations sur un fichier téléchargé et les renvoyer en réponse à une requête.