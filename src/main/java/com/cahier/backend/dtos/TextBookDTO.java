package com.cahier.backend.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TextBookDTO {
    private String id;  // Utilisation de String pour l'ID

    @NotNull(message = "Le titre du manuel est obligatoire")
    private String title;

    private List<String> subtitles;  // Liste de sous-titres (optionnels)

    @NotNull(message = "La date du manuel est obligatoire")
    @PastOrPresent(message = "La date ne peut pas être dans le futur")
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

    public boolean isValid() {
        return heureDebut.isBefore(heureFin);
    }
}
