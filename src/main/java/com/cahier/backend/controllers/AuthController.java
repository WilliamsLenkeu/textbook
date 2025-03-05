package com.cahier.backend.controllers;

import com.cahier.backend.entities.User;
import com.cahier.backend.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            String token = authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de l'authentification : " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user) {
        try {
            User registeredUser = authService.register(user);
            return ResponseEntity.ok(registeredUser);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Erreur : L'email ou le numéro de téléphone est déjà utilisé.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erreur interne du serveur : " + e.getMessage());
        }
    }

    private static class LoginRequest {
        private String email;
        private String password;

        // Getters et Setters
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}