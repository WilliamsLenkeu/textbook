package com.cahier.backend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UEDTO {
    private String id;  // Utilisation de String pour l'ID

    private String titre;

    private String enseignantId;  // Utilisation de String pour l'ID de l'enseignant

    private String classeId;  // Utilisation de String pour l'ID de la classe

    private String codeUE;  // Ajout du champ CodeUE
}