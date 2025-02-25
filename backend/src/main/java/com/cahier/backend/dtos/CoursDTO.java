package com.cahier.backend.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoursDTO {
    private Long id;

    @NotNull(message = "La date du cours est obligatoire")
    private LocalDate date;

    @NotNull(message = "L'heure de début est obligatoire")
    private LocalTime heureDebut;

    @NotNull(message = "L'heure de fin est obligatoire")
    private LocalTime heureFin;

    @NotNull(message = "L'unité d'enseignement est obligatoire")
    private Long ueId;

    @NotNull(message = "L'enseignant est obligatoire")
    private Long enseignantId;

    @NotNull(message = "Le délégué est obligatoire")
    private Long delegueId;

    @NotNull(message = "La classe est obligatoire")
    private Long classeId;
}

