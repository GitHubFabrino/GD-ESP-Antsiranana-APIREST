package com.appli.springjwt.message;

public class ResponseMessage {
  private String message;

  public ResponseMessage(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
// Cette classe représente un objet de réponse pour un message générique.
// Elle contient un attribut "message" qui représente le contenu du message à renvoyer en réponse.
// L'objet ResponseMessage est utilisé pour encapsuler un message et le renvoyer en réponse à une requête.