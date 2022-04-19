package com.financedash.resourceserver.service;

import com.financedash.resourceserver.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import com.financedash.resourceserver.repository.CategoryRepository;
import com.financedash.resourceserver.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategoryById(String categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        category.ifPresent(value -> categoryRepository.deleteById(value.getId()));
    }

    public Category getCategoryById(String categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        return category.orElse(null);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
