package com.seerbit.manager;

import com.seerbit.Exception.FutureTimeException;
import com.seerbit.Exception.OutOfRangeException;
import com.seerbit.config.StoreImpl;
import com.seerbit.model.Transaction;

import java.util.List;

public interface TransactionsManager {

    void addTransaction(Transaction transaction, long currentTimestamp) throws FutureTimeException, OutOfRangeException;
    List<StoreImpl> getValidStatisticsStore(long timestamp);
    void clear();
}
