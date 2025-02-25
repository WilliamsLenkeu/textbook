package com.cahier.backend.entities;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class Enseignant extends User {}
