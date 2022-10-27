package com.seerbit.service;

import com.seerbit.manager.TransactionsManager;
import com.seerbit.config.StoreImpl;
import com.seerbit.model.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private TransactionsManager transactionsManager;

    @Override
    public Statistics getStatistics() {
        long currentTime = System.currentTimeMillis();
        List<StoreImpl> validStatisticsStore = transactionsManager.getValidStatisticsStore(currentTime);
        Statistics result = new Statistics();
        if (validStatisticsStore.isEmpty()) {
            result.resetToZero();
            return result;
        }
        validStatisticsStore.forEach(store -> store.addToResult(result));
        return result;
    }
}
