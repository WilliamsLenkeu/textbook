package com.cahier.backend.repositories;

import com.cahier.backend.entities.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository pour Classe
@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {}
