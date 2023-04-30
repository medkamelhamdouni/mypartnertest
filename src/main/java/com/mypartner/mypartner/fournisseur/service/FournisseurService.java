package com.mypartner.mypartner.fournisseur.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypartner.mypartner.fournisseur.model.Fournisseur;
import com.mypartner.mypartner.fournisseur.repository.FournisseurRepository;

import java.util.List;

@Service
public class FournisseurService {

    @Autowired
    private FournisseurRepository fournisseurRepository;

    public List<Fournisseur> getAllFournisseurs() {
        return fournisseurRepository.findAll();
    }

    public Fournisseur getFournisseurById(Long id) {
        return fournisseurRepository.findById(id).orElse(null);
    }

    public Fournisseur saveFournisseur(Fournisseur fournisseur) {
        return fournisseurRepository.save(fournisseur);
    }

    public void deleteFournisseur(Long id) {
        fournisseurRepository.deleteById(id);
    }
}
