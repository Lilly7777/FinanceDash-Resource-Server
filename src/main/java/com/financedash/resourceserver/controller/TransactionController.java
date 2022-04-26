package com.financedash.resourceserver.controller;

import com.azure.core.annotation.HeaderParam;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.financedash.resourceserver.exception.TransactionNotFoundException;
import com.financedash.resourceserver.model.Category;
import com.financedash.resourceserver.model.ExtendedTransaction;
import com.financedash.resourceserver.model.Transaction;
import com.financedash.resourceserver.service.CategoryService;
import com.financedash.resourceserver.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "*")

@RestController
public class TransactionController {
    //    GET http://localhost:8080/transaction/<id> - gets the transaction json object
    //    GET http://localhost:8080/transaction?userId=<userId> - gets all the transactions of a user
    //    POST http://localhost:8080/transaction - creates a transaction (Request body =  json object)
    //    DELETE http://localhost:8080/transaction/<id> - deletes the transaction

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/api/v1/transaction/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable String id){
        try{
            return new ResponseEntity<>(transactionService.getTransactionById(id), HttpStatus.OK);
        }catch (TransactionNotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            // {"id":"565","userId":"123","sum":12.0}
        }
    }

    @GetMapping("/api/v1/transaction")
    public ResponseEntity<List<String>> getAllTransactions(@RequestParam String userId,
                                                                @RequestParam(value = "output", required = false) String output){
        ObjectMapper mapper = new ObjectMapper();
        List<String> returnList = new LinkedList<>();
        List<Transaction> transactions = transactionService.getAllTransactionByUserId(userId);
        System.out.println(transactions);
        boolean isExtended = Objects.nonNull(output) && output.equals("extended");
            transactions.forEach(transaction -> {
                Category category = categoryService.getCategoryById(transaction.getCategoryId());
                try {
                    if(isExtended) {
                        returnList.add(mapper.writeValueAsString(new ExtendedTransaction(transaction, category)));
                    }else{
                        returnList.add(mapper.writeValueAsString(transaction));
                    }
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            });
        return new ResponseEntity<>(returnList, HttpStatus.OK);
    }

    @PostMapping(value= "/api/v1/transaction", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction){
        return new ResponseEntity<>(transactionService.addTransaction(transaction), HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/transaction/{id}")
    public ResponseEntity<Transaction> deleteTransaction(@PathVariable String id){
        try{
            transactionService.deleteTransactionById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }catch (TransactionNotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
