package com.seerbit.service;

import com.seerbit.model.Transaction;
import com.seerbit.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionRepository transactionRepository;

    private Transaction transaction;

    @BeforeEach
    public void initSetup(){
        transaction = new Transaction();
        transactionService = new TransactionService();
    }

    @Test
    @DisplayName("Create Transaction")
    public void test_createTransaction(){
        transaction.setAmount("55.89");
        transaction.setId(2);

        assert transaction != null;
        assert transaction.getAmount() != null;

        transactionRepository.save(transaction);
        log.info("Created a transaction --> {}", transaction);
    }
}