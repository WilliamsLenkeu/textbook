package com.cahier.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Signature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le code de signature est obligatoire")
    @Size(min = 5, max = 100, message = "Le code de signature doit avoir entre 5 et 100 caractères")
    private String codeSignature; // Peut stocker un hash d’empreinte, reconnaissance faciale, ou un simple booléen
}
