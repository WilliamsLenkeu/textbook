package com.cahier.backend.repositories;

import com.cahier.backend.entities.UE;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UERepository extends MongoRepository<UE, String> {}
