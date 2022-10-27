package com.seerbit.config;

import com.seerbit.Exception.FutureTimeException;
import com.seerbit.Exception.OutOfRangeException;
import com.seerbit.manager.TransactionsManager;
import com.seerbit.model.Statistics;
import com.seerbit.model.Transaction;
import com.seerbit.service.TransactionServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class StoreImplTest {

    @Autowired
    private TransactionServiceImpl transactionService;

    @Autowired
    private TransactionsManager transactionsManager;

    @Test
    @DisplayName("Create from Transaction")
    public void test_createFromTransaction() {
        Transaction transaction = new Transaction(BigDecimal.valueOf(12.5), 123L);
        StoreImpl statisticsStore = new StoreImpl();
        statisticsStore.create(transaction);
        assertEquals(123L, statisticsStore.getTimestamp());
        assertEquals("12.50", statisticsStore.getStatistics().getSum().toString());
        assertEquals(1, statisticsStore.getStatistics().getCount());
    }

    @Test
    @DisplayName("Aggregate Result")
    public void test_aggregateResult() {
        long time = Instant.now().toEpochMilli();
        final BigDecimal[] sum = {BigDecimal.ZERO};
        IntStream.range(0, 100).forEach(i->{
            Transaction t = new Transaction(BigDecimal.valueOf(i),time - i * 100L);
            sum[0] = sum[0].add(BigDecimal.valueOf(i));
            try {
                transactionService.addTransaction(t);
            } catch (OutOfRangeException | FutureTimeException ignored) {}
        });
        Statistics result = new Statistics();
        List<StoreImpl> validStatisticsStore = transactionsManager.getValidStatisticsStore(System.currentTimeMillis());
        validStatisticsStore.forEach(store -> store.addToResult(result));

        Statistics expected = new Statistics();
        expected.setMin(BigDecimal.ZERO);
        expected.setMax(BigDecimal.valueOf(99));
        expected.setCount(100);
        expected.setSum(BigDecimal.valueOf(4950));
        expected.setAvg(BigDecimal.valueOf(49.5));
        assertEquals(expected, result);
    }
}