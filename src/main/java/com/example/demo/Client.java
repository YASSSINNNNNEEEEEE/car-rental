package com.example.demo;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CLIENT")
public class Client extends Utilisateur {
    private String numeroPermis;
    private String adresse;

    public String getNumeroPermis() { return numeroPermis; }
    public void setNumeroPermis(String numeroPermis) { this.numeroPermis = numeroPermis; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
}