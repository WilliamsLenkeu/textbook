package com.cahier.backend.repositories;

import com.cahier.backend.entities.TextBook;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextBookRepository extends MongoRepository<TextBook, String> {}
