package com.financedash.resourceserver.service;

import com.financedash.resourceserver.exception.TransactionNotFoundException;
import com.financedash.resourceserver.model.Transaction;
import com.financedash.resourceserver.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction addTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactionByUserId(String userId){
        return transactionRepository.findByUserId(userId);
    }

    public Transaction getTransactionById(String id){
        return transactionRepository.findById(id).orElseThrow(() -> new TransactionNotFoundException(id));
    }

    public void deleteTransactionById(String id){
        if(transactionRepository.existsById(id)){
            transactionRepository.deleteById(id);
        }else{
            throw new TransactionNotFoundException();
        }
    }
}
