package com.cahier.backend.services;

import com.cahier.backend.entities.Signature;
import com.cahier.backend.repositories.SignatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignatureService {
    private final SignatureRepository signatureRepository;

    public List<Signature> getAllSignatures() {
        return signatureRepository.findAll();
    }

    public Optional<Signature> getSignatureById(String id) {  // Utilisation de String pour l'ID
        return signatureRepository.findById(id);
    }

    public Signature saveSignature(Signature signature) {
        return signatureRepository.save(signature);
    }

    public void deleteSignature(String id) {  // Utilisation de String pour l'ID
        signatureRepository.deleteById(id);
    }
}
