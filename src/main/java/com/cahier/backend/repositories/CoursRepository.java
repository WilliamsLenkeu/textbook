package com.cahier.backend.repositories;

import com.cahier.backend.entities.Cours;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursRepository extends MongoRepository<Cours, String> {}
