package com.cahier.backend.controllers;

import com.cahier.backend.dtos.ClasseDTO;
import com.cahier.backend.entities.Classe;
import com.cahier.backend.services.ClasseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/classes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClasseController {
    private final ClasseService classeService;

    @GetMapping
    public List<ClasseDTO> getAllClasses() {
        List<Classe> classes = classeService.getAllClasses();
        return classes.stream()
                .map(c -> new ClasseDTO(c.getId(), c.getNom()))  // Correction de l'ID
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClasseDTO> getClasseById(@PathVariable String id) {
        Optional<Classe> classe = classeService.getClasseById(id);
        return classe.map(c -> ResponseEntity.ok(new ClasseDTO(c.getId(), c.getNom())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClasseDTO> createClasse(@Valid @RequestBody ClasseDTO classeDTO) {
        Classe classe = new Classe();
        classe.setNom(classeDTO.getNom());  // Assure-toi que classeDTO.getNom() est valide

        Classe savedClasse = classeService.saveClasse(classe);
        return ResponseEntity.ok(new ClasseDTO(savedClasse.getId(), savedClasse.getNom())); // Correction de l'ID
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClasse(@PathVariable String id) {
        classeService.deleteClasse(id);
        return ResponseEntity.noContent().build();
    }
}
