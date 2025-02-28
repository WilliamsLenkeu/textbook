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
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Validated
public class SignatureController {
    private final SignatureService signatureService;

    @GetMapping
    public List<SignatureDTO> getAllSignatures() {
        List<Signature> signatures = signatureService.getAllSignatures();
        return signatures.stream()
                .map(s -> new SignatureDTO(s.getId(), s.getCodeSignature(), s.getUserId()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SignatureDTO> getSignatureById(@PathVariable String id) {
        Optional<Signature> signature = signatureService.getSignatureById(id);
        return signature.map(s -> ResponseEntity.ok(new SignatureDTO(s.getId(), s.getCodeSignature(), s.getUserId())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SignatureDTO> createSignature(@RequestBody @Valid SignatureDTO signatureDTO) {
        Signature signature = new Signature();
        signature.setCodeSignature(signatureDTO.getCodeSignature());
        signature.setUserId(signatureDTO.getUserId());  // Ajout du userId

        Signature savedSignature = signatureService.saveSignature(signature);
        return ResponseEntity.ok(new SignatureDTO(savedSignature.getId(), savedSignature.getCodeSignature(), savedSignature.getUserId()));
    }

    @GetMapping("/user/{userId}")
    public List<SignatureDTO> getSignaturesByUserId(@PathVariable String userId) {
        List<Signature> signatures = signatureService.getSignaturesByUserId(userId);
        return signatures.stream()
                .map(s -> new SignatureDTO(s.getId(), s.getCodeSignature(), s.getUserId()))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSignature(@PathVariable String id) {
        signatureService.deleteSignature(id);
        return ResponseEntity.noContent().build();
    }
}
