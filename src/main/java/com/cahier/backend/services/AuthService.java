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

import com.cahier.backend.entities.Signature;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final SignatureService signatureService;

    public String authenticate(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        UserDetails user = userDetailsService.loadUserByUsername(email);
        return jwtUtil.generateToken(user.getUsername());
    }

    public User register(User user) {
        if (userRepository.existsByMail(user.getMail())) {
            throw new DataIntegrityViolationException("Cet email est déjà utilisé.");
        }
        if (userRepository.existsByNumeroTelephone(user.getNumeroTelephone())) {
            throw new DataIntegrityViolationException("Ce numéro de téléphone est déjà utilisé.");
        }

        // Enregistrer l'utilisateur avant de créer la signature
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);  // Enregistrer l'utilisateur et obtenir l'ID

        // Créer la signature avec l'ID de l'utilisateur
        String codeSignature = generateSignature(savedUser);
        Signature signature = new Signature();
        signature.setCodeSignature(codeSignature);
        signature.setUserId(savedUser.getId());  // Utiliser l'ID de l'utilisateur enregistré

        // Enregistrer la signature dans la base de données
        signatureService.saveSignature(signature);

        return savedUser;  // Retourner l'utilisateur enregistré
    }

    private String generateSignature(User user) {
        // Utilisation d'un UUID ou d'un hash des données de l'utilisateur pour générer la signature
        return UUID.randomUUID().toString();  // Vous pouvez aussi utiliser un autre algorithme de génération
    }
}