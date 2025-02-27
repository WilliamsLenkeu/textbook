package com.cahier.backend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignatureDTO {
    private String id;
    private String codeSignature;
    private String userId;  // Ajout du champ userId
}
