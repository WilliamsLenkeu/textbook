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
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Validated  // Permet d'utiliser les validations annotées sur les entrées
public class UEController {
    private final UEService ueService;

    @GetMapping
    public List<UEDTO> getAllUEs() {
        List<UE> ues = ueService.getAllUEs();
        return ues.stream()
                .map(ue -> new UEDTO(ue.getId(), ue.getTitre(),
                        ue.getEnseignantId(),
                        ue.getClasseId(),
                        ue.getCodeUE()))  // Ajout du champ CodeUE
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UEDTO> getUEById(@PathVariable String id) {
        Optional<UE> ue = ueService.getUEById(id);
        return ue.map(u -> ResponseEntity.ok(new UEDTO(u.getId(), u.getTitre(),
                        u.getEnseignantId(),
                        u.getClasseId(),
                        u.getCodeUE())))  // Ajout du champ CodeUE
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UEDTO> createUE(@RequestBody @Valid UEDTO ueDTO) {
        UE ue = new UE();
        ue.setTitre(ueDTO.getTitre());
        ue.setEnseignantId(ueDTO.getEnseignantId());
        ue.setClasseId(ueDTO.getClasseId());
        ue.setCodeUE(ueDTO.getCodeUE());  // Ajout du champ CodeUE

        UE savedUE = ueService.saveUE(ue);

        return ResponseEntity.ok(new UEDTO(savedUE.getId(), savedUE.getTitre(),
                savedUE.getEnseignantId(),
                savedUE.getClasseId(),
                savedUE.getCodeUE()));  // Ajout du champ CodeUE
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUE(@PathVariable String id) {
        ueService.deleteUE(id);
        return ResponseEntity.noContent().build();
    }
}
