package com.financedash.resourceserver.repository;

import com.financedash.resourceserver.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String>{
     List<Transaction> findByUserId(String userId);
}
