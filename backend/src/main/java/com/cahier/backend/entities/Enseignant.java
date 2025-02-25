package com.cahier.backend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ENSEIGNANT")
public class Enseignant extends User {}
