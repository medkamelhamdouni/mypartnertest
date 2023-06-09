package com.mypartner.mypartner.article.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypartner.mypartner.article.model.Article;
import com.mypartner.mypartner.article.repository.ArticleRepository;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public Article save(Article article) {
        return articleRepository.save(article);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article findById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }

}