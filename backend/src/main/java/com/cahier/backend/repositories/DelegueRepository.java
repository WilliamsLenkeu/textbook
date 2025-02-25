package com.cahier.backend.repositories;

import com.cahier.backend.entities.Delegue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository pour Délégué
@Repository
public interface DelegueRepository extends JpaRepository<Delegue, Long> {}
