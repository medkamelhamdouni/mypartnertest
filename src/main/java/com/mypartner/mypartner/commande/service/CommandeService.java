package com.mypartner.mypartner.commande.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mypartner.mypartner.commande.model.Commande;
import com.mypartner.mypartner.commande.repository.CommandeRepository;

@Service
public class CommandeService {
    private CommandeRepository commandeRepository;

    public CommandeService(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    public Commande save(Commande commande) {
        return commandeRepository.save(commande);
    }

    public Commande findById(Long id) {
        return commandeRepository.findById(id).orElse(null);
    }

    public List<Commande> findAll() {
        return commandeRepository.findAll();
    }

    public void deleteById(Long id) {
        commandeRepository.deleteById(id);
    }
}
