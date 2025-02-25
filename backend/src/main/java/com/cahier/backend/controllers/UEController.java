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
                        ue.getEnseignant() != null ? ue.getEnseignant().getId() : null,
                        ue.getClasse() != null ? ue.getClasse().getId() : null))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UEDTO> getUEById(@PathVariable Long id) {
        Optional<UE> ue = ueService.getUEById(id);
        return ue.map(u -> ResponseEntity.ok(new UEDTO(u.getId(), u.getTitre(),
                        u.getEnseignant() != null ? u.getEnseignant().getId() : null,
                        u.getClasse() != null ? u.getClasse().getId() : null)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UEDTO> createUE(@RequestBody @Valid UEDTO ueDTO) {
        // Conversion du DTO en entité UE avant de sauvegarder
        UE ue = new UE();
        ue.setTitre(ueDTO.getTitre());

        // Aucune conversion explicite pour `enseignant` et `classe` n'est effectuée ici
        // Assurez-vous de gérer les entités `Enseignant` et `Classe` dans la logique de service
        UE savedUE = ueService.saveUE(ue);

        return ResponseEntity.ok(new UEDTO(savedUE.getId(), savedUE.getTitre(),
                savedUE.getEnseignant() != null ? savedUE.getEnseignant().getId() : null,
                savedUE.getClasse() != null ? savedUE.getClasse().getId() : null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUE(@PathVariable Long id) {
        ueService.deleteUE(id);
        return ResponseEntity.noContent().build();
    }
}
