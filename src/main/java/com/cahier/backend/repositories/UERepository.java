package com.cahier.backend.repositories;

import com.cahier.backend.entities.UE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository pour UE
@Repository
public interface UERepository extends JpaRepository<UE, Long> {}
