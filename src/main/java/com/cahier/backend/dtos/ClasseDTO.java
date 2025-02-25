package com.cahier.backend.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClasseDTO {
    private String id;  // Utilisation d'un String pour l'ID, pour correspondre avec l'entité Classe

    @NotNull(message = "Le nom de la classe est obligatoire")
    @Size(min = 3, max = 50, message = "Le nom de la classe doit être entre 3 et 50 caractères")
    private String nom;
}
