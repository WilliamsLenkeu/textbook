package com.cahier.backend.dtos;

import com.cahier.backend.entities.Role;
import jakarta.validation.constraints.*;

public record UserDTO(
        @NotBlank(message = "Le nom est obligatoire")
        String nom,

        @Email(message = "L'email doit être valide")
        @NotBlank(message = "L'email est obligatoire")
        String mail,

        @NotBlank(message = "Le numéro de téléphone est obligatoire")
        @Pattern(regexp = "^(\\+\\d{1,3})?\\d{10}$", message = "Numéro de téléphone invalide")
        String numeroTelephone,

        @NotBlank(message = "Le mot de passe est obligatoire")
        @Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères")
        String password,

        @NotNull(message = "Le rôle est obligatoire")
        Role role
) {}

