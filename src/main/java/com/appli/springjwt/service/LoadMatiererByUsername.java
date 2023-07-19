package com.appli.springjwt.service;

import com.appli.springjwt.repository.MatiereconcourstciRepository;
import com.appli.springjwt.models.Authentification;
import com.appli.springjwt.models.Matiereconcourstci;
import com.appli.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoadMatiererByUsername {
    @Autowired
    MatiereconcourstciRepository matiereconcourstciRepository;
    @Autowired
    UserRepository userRepository;

    public Matiereconcourstci loadUserByUsername(String username) throws UsernameNotFoundException {
        Authentification authentification = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return null;
    }
}
