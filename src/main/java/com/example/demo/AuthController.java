package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    // 1. SIGNUP (POST)
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        if (utilisateurRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.status(400).body("Cet email est déjà utilisé.");
        }

        if ("AGENCE".equalsIgnoreCase(request.getRole())) {
            Agence agence = new Agence();
            agence.setNom(request.getNom());
            agence.setPrenom(request.getPrenom());
            agence.setEmail(request.getEmail());
            agence.setMotDePasse(request.getMotDePasse());
            agence.setTelephone(request.getTelephone());
            agence.setNomAgence(request.getNomAgence());
            agence.setVille(request.getVille());
            agence.setSiteWeb(request.getSiteWeb());
            return ResponseEntity.ok(utilisateurRepository.save(agence));

        } else if ("ADMIN".equalsIgnoreCase(request.getRole())) {
            Administrateur admin = new Administrateur();
            admin.setNom(request.getNom());
            admin.setPrenom(request.getPrenom());
            admin.setEmail(request.getEmail());
            admin.setMotDePasse(request.getMotDePasse());
            admin.setTelephone(request.getTelephone());
            return ResponseEntity.ok(utilisateurRepository.save(admin));

        } else {
            Client client = new Client();
            client.setNom(request.getNom());
            client.setPrenom(request.getPrenom());
            client.setEmail(request.getEmail());
            client.setMotDePasse(request.getMotDePasse());
            client.setTelephone(request.getTelephone());
            client.setNumeroPermis(request.getNumeroPermis());
            client.setAdresse(request.getAdresse());
            return ResponseEntity.ok(utilisateurRepository.save(client));
        }
    }

    // 2. LOGIN (POST)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SignupRequest loginRequest) {
        Optional<Utilisateur> user = utilisateurRepository.findByEmailAndMotDePasse(
                loginRequest.getEmail(),
                loginRequest.getMotDePasse()
        );

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(401).body("Email ou mot de passe incorrect");
        }
    }

    // 3. GET ALL USERS (GET)
    @GetMapping("/users")
    public List<Utilisateur> getAllUsers() {
        return utilisateurRepository.findAll();
    }

    @GetMapping("/clients")
    public List<Utilisateur> getAllClients() {
        return utilisateurRepository.findAll().stream()
                .filter(u -> u instanceof Client)
                .toList();
    }

    @GetMapping("/agences")
    public List<Utilisateur> getAllAgences() {
        return utilisateurRepository.findAll().stream()
                .filter(u -> u instanceof Agence)
                .toList();
    }
}