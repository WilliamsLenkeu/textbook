package com.cahier.backend.repositories;

import com.cahier.backend.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository pour Admin
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {}
