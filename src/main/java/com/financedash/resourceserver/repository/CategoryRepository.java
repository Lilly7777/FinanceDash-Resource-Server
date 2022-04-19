package com.financedash.resourceserver.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.financedash.resourceserver.model.Category;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {

    Category findByCategoryName(String categoryName);

    List<Category> findAllTransactionsByCategoryName(String categoryName);

}
