package com.appli.springjwt.controllers;

import com.appli.springjwt.models.Centreconcourstci;
import com.appli.springjwt.service.CentreConcourstciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/centre")
public class CentreConcourstciController {
    @Autowired
    CentreConcourstciService concourstciService;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasAuthority('SCOLARITE') or hasRole('ADMIN')")
    public List<Centreconcourstci> listCentre() {
        return concourstciService.getConcoursList();
    }

}
