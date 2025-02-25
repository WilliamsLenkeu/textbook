package com.cahier.backend.repositories;

import com.cahier.backend.entities.Signature;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignatureRepository extends MongoRepository<Signature, String> {}  // Utilisation de String pour l'ID
