package com.appli.springjwt.exception;

import com.appli.springjwt.message.ResponseMessage;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Ce conseiller de contrôleur gère les exceptions liées au téléchargement de fichiers.
// Lorsqu'une exception MaxUploadSizeExceededException est levée (lorsque la taille du fichier dépasse la limite autorisée),
// cette méthode est appelée pour renvoyer une réponse avec un statut EXPECTATION_FAILED (417) et un message d'erreur approprié.
@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public ResponseEntity<ResponseMessage> handleMaxSizeException(MaxUploadSizeExceededException exc) {
    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("File too large!"));
  }
}