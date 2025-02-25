package com.cahier.backend.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.*;

@Document(collection = "signatures")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Signature {
    @Id
    private String id;  // Utilisation de String pour l'ID

    @NotNull(message = "Le code de signature est obligatoire")
    @Size(min = 5, max = 100, message = "Le code de signature doit avoir entre 5 et 100 caractères")
    private String codeSignature;
}
