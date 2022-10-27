package com.seerbit.manager;

import com.seerbit.Exception.FutureTimeException;
import com.seerbit.Exception.OutOfRangeException;
import com.seerbit.config.StoreImpl;
import com.seerbit.model.Constant;
import com.seerbit.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Component
@Primary
@Slf4j
public class TransactionsManagerImpl implements TransactionsManager {
    private StoreImpl[] statisticsStores;

    @PostConstruct
    public void init() {
        statisticsStores = new StoreImpl[Constant.TOTAL_WINDOW_SIZE_MILLIS / Constant.WINDOW_SIZE_MILLIS];
        initStores();
    }

    @Override
    public void addTransaction(Transaction transaction, long currentTimestamp) throws OutOfRangeException, FutureTimeException {
        if (transaction.isValid(currentTimestamp)) {
            aggregate(transaction, currentTimestamp);
        }
    }


    @Override
    public List<StoreImpl> getValidStatisticsStore(long currentTimestamp) {
        return Arrays.stream(statisticsStores).filter(ss -> ss.isValid(currentTimestamp)).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        initStores();
    }

    private void initStores() {
        for (int i = 0; i< statisticsStores.length; i++) {
            statisticsStores[i] = new StoreImpl();
        }
    }

    private void aggregate(Transaction transaction, long currentTimestamp) {
        int index = transaction.getIndex(currentTimestamp);
        StoreImpl statisticsStore = statisticsStores[index];
        try {
            statisticsStore.getLock().writeLock().lock();
            if (statisticsStore.isEmpty()) {
                statisticsStore.create(transaction);
            } else {
                if (statisticsStore.isValid(currentTimestamp)) {
                    statisticsStore.merge(transaction);
                } else {
                    statisticsStore.clear();
                    statisticsStore.create(transaction);
                }
            }
        } finally {
            statisticsStore.getLock().writeLock().unlock();
        }
    }
}