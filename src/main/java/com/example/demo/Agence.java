package com.example.demo;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("AGENCE")
public class Agence extends Utilisateur {
    private String nomAgence;
    private String ville;
    private String siteWeb;

    public String getNomAgence() { return nomAgence; }
    public void setNomAgence(String nomAgence) { this.nomAgence = nomAgence; }
    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }
    public String getSiteWeb() { return siteWeb; }
    public void setSiteWeb(String siteWeb) { this.siteWeb = siteWeb; }
}