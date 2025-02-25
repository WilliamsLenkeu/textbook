package com.cahier.backend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignatureDTO {
    private String id;  // Utilisation de String pour l'ID

    private String codeSignature;
}
