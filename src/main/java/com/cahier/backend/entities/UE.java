package com.cahier.backend.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.*;

@Document(collection = "ues")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UE {
    @Id
    private String id;  // Utilisation de String pour l'ID

    @NotNull(message = "Le titre de l'UE est obligatoire")
    @Size(min = 3, max = 100, message = "Le titre de l'UE doit avoir entre 3 et 100 caractères")
    private String titre;

    private String enseignantId;  // Utilisation de String pour l'ID de l'enseignant

    private String classeId;  // Utilisation de String pour l'ID de la classe

    @Pattern(regexp = "ICT\\d{3}", message = "Le codeUE doit être au format 'ICTXXX' avec X un chiffre entre 0 et 9")
    private String codeUE;
}
