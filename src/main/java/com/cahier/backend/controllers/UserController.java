package com.cahier.backend.controllers;

import com.cahier.backend.dtos.UserDTO;
import com.cahier.backend.entities.User;
import com.cahier.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {  // Utilisation de String pour l'ID
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid UserDTO userDTO) {
        // Conversion du DTO en entité User
        User user = new User();
        user.setNom(userDTO.nom());
        user.setMail(userDTO.mail());
        user.setNumeroTelephone(userDTO.numeroTelephone());
        user.setPassword(userDTO.password());
        user.setRole(userDTO.rôle());

        // Sauvegarde et retour de l'utilisateur créé
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {  // Utilisation de String pour l'ID
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
