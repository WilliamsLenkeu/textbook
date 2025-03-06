package com.cahier.backend.controllers;

import com.cahier.backend.dtos.AuthResponse;
import com.cahier.backend.entities.User;
import com.cahier.backend.entities.Signature;
import com.cahier.backend.services.AuthService;
import com.cahier.backend.services.SignatureService;
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
    private final SignatureService signatureService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            AuthResponse authResponse = authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(authResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de l'authentification : " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user) {
        try {
            // Enregistrer l'utilisateur et générer la signature
            User registeredUser = authService.register(user);

            // Récupérer la signature liée à l'utilisateur
            Signature signature = signatureService.getSignaturesByUserId(registeredUser.getId()).get(0);  // Assurez-vous qu'une signature existe pour l'utilisateur

            // Retourner l'utilisateur et la signature
            return ResponseEntity.ok(new RegistrationResponse(registeredUser, signature.getCodeSignature()));
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

    public static class RegistrationResponse {
        private User user;
        private String signature;

        public RegistrationResponse(User user, String signature) {
            this.user = user;
            this.signature = signature;
        }

        // Getters et Setters
        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }
    }
}