package com.cahier.backend.entities;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("DELEGUE")
public class Delegue extends User {}
