package com.seerbit.service;

import com.seerbit.Exception.FutureTimeException;
import com.seerbit.Exception.OutOfRangeException;
import com.seerbit.manager.TransactionsManager;
import com.seerbit.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    private TransactionsManager transactionsStore;

    @Override
    public void addTransaction(Transaction transaction) throws OutOfRangeException, FutureTimeException {
        long currentTimestamp = Instant.now().toEpochMilli();
        transactionsStore.addTransaction(transaction, currentTimestamp);
    }

    @Override
    public void deleteAllTransactions() {
        transactionsStore.clear();
    }
}
