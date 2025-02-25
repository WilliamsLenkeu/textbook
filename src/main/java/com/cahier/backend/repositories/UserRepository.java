package com.cahier.backend.repositories;

import com.cahier.backend.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    // Méthodes de recherche par email et par numéro de téléphone
    Optional<User> findByMail(String mail);
    Optional<User> findByNumeroTelephone(String numeroTelephone);
    boolean existsByMail(String mail);
    boolean existsByNumeroTelephone(String numeroTelephone);
}
