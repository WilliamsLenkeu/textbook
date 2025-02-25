package com.cahier.backend.services;

import com.cahier.backend.entities.UE;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.cahier.backend.repositories.UERepository;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UEService {
    private final UERepository ueRepository;

    public List<UE> getAllUEs() {
        return ueRepository.findAll();
    }

    public Optional<UE> getUEById(Long id) {
        return ueRepository.findById(id);
    }

    public UE saveUE(UE ue) {
        return ueRepository.save(ue);
    }

    public void deleteUE(Long id) {
        ueRepository.deleteById(id);
    }
}
