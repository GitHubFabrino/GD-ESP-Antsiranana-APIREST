package com.appli.springjwt.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.appli.springjwt.payload.request.LoginRequest;
import com.appli.springjwt.payload.response.JwtResponse;
import com.appli.springjwt.payload.response.MessageResponse;
import com.appli.springjwt.repository.RoleRepository;
import com.appli.springjwt.security.jwt.JwtUtils;
import com.appli.springjwt.security.services.UserDetailsImpl;
import com.appli.springjwt.service.AuthentificationService;
import com.appli.springjwt.models.Authentification;
import com.appli.springjwt.models.Fonction;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appli.springjwt.models.ERole;
import com.appli.springjwt.payload.request.SignupRequest;
import com.appli.springjwt.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;
  @Autowired
  UserRepository userRepository;
  @Autowired
  RoleRepository roleRepository;
  @Autowired
  AuthentificationService authentificationService;
  @Autowired
  PasswordEncoder encoder;
  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    // Test recuperation pseudo
    System.out.println(loginRequest.getUsername());
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());
// authentificationService.getAuthentificationById(userDetails.getIdCandidatCTCI()).getIdCandidatCTCI();
    System.out.println(authentificationService.getAuthentificationById(userDetails.getId()).getIdPersonne().getId());
    return ResponseEntity.ok(new JwtResponse(jwt,
            authentificationService.getAuthentificationById(userDetails.getId()).getIdPersonne().getId(),
                         userDetails.getUsername(),
                         roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    System.out.println(signUpRequest.getUsername());

    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    // Create new user's account
    Authentification authentification = new Authentification(signUpRequest.getUsername(),
               encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Fonction> fonctions = new HashSet<>();

    if (strRoles == null) {
      Fonction userFonction = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      fonctions.add(userFonction);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Fonction adminFonction = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          fonctions.add(adminFonction);

          break;
        case "mod":
          Fonction modFonction = roleRepository.findByName(ERole.ROLE_MODERATOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          fonctions.add(modFonction);

          break;
          case "scolarite":
            Fonction scolariteFonction = roleRepository.findByName(ERole.SCOLARITE)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            fonctions.add(scolariteFonction);

            break;

          case "etudiant":
            Fonction etudiantFonction = roleRepository.findByName(ERole.ETUDIANT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            fonctions.add(etudiantFonction);

            break;

          case "direction":
            Fonction directionFonction = roleRepository.findByName(ERole.DIRECTION)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            fonctions.add(directionFonction);

            break;

          case "enseignant":
            Fonction enseignantFonction = roleRepository.findByName(ERole.ENSEIGNANT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            fonctions.add(enseignantFonction);

            break;

          case "responsable_parcours":
            Fonction responsableParcours = roleRepository.findByName(ERole.RESPONSABLE_PARCOURS)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            fonctions.add(responsableParcours);

            break;

          case "responsable_mention":
            Fonction responsableMention = roleRepository.findByName(ERole.RESPONSABLE_MENTION)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            fonctions.add(responsableMention);

            break;

          case "candidat":
            Fonction candidat = roleRepository.findByName(ERole.CANDIDAT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            fonctions.add(candidat);

            break;
          default:
          Fonction userFonction = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          fonctions.add(userFonction);

        }

      });
    }

    String password = RandomStringUtils.randomAlphanumeric(8);
    authentification.setRoles(fonctions);
    userRepository.save(authentification);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
  /*
  @PostMapping("/signup")
  public ResponseEntity<?> modifiermotdepasse(@Valid @RequestBody SignupRequest signUpRequest) {
    System.out.println(signUpRequest.getUsername());

    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Username is already taken!"));
    }

    // Create new user's account
    Authentification authentification = new Authentification(signUpRequest.getUsername(),
            encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Fonction> fonctions = new HashSet<>();

    if (strRoles == null) {
      Fonction userFonction = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      fonctions.add(userFonction);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Fonction adminFonction = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            fonctions.add(adminFonction);

            break;
          case "mod":
            Fonction modFonction = roleRepository.findByName(ERole.ROLE_MODERATOR)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            fonctions.add(modFonction);

            break;
          case "scolarite":
            Fonction scolariteFonction = roleRepository.findByName(ERole.SCOLARITE)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            fonctions.add(scolariteFonction);

            break;

          case "etudiant":
            Fonction etudiantFonction = roleRepository.findByName(ERole.ETUDIANT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            fonctions.add(etudiantFonction);

            break;

          case "direction":
            Fonction directionFonction = roleRepository.findByName(ERole.DIRECTION)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            fonctions.add(directionFonction);

            break;

          case "enseignant":
            Fonction enseignantFonction = roleRepository.findByName(ERole.ENSEIGNANT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            fonctions.add(enseignantFonction);

            break;

          case "responsable_parcours":
            Fonction responsableParcours = roleRepository.findByName(ERole.RESPONSABLE_PARCOURS)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            fonctions.add(responsableParcours);

            break;

          case "responsable_mention":
            Fonction responsableMention = roleRepository.findByName(ERole.RESPONSABLE_MENTION)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            fonctions.add(responsableMention);

            break;

          case "candidat":
            Fonction candidat = roleRepository.findByName(ERole.CANDIDAT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            fonctions.add(candidat);

            break;
          default:
            Fonction userFonction = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            fonctions.add(userFonction);

        }

      });
    }

    String password = RandomStringUtils.randomAlphanumeric(8);
    authentification.setRoles(fonctions);
    userRepository.save(authentification);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
  */
}
