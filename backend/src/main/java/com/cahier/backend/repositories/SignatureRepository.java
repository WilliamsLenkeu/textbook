package com.cahier.backend.repositories;

import com.cahier.backend.entities.Signature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository pour Signature
@Repository
public interface SignatureRepository extends JpaRepository<Signature, Long> {}
