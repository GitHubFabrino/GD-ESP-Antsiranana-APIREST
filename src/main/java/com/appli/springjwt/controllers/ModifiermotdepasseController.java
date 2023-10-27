package com.appli.springjwt.controllers;

import com.appli.springjwt.dto.ModifiermotdepasseDto;
import com.appli.springjwt.models.Authentification;
import com.appli.springjwt.payload.response.MessageResponse;
import com.appli.springjwt.repository.AuthentificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/mdp")
public class ModifiermotdepasseController {

    @Autowired
    private AuthentificationRepository authentificationRepository;
    @Autowired
    PasswordEncoder encoder;
    @PostMapping()
    @PreAuthorize("hasRole('USER') or hasAuthority('ENSEIGNANT') or hasAuthority('DIRECTION') or hasAuthority('RESPONSABLE_MENTION') or hasAuthority('RESPONSABLE_PARCOURS') or hasAuthority('PRESIDENT_JURY') or hasAuthority('SCOLARITE') or hasAuthority('ETUDIANT') or hasRole('ADMIN')")
    public ResponseEntity<?> modifiermotdepasse(@Valid @RequestBody ModifiermotdepasseDto modifiermotdepasseDto) {
        System.out.println("ModifiermotdepasseController : modifiermotdepasse");

        Authentification user = authentificationRepository.findByUsername(modifiermotdepasseDto.getUsername());
        if (user == null){
            return ResponseEntity.badRequest().body(new MessageResponse("Erreur : authentification non trouvée"));
        }
        // Vérifier si l'ancien mot de passe est correct
        if (!encoder.matches(modifiermotdepasseDto.getOldpassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Erreur : authentification non trouvée"));
        }

        // Mettre à jour le mot de passe avec le nouveau mot de passe hashé
        user.setPassword(encoder.encode(modifiermotdepasseDto.getNewpassword()));
        user.setPass_word(modifiermotdepasseDto.getNewpassword());
        authentificationRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Modification de mot de passe reussi !!!"));

    }

}
