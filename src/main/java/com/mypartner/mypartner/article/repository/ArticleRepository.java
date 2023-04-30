package com.mypartner.mypartner.article.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mypartner.mypartner.article.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

}