package com.appli.springjwt.controllers;


import com.appli.springjwt.message.ResponseMessage;
import com.appli.springjwt.models.Fichier;
import com.appli.springjwt.service.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class FilesController {
  @Autowired
  FilesStorageService storageService;

  @PostMapping("/upload")
  @PreAuthorize("hasAuthority('DIRECTION') or hasAuthority('SCOLARITE') or hasAuthority('ENSEIGNANT') or hasAuthority('ETUDIANT') or hasRole('ADMIN')")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile[] files) {
    System.out.println("FilesController : uploadFile");
    String message = "";
    try {
      List<String> fileNames = new ArrayList<>();
      Arrays.asList(files).stream().forEach(file -> {
        Integer i = 0;
        storageService.save(file, i);
        fileNames.add(file.getOriginalFilename());
        i+=1;
      });

      message = "Uploaded the file successfully: " + fileNames;
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

    } catch (Exception e) {
      message = "Fail to upload files!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }
  @PermitAll
  @GetMapping("/files")
  @PreAuthorize("hasAuthority('DIRECTION') or hasAuthority('SCOLARITE') or hasAuthority('ENSEIGNANT') or hasAuthority('ETUDIANT') or hasRole('ADMIN')")
  public ResponseEntity<List<Fichier>> getListFiles() {
    System.out.println("FilesController : getListFiles");
    List<Fichier> fileInfos = storageService.loadAll().map(path -> {
      String filename = path.getFileName().toString();
      String url = MvcUriComponentsBuilder
          .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

      return new Fichier(filename, url);
    }).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
  }
  @PermitAll
  @GetMapping("/files/{filename:=+}")
  @PreAuthorize("hasAuthority('DIRECTION') or hasAuthority('SCOLARITE') or hasAuthority('ENSEIGNANT') or hasAuthority('ETUDIANT') or hasRole('ADMIN')")
  public ResponseEntity<Resource> getFile(@PathVariable String filename ) {
    System.out.println("FilesController : getFile");
    Resource file = storageService.load(filename);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }

  @DeleteMapping("/files/{filename:.+}")
  @PreAuthorize("hasAuthority('DIRECTION') or hasAuthority('SCOLARITE') or hasAuthority('ENSEIGNANT') or hasAuthority('ETUDIANT') or hasRole('ADMIN')")
  public ResponseEntity<ResponseMessage> deleteFile(@PathVariable String filename) {
    System.out.println("FilesController : deleteFile");
    String message = "";
    
    try {
      boolean existed = storageService.delete(filename);
      
      if (existed) {
        message = "Delete the file successfully: " + filename;
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
      }
      
      message = "The file does not exist!";
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(message));
    } catch (Exception e) {
      message = "Could not delete the file: " + filename + ". Error: " + e.getMessage();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(message));
    }
  }

}
