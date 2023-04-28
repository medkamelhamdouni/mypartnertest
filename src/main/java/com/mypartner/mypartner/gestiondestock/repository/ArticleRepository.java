package com.mypartner.mypartner.gestiondestock.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mypartner.mypartner.gestiondestock.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

}