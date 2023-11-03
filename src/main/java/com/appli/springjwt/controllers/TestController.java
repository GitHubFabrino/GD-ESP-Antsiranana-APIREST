package com.appli.springjwt.controllers;

import com.appli.springjwt.models.Authentification;
import com.appli.springjwt.repository.AuthentificationRepository;
import com.appli.springjwt.repository.UserRepository;
import com.appli.springjwt.service.AuthentificationService;
import com.appli.springjwt.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
  @Autowired
  private PersonneService personneService;

  @Autowired
  AuthentificationService authentificationService;
  @Autowired
  AuthentificationRepository authentificationRepository;

  @Autowired
  UserRepository userRepository;

  @GetMapping("/all/{id}")
  @PreAuthorize("hasRole('SCOLARITE')")
  public String allAccess() {
    return "Admin Board.";
  }

  @GetMapping("/user")
  @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') ")
  public String userAccess() {
    return "Admin Board.";
  }

  @GetMapping("/mod")
  @PreAuthorize("hasRole('MODERATOR')")
  public String moderatorAccess() {
    return "Admin Board.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {

    return "Admin Board.";
  }

  @GetMapping("/scol")
  @PreAuthorize("hasAuthority('SCOLARITE')")
  public List<Authentification> scolarAccess() {
    System.out.println("Scol Board");
    return authentificationRepository.findAll();
  }
}
