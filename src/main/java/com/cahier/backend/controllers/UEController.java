package com.cahier.backend.controllers;

import com.cahier.backend.dtos.UEDTO;
import com.cahier.backend.entities.UE;
import com.cahier.backend.services.UEService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ues")
@RequiredArgsConstructor
@Validated  // Permet d'utiliser les validations annotées sur les entrées
public class UEController {
    private final UEService ueService;

    @GetMapping
    public List<UEDTO> getAllUEs() {
        List<UE> ues = ueService.getAllUEs();
        return ues.stream()
                .map(ue -> new UEDTO(ue.getId(), ue.getTitre(),
                        ue.getEnseignantId(),  // Récupération de l'ID de l'enseignant
                        ue.getClasseId()))  // Récupération de l'ID de la classe
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UEDTO> getUEById(@PathVariable String id) {  // Utilisation de String pour l'ID
        Optional<UE> ue = ueService.getUEById(id);
        return ue.map(u -> ResponseEntity.ok(new UEDTO(u.getId(), u.getTitre(),
                        u.getEnseignantId(),  // Récupération de l'ID de l'enseignant
                        u.getClasseId())))  // Récupération de l'ID de la classe
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UEDTO> createUE(@RequestBody @Valid UEDTO ueDTO) {
        // Conversion du DTO en entité UE avant de sauvegarder
        UE ue = new UE();
        ue.setTitre(ueDTO.getTitre());
        ue.setEnseignantId(ueDTO.getEnseignantId());  // Assignation de l'ID de l'enseignant
        ue.setClasseId(ueDTO.getClasseId());  // Assignation de l'ID de la classe

        // Sauvegarde et retour du DTO créé
        UE savedUE = ueService.saveUE(ue);

        return ResponseEntity.ok(new UEDTO(savedUE.getId(), savedUE.getTitre(),
                savedUE.getEnseignantId(),  // Retour de l'ID de l'enseignant
                savedUE.getClasseId()));  // Retour de l'ID de la classe
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUE(@PathVariable String id) {  // Utilisation de String pour l'ID
        ueService.deleteUE(id);
        return ResponseEntity.noContent().build();
    }
}
