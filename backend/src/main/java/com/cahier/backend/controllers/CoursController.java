package com.cahier.backend.controllers;

import com.cahier.backend.dtos.CoursDTO;
import com.cahier.backend.entities.Cours;
import com.cahier.backend.services.CoursService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cours")
@RequiredArgsConstructor
public class CoursController {
    private final CoursService coursService;

    @GetMapping
    public List<CoursDTO> getAllCours() {
        // Récupération des entités et transformation manuelle en DTO
        List<Cours> coursList = coursService.getAllCours();
        return coursList.stream().map(cours -> new CoursDTO(
                cours.getId(),
                cours.getDate(),
                cours.getHeureDebut(),
                cours.getHeureFin(),
                cours.getUe().getId(), // ou le nom si tu préfères
                cours.getEnseignant().getId(),
                cours.getDelegue().getId(),
                cours.getClasse().getId()
        )).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoursDTO> getCoursById(@PathVariable Long id) {
        Optional<Cours> cours = coursService.getCoursById(id);
        return cours.map(c -> ResponseEntity.ok(new CoursDTO(
                c.getId(),
                c.getDate(),
                c.getHeureDebut(),
                c.getHeureFin(),
                c.getUe().getId(),
                c.getEnseignant().getId(),
                c.getDelegue().getId(),
                c.getClasse().getId()
        ))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CoursDTO> createCours(@RequestBody CoursDTO coursDTO) {
        // Conversion du DTO en entité avant la sauvegarde
        Cours cours = new Cours();
        cours.setDate(coursDTO.getDate());
        cours.setHeureDebut(coursDTO.getHeureDebut());
        cours.setHeureFin(coursDTO.getHeureFin());
        // Assigner les entités UE, Enseignant, Délégué, Classe à partir des IDs
        // Vous devrez probablement les récupérer depuis leur service ou repository
        // Exemple : cours.setUe(ueService.findById(coursDTO.getUeId()));
        // Le même pour enseignant, délégué, classe
        Cours savedCours = coursService.saveCours(cours);

        // Retourner le DTO du cours créé
        return ResponseEntity.ok(new CoursDTO(
                savedCours.getId(),
                savedCours.getDate(),
                savedCours.getHeureDebut(),
                savedCours.getHeureFin(),
                savedCours.getUe().getId(),
                savedCours.getEnseignant().getId(),
                savedCours.getDelegue().getId(),
                savedCours.getClasse().getId()
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCours(@PathVariable Long id) {
        coursService.deleteCours(id);
        return ResponseEntity.noContent().build();
    }
}
