package com.mypartner.mypartner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import com.mypartner.mypartner.category.model.Category;
import com.mypartner.mypartner.category.repository.CategoryRepository;
import com.mypartner.mypartner.category.service.CategoryService;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void testGetAllCategories() {
        // Given
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1L, "Category 1"));
        categories.add(new Category(2L, "Category 2"));
        categories.add(new Category(3L, "Category 3"));

        Mockito.when(categoryRepository.findAll()).thenReturn(categories);

        // When
        List<Category> result = categoryService.getAllCategories();

        // Then
        assertThat(result.size()).isEqualTo(3);
        assertThat(result).containsAll(categories);
    }

    @Test
    public void testGetCategoryById() {
        // Given
        Long categoryId = 1L;
        Category category = new Category(categoryId, "Category 1");

        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        // When
        Optional<Category> result = categoryService.getCategoryById(categoryId);

        // Then
        assertThat(result).isPresent().contains(category);
    }

    @Test
    public void testGetCategoryByName() {
        // Given
        String categoryName = "Category 1";
        Category category = new Category(1L, categoryName);

        Mockito.when(categoryRepository.findByName(categoryName)).thenReturn(Optional.of(category));

        // When
        Optional<Category> result = categoryService.getCategoryByName(categoryName);

        // Then
        assertThat(result).isPresent().contains(category);
    }

    @Test
    public void testSaveCategory() {
        // Given
        Category category = new Category(null, "New Category");

        Mockito.when(categoryRepository.save(category)).thenReturn(new Category(1L, "New Category"));

        // When
        Category result = categoryService.saveCategory(category);

        // Then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getName()).isEqualTo(category.getName());
    }

    @Test
    public void testDeleteCategory() {
        // Given
        Long categoryId = 1L;

        // When
        categoryService.deleteCategory(categoryId);

        // Then
        Mockito.verify(categoryRepository, Mockito.times(1)).deleteById(categoryId);
    }
}
