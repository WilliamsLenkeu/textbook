package com.cahier.backend.repositories;

import com.cahier.backend.entities.Delegue;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DelegueRepository extends MongoRepository<Delegue, String> {}
