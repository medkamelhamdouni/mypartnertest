package com.mypartner.mypartner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mypartner.mypartner.commande.model.Commande;
import com.mypartner.mypartner.commande.repository.CommandeRepository;
import com.mypartner.mypartner.commande.service.CommandeService;

@SpringBootTest
public class CommandeServiceTest {
    @Autowired
    private CommandeService commandeService;

    @Autowired
    private CommandeRepository commandeRepository;

    @Test
    public void testSave() {
        Commande commande = new Commande();
        commande.setIdProduit(1L);
        commande.setIdFournisseur(1L);
        commande.setDateCommande(LocalDate.now());

        Commande savedCommande = commandeService.save(commande);

        assertNotNull(savedCommande.getId());
        assertEquals(commande.getIdProduit(), savedCommande.getIdProduit());
        assertEquals(commande.getIdFournisseur(), savedCommande.getIdFournisseur());
        assertEquals(commande.getDateCommande(), savedCommande.getDateCommande());
    }

    @Test
    public void testFindById() {
        Commande commande = new Commande();
        commande.setIdProduit(1L);
        commande.setIdFournisseur(1L);
        commande.setDateCommande(LocalDate.now());

        commandeRepository.save(commande);

        Commande foundCommande = commandeService.findById(commande.getId());

        assertNotNull(foundCommande);
        assertEquals(commande.getId(), foundCommande.getId());
        assertEquals(commande.getIdProduit(), foundCommande.getIdProduit());
        assertEquals(commande.getIdFournisseur(), foundCommande.getIdFournisseur());
        assertEquals(commande.getDateCommande(), foundCommande.getDateCommande());
    }

    @Test
    public void testFindAll() {
        commandeRepository.deleteAll();

        Commande commande1 = new Commande();
        commande1.setIdProduit(1L);
        commande1.setIdFournisseur(1L);
        commande1.setDateCommande(LocalDate.now());

        commandeRepository.save(commande1);

        Commande commande2 = new Commande();
        commande2.setIdProduit(2L);
        commande2.setIdFournisseur(2L);
        commande2.setDateCommande(LocalDate.now().plusDays(1));

        commandeRepository.save(commande2);

        List<Commande> commandes = commandeService.findAll();

        assertEquals(2, commandes.size());
    }

    @Test
    public void testDeleteById() {
        Commande commande = new Commande();
        commande.setIdProduit(1L);
        commande.setIdFournisseur(1L);
        commande.setDateCommande(LocalDate.now());

        commandeRepository.save(commande);

        commandeService.deleteById(commande.getId());

        Commande deletedCommande = commandeRepository.findById(commande.getId()).orElse(null);

        assertNull(deletedCommande);
    }
}
