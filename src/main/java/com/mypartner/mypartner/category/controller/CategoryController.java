package com.mypartner.mypartner.category.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mypartner.mypartner.category.model.Category;
import com.mypartner.mypartner.category.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.getCategoryById(id);

        return categoryOptional.map(category -> ResponseEntity.ok().body(category))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Optional<Category> categoryOptional = categoryService.getCategoryByName(category.getName());

        if (categoryOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Category savedCategory = categoryService.saveCategory(category);

        return ResponseEntity.created(URI.create("/categories/" + savedCategory.getId()))
                .body(savedCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Optional<Category> categoryOptional = categoryService.getCategoryById(id);

        if (categoryOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        category.setId(id);
        Category updatedCategory = categoryService.saveCategory(category);

        return ResponseEntity.ok().body(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.getCategoryById(id);

        if (categoryOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        categoryService.deleteCategory(id);

        return ResponseEntity.noContent().build();
    }
}
