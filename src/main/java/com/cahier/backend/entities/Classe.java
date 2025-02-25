package com.cahier.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le nom de la classe est obligatoire")
    @Size(min = 3, max = 50, message = "Le nom de la classe doit être entre 3 et 50 caractères")
    private String nom;

    @OneToMany(mappedBy = "classe")
    private List<UE> unitesEnseignement;
}
