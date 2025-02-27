package com.cahier.backend.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Document(collection = "textbooks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TextBook {
    @Id
    private String id;  // Utilisation de String pour l'ID

    @NotNull(message = "Le titre du manuel est obligatoire")
    @Size(min = 3, max = 100, message = "Le titre doit avoir entre 3 et 100 caractères")
    private String title;

    private List<String> subtitles;  // Liste de sous-titres (optionnels)

    @NotNull(message = "La date du manuel est obligatoire")
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
}
