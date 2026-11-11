package com.example.demo;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("VISITEUR")
public class Visiteur extends Utilisateur {
}