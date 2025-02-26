package com.cahier.backend.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Document(collection = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    private String id;

    @NotNull(message = "Le contenu de la notification est obligatoire")
    private String contenu;

    @NotNull(message = "L'expéditeur est obligatoire")
    private String expediteur;

    @NotNull(message = "Le destinataire est obligatoire")
    private String destinataire;

    private int lu = 0; // 0 = non lu, 1 = lu

    private LocalDateTime dateCreation = LocalDateTime.now();
}
