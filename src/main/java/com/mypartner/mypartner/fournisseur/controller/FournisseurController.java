package com.mypartner.mypartner.fournisseur.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mypartner.mypartner.fournisseur.model.Fournisseur;
import com.mypartner.mypartner.fournisseur.service.FournisseurService;

import java.util.List;

@RestController
@RequestMapping("/fournisseurs")
public class FournisseurController {

    @Autowired
    private FournisseurService fournisseurService;

    @GetMapping
    public List<Fournisseur> getAllFournisseurs() {
        return fournisseurService.getAllFournisseurs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fournisseur> getFournisseurById(@PathVariable Long id) {
        Fournisseur fournisseur = fournisseurService.getFournisseurById(id);
        if (fournisseur == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(fournisseur);
    }

    @PostMapping
    public ResponseEntity<Fournisseur> createFournisseur(@RequestBody Fournisseur fournisseur) {
        Fournisseur createdFournisseur = fournisseurService.saveFournisseur(fournisseur);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFournisseur);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fournisseur> updateFournisseur(@PathVariable Long id, @RequestBody Fournisseur fournisseur) {
        Fournisseur existingFournisseur = fournisseurService.getFournisseurById(id);
        if (existingFournisseur == null) {
            return ResponseEntity.notFound().build();
        }
        existingFournisseur.setNom(fournisseur.getNom());
        existingFournisseur.setAdresse(fournisseur.getAdresse());
        existingFournisseur.setEmail(fournisseur.getEmail());
        existingFournisseur.setTelephone(fournisseur.getTelephone());
        Fournisseur updatedFournisseur = fournisseurService.saveFournisseur(existingFournisseur);
        return ResponseEntity.ok(updatedFournisseur);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFournisseur(@PathVariable Long id) {
        Fournisseur existingFournisseur = fournisseurService.getFournisseurById(id);
        if (existingFournisseur == null) {
            return ResponseEntity.notFound().build();
        }
        fournisseurService.deleteFournisseur(id);
        return ResponseEntity.ok().build();
    }
}
