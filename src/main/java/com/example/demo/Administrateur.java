package com.example.demo;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("ADMIN")
public class Administrateur extends Utilisateur {
}