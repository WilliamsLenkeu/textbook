package com.cahier.backend.controllers;

import com.cahier.backend.dtos.SignatureDTO;
import com.cahier.backend.entities.Signature;
import com.cahier.backend.services.SignatureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/signatures")
@RequiredArgsConstructor
@Validated  // Permet d'utiliser les validations annotées sur les entrées
public class SignatureController {
    private final SignatureService signatureService;

    @GetMapping
    public List<SignatureDTO> getAllSignatures() {
        List<Signature> signatures = signatureService.getAllSignatures();
        return signatures.stream()
                .map(s -> new SignatureDTO(s.getId(), s.getCodeSignature()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SignatureDTO> getSignatureById(@PathVariable String id) {  // Utilisation de String pour l'ID
        Optional<Signature> signature = signatureService.getSignatureById(id);
        return signature.map(s -> ResponseEntity.ok(new SignatureDTO(s.getId(), s.getCodeSignature())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SignatureDTO> createSignature(@RequestBody @Valid SignatureDTO signatureDTO) {
        // Conversion du DTO en entité Signature avant de sauvegarder
        Signature signature = new Signature();
        signature.setCodeSignature(signatureDTO.getCodeSignature());

        // Sauvegarde et retour du DTO créé
        Signature savedSignature = signatureService.saveSignature(signature);
        return ResponseEntity.ok(new SignatureDTO(savedSignature.getId(), savedSignature.getCodeSignature()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSignature(@PathVariable String id) {  // Utilisation de String pour l'ID
        signatureService.deleteSignature(id);
        return ResponseEntity.noContent().build();
    }
}
