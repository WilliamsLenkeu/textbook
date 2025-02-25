package com.cahier.backend.repositories;

import com.cahier.backend.entities.Enseignant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnseignantRepository extends MongoRepository<Enseignant, String> {}
