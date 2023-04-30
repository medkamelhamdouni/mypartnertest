package com.mypartner.mypartner.article.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mypartner.mypartner.article.model.Article;
import com.mypartner.mypartner.article.service.ArticleService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        Article createdArticle = articleService.save(article);
        return new ResponseEntity<>(createdArticle, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleService.findAll();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        Article article = articleService.findById(id);
        if (article != null) {
            return new ResponseEntity<>(article, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article article) {
        Article existingArticle = articleService.findById(id);
        if (existingArticle != null) {
            existingArticle.setCode(article.getCode());
            existingArticle.setTitle(article.getTitle());
            existingArticle.setDescription(article.getDescription());
            existingArticle.setCategoryCode(article.getCategoryCode());
            existingArticle.setQuantity(article.getQuantity());
            Article updatedArticle = articleService.save(existingArticle);
            return new ResponseEntity<>(updatedArticle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}