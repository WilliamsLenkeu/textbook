package com.cahier.backend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UEDTO {
    private Long id;
    private String titre;
    private Long enseignantId;
    private Long classeId;
}
