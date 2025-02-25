package com.cahier.backend.services;

import com.cahier.backend.entities.User;
import com.cahier.backend.repositories.UserRepository;
import com.cahier.backend.config.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public String authenticate(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        UserDetails user = userDetailsService.loadUserByUsername(email);
        return jwtUtil.generateToken(user.getUsername());
    }

    public User register(User user) {
        // Vérifier si l'email ou le numéro de téléphone existe déjà
        if (userRepository.existsByMail(user.getMail())) {
            throw new DataIntegrityViolationException("Cet email est déjà utilisé.");
        }
        if (userRepository.existsByNumeroTelephone(user.getNumeroTelephone())) {
            throw new DataIntegrityViolationException("Ce numéro de téléphone est déjà utilisé.");
        }

        // Encoder le mot de passe avant de sauvegarder
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
