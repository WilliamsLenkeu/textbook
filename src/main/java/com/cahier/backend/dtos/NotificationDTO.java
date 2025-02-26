package com.cahier.backend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
    private String id;
    private String contenu;
    private String expediteur;
    private String destinataire;
    private int lu;
}
