package com.cahier.backend.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.*;
import java.util.List;

@Document(collection = "classes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Classe {
    @Id
    private String id;

    @NotNull(message = "Le nom de la classe est obligatoire")
    @Size(min = 3, max = 50, message = "Le nom de la classe doit être entre 3 et 50 caractères")
    private String nom;

    private List<String> unitesEnseignementIds;
}
