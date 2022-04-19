package com.financedash.resourceserver.data.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.financedash.resourceserver.model.Transaction;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TransactionModelTest {

    @Test
    public void shouldSerializeTransactionModelToJson() throws JsonProcessingException {
        String transactionId = UUID.randomUUID().toString();
        Transaction transaction = new Transaction(transactionId, 124);
        Assertions.assertEquals("{\"id\":null,\"description\":null,\"userId\":\""+transactionId+"\",\"sum\":124.0,\"date\":null}",
                new ObjectMapper().writeValueAsString(transaction));
    }

    @Test
    public void shouldDeserializeJsonToTransactionModel() throws JsonProcessingException {
        String transactionId = UUID.randomUUID().toString();
        Transaction transaction = new Transaction(transactionId, 124);
        Assertions.assertEquals(transaction, new ObjectMapper().readValue("{\"id\":null,\"description\":null,\"userId\":\""+transactionId+"\",\"sum\":124.0,\"date\":null}", Transaction.class));
    }

    @Test
    public void shouldDeserializeJsonToTransactionModelWithId() throws JsonProcessingException {
        String transactionId = UUID.randomUUID().toString();
        Transaction transaction = new Transaction(transactionId, 124);
        transaction.setId(UUID.randomUUID().toString());
        Assertions.assertEquals(transaction, new ObjectMapper().readValue("{\"id\":\""+transaction.getId()+"\",\"description\":null,\"userId\":\""+transactionId+"\",\"sum\":124.0,\"date\":null}", Transaction.class));
    }

    @Test
    public void shouldDeserializeJsonToTransactionModelWithDescription() throws JsonProcessingException {
        String transactionId = UUID.randomUUID().toString();
        Transaction transaction = new Transaction(transactionId, 124);
        transaction.setDescription("test");
        Assertions.assertEquals(transaction, new ObjectMapper().readValue("{\"id\":null,\"description\":\"test\",\"userId\":\""+transactionId+"\",\"sum\":124.0,\"date\":null}", Transaction.class));
    }

    @Test
    public void shouldDeserializeJsonToTransactionModelWithDate() throws JsonProcessingException {
        String transactionId = UUID.randomUUID().toString();
        Transaction transaction = new Transaction(transactionId, 124);
        transaction.setDate("2019-01-01");
        Assertions.assertEquals(transaction, new ObjectMapper().readValue("{\"id\":null,\"description\":null,\"userId\":\""+transactionId+"\",\"sum\":124.0,\"date\":\"2019-01-01\"}", Transaction.class));
    }

    @Test
    public void shouldDeserializeJsonToTransactionModelWithDescriptionAndDate() throws JsonProcessingException {
        String transactionId = UUID.randomUUID().toString();
        Transaction transaction = new Transaction(transactionId, 124);
        transaction.setDescription("test");
        transaction.setDate("2019-01-01");
        Assertions.assertEquals(transaction, new ObjectMapper().readValue("{\"id\":null,\"description\":\"test\",\"userId\":\""+transactionId+"\",\"sum\":124.0,\"date\":\"2019-01-01\"}", Transaction.class));
    }


}
