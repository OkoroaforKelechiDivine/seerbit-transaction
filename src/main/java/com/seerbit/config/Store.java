package com.seerbit.config;

import com.seerbit.model.Statistics;
import com.seerbit.model.Transaction;

public interface Store {

    void create(Transaction transaction);

    void addToResult(Statistics result);

    void merge(Transaction transaction);

    boolean isEmpty();

    void clear();

    boolean isValid(long currentTimestamp);
}
