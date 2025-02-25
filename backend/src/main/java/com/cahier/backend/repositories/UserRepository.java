package com.cahier.backend.repositories;

import com.cahier.backend.entities.Admin;
import com.cahier.backend.entities.Delegue;
import com.cahier.backend.entities.Enseignant;
import com.cahier.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository pour User (superclass des autres rôles)
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByMail(String mail);
    boolean existsByMail(String mail_);
    boolean existsByNumeroTelephone(String numeroTelephone);
}

