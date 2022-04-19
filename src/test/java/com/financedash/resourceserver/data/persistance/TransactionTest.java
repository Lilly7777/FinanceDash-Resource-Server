package com.financedash.resourceserver.data.persistance;

import com.financedash.resourceserver.model.Transaction;
import com.financedash.resourceserver.repository.TransactionRepository;
import com.financedash.resourceserver.service.TransactionService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@RunWith(SpringRunner.class)
@EnableMongoRepositories
public class TransactionTest {

    @MockBean
    private TransactionService transactionService;

    @MockBean
    private TransactionRepository transactionRepository;

    @Test
    public void addTransaction() {
        Transaction transaction = new Transaction(UUID.randomUUID().toString(), 1224);
        Transaction savedTransaction = transactionService.addTransaction(transaction);
        Assertions.assertEquals(transaction, savedTransaction);
    }

    @Test
    public void getAllTransactionByUserId() {
        Assertions.assertEquals(0, transactionService.getAllTransactionByUserId(UUID.randomUUID().toString()).size());
    }

    @Test
    public void getTransactionById() {
        Assertions.assertNull(transactionService.getTransactionById(UUID.randomUUID().toString()));
    }

    @Test
    public void updateTransaction() {
        Transaction transaction = new Transaction(UUID.randomUUID().toString(), 1224);
        Transaction savedTransaction = transactionService.addTransaction(transaction);
        Assertions.assertEquals(transaction, savedTransaction);
        savedTransaction.setSum(1225);
        Transaction updatedTransaction = transactionService.addTransaction(savedTransaction);
        Assertions.assertEquals(savedTransaction, updatedTransaction);
    }

    @Test
    public void deleteTransaction() {
        Transaction transaction = new Transaction(UUID.randomUUID().toString(), 1224);
        Transaction savedTransaction = transactionService.addTransaction(transaction);
        Assertions.assertEquals(transaction, savedTransaction);
        transactionService.deleteTransactionById(savedTransaction.getId());
        Assertions.assertNull(transactionService.getTransactionById(savedTransaction.getId()));
    }

}
