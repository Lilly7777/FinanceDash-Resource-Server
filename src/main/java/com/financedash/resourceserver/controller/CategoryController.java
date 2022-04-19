package com.financedash.resourceserver.controller;

import com.financedash.resourceserver.exception.CategoryNotFoundException;
import com.financedash.resourceserver.exception.TransactionNotFoundException;
import com.financedash.resourceserver.model.Category;
import com.financedash.resourceserver.model.Transaction;
import com.financedash.resourceserver.service.CategoryService;
import com.financedash.resourceserver.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/api/v1/category", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.OK);
    }

    @GetMapping("/api/v1/category/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable String id) {
        try {
            return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
        } catch (CategoryNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/v1/categories")
    public ResponseEntity<List<Category>> getAllCategories(@PathVariable String id) {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/category/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable String id) {
        try {
            categoryService.deleteCategoryById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (TransactionNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
