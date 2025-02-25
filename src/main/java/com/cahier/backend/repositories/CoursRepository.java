package com.cahier.backend.repositories;

import com.cahier.backend.entities.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository pour Cours
@Repository
public interface CoursRepository extends JpaRepository<Cours, Long> {}
