package com.mypartner.mypartner.commande.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mypartner.mypartner.commande.model.Commande;
import com.mypartner.mypartner.commande.service.CommandeService;

@RestController
@CrossOrigin

@RequestMapping("/commandes")
public class CommandeController {
    private CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @PostMapping
    public ResponseEntity<Commande> save(@RequestBody Commande commande) {
        return ResponseEntity.ok(commandeService.save(commande));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commande> findById(@PathVariable Long id) {
        Commande commande = commandeService.findById(id);

        if (commande == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(commande);
        }
    }

    @GetMapping
    public ResponseEntity<List<Commande>> findAll() {
        return ResponseEntity.ok(commandeService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        commandeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
