package com.cahier.backend.controllers;

import com.cahier.backend.dtos.ClasseDTO;
import com.cahier.backend.entities.Classe;
import com.cahier.backend.services.ClasseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/classes")
@RequiredArgsConstructor
@Validated  // Permet d'utiliser les validations annotées sur les entrées
public class ClasseController {
    private final ClasseService classeService;

    @GetMapping
    public List<ClasseDTO> getAllClasses() {
        List<Classe> classes = classeService.getAllClasses();
        return classes.stream()
                .map(c -> new ClasseDTO(c.getId(), c.getNom()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClasseDTO> getClasseById(@PathVariable Long id) {
        Optional<Classe> classe = classeService.getClasseById(id);
        return classe.map(c -> ResponseEntity.ok(new ClasseDTO(c.getId(), c.getNom())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClasseDTO> createClasse(@RequestBody @Valid ClasseDTO classeDTO) {
        // Conversion du DTO en entité Classe avant de sauvegarder
        Classe classe = new Classe();
        classe.setNom(classeDTO.getNom());

        // Sauvegarde et retour du DTO créé
        Classe savedClasse = classeService.saveClasse(classe);
        return ResponseEntity.ok(new ClasseDTO(savedClasse.getId(), savedClasse.getNom()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClasse(@PathVariable Long id) {
        classeService.deleteClasse(id);
        return ResponseEntity.noContent().build();
    }
}
