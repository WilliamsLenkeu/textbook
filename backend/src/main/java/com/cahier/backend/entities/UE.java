package com.cahier.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le titre de l'UE est obligatoire")
    @Size(min = 3, max = 100, message = "Le titre de l'UE doit avoir entre 3 et 100 caractères")
    private String titre;

    @ManyToOne
    @JoinColumn(name = "id_enseignant")
    private Enseignant enseignant;

    @ManyToOne
    @JoinColumn(name = "id_classe")
    private Classe classe;
}
