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
        List<Cours> coursList = coursService.getAllCours();
        return coursList.stream().map(cours -> new CoursDTO(
                cours.getId(),
                cours.getDate(),
                cours.getHeureDebut(),
                cours.getHeureFin(),
                cours.getUeId(),
                cours.getEnseignantId(),
                cours.getDelegueId(),
                cours.getClasseId()
        )).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoursDTO> getCoursById(@PathVariable String id) {  // Utilisation de String pour l'ID
        Optional<Cours> cours = coursService.getCoursById(id);
        return cours.map(c -> ResponseEntity.ok(new CoursDTO(
                c.getId(),
                c.getDate(),
                c.getHeureDebut(),
                c.getHeureFin(),
                c.getUeId(),
                c.getEnseignantId(),
                c.getDelegueId(),
                c.getClasseId()
        ))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CoursDTO> createCours(@RequestBody CoursDTO coursDTO) {
        Cours cours = new Cours();
        cours.setDate(coursDTO.getDate());
        cours.setHeureDebut(coursDTO.getHeureDebut());
        cours.setHeureFin(coursDTO.getHeureFin());
        cours.setUeId(coursDTO.getUeId());
        cours.setEnseignantId(coursDTO.getEnseignantId());
        cours.setDelegueId(coursDTO.getDelegueId());
        cours.setClasseId(coursDTO.getClasseId());

        Cours savedCours = coursService.saveCours(cours);

        return ResponseEntity.ok(new CoursDTO(
                savedCours.getId(),
                savedCours.getDate(),
                savedCours.getHeureDebut(),
                savedCours.getHeureFin(),
                savedCours.getUeId(),
                savedCours.getEnseignantId(),
                savedCours.getDelegueId(),
                savedCours.getClasseId()
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCours(@PathVariable String id) {  // Utilisation de String pour l'ID
        coursService.deleteCours(id);
        return ResponseEntity.noContent().build();
    }
}
