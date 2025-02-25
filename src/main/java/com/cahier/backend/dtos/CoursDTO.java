package com.cahier.backend.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoursDTO {
    private String id;  // Utilisation de String pour l'ID afin de correspondre à l'entité Cours

    @NotNull(message = "La date du cours est obligatoire")
    @PastOrPresent(message = "La date du cours ne peut pas être dans le futur")
    private LocalDate date;

    @NotNull(message = "L'heure de début est obligatoire")
    private LocalTime heureDebut;

    @NotNull(message = "L'heure de fin est obligatoire")
    private LocalTime heureFin;

    @NotNull(message = "L'unité d'enseignement est obligatoire")
    private String ueId;

    @NotNull(message = "L'enseignant est obligatoire")
    private String enseignantId;

    @NotNull(message = "Le délégué est obligatoire")
    private String delegueId;

    @NotNull(message = "La classe est obligatoire")
    private String classeId;

    // Ajout d'une méthode pour vérifier que l'heure de début est avant l'heure de fin
    public boolean isValid() {
        return heureDebut.isBefore(heureFin);
    }
}
