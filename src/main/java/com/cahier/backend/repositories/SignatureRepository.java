package com.cahier.backend.repositories;

import com.cahier.backend.entities.Signature;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SignatureRepository extends MongoRepository<Signature, String> {
    List<Signature> findByUserId(String userId);  // Recherche par userId
}
