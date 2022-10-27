package com.seerbit.model;

import com.seerbit.Exception.FutureTimeException;
import com.seerbit.Exception.OutOfRangeException;
import com.seerbit.model.request.TransactionRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class TransactionTest {

    @Test
    public void test_generationTransactionRequest() {
        TransactionRequest transactionRequest = new TransactionRequest("40.2", "2021-02-01T14:01:30.312Z");
        Transaction transaction = Transaction.from(transactionRequest);
        assertEquals(BigDecimal.valueOf(40.2), transaction.getAmount());
        assertEquals(1612188090312L, transaction.getTimestamp());
        log.info("First transaction --> {}", transaction);
    }

    @Test(expected = FutureTimeException.class)
    public void test_transactionTimeInFuture() throws OutOfRangeException, FutureTimeException {
        TransactionRequest transactionRequest = new TransactionRequest("40.2", "2021-02-01T14:01:30.312Z");
        Transaction transaction = Transaction.from(transactionRequest);
        long currentTimestamp = 1612188020312L;
        transaction.isValid(currentTimestamp);
        log.info("Second transaction --> {}", transaction);
    }

    @Test(expected = OutOfRangeException.class)
    public void test_transactionOutOFRange() throws OutOfRangeException, FutureTimeException {
        TransactionRequest transactionRequest = new TransactionRequest("40.2", "2021-02-01T14:01:30.312Z");
        Transaction transaction = Transaction.from(transactionRequest);
        long currentTimestamp = 1612188190312L;
        transaction.isValid(currentTimestamp);
        log.info("Third transaction --> {}", transaction);
    }

    @Test
    public void test_validTransaction() throws OutOfRangeException, FutureTimeException {
        TransactionRequest transactionRequest = new TransactionRequest("40.2", "2021-02-01T14:01:30.312Z");
        Transaction transaction = Transaction.from(transactionRequest);
        long currentTimestamp = 1612188098312L;
        assertTrue(transaction.isValid(currentTimestamp));
        log.info("Fourth transaction --> {}", transaction);
    }
}