package com.mypartner.mypartner.commande.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mypartner.mypartner.commande.model.Commande;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
}
