package com.seerbit.service;

import com.seerbit.Exception.FutureTimeException;
import com.seerbit.Exception.OutOfRangeException;
import com.seerbit.model.Transaction;


public interface TransactionService {
    void addTransaction(Transaction transaction) throws OutOfRangeException, FutureTimeException;
    void deleteAllTransactions();
}
