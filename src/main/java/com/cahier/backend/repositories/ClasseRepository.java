package com.cahier.backend.repositories;

import com.cahier.backend.entities.Classe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasseRepository extends MongoRepository<Classe, String> {}
