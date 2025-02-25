package com.cahier.backend.services;

import com.cahier.backend.entities.Cours;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.cahier.backend.repositories.CoursRepository;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoursService {
    private final CoursRepository coursRepository;

    public List<Cours> getAllCours() {
        return coursRepository.findAll();
    }

    public Optional<Cours> getCoursById(String id) {  // Utilisation de String pour l'ID
        return coursRepository.findById(id);
    }

    public Cours saveCours(Cours cours) {
        return coursRepository.save(cours);
    }

    public void deleteCours(String id) {  // Utilisation de String pour l'ID
        coursRepository.deleteById(id);
    }
}
