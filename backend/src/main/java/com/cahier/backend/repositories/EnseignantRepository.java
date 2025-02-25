package com.cahier.backend.repositories;

import com.cahier.backend.entities.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository pour Enseignant
@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {}
