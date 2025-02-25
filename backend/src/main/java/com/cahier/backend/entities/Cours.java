package com.cahier.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cours")
public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La date du cours est obligatoire")
    private LocalDate date;

    @NotNull(message = "L'heure de début est obligatoire")
    private LocalTime heureDebut;

    @NotNull(message = "L'heure de fin est obligatoire")
    private LocalTime heureFin;

    @NotNull(message = "L'unité d'enseignement est obligatoire")
    @ManyToOne
    @JoinColumn(name = "id_ue", nullable = false)
    private UE ue;

    @NotNull(message = "L'enseignant est obligatoire")
    @ManyToOne
    @JoinColumn(name = "id_enseignant", nullable = false)
    private User enseignant;

    @NotNull(message = "Le délégué est obligatoire")
    @ManyToOne
    @JoinColumn(name = "id_délégué", nullable = false)
    private User delegue;

    @NotNull(message = "La classe est obligatoire")
    @ManyToOne
    @JoinColumn(name = "id_classe", nullable = false)
    private Classe classe;
}
