package com.seerbit.service;

import com.seerbit.Exception.FutureTimeException;
import com.seerbit.Exception.OutOfRangeException;
import com.seerbit.config.StoreImpl;
import com.seerbit.manager.TransactionsManagerImpl;
import com.seerbit.model.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionServiceImplTest {

    @Autowired
    private TransactionServiceImpl transactionService;

    @Autowired
    private TransactionsManagerImpl transactionsManager;

    @Before
    public void before(){
        transactionsManager.clear();
    }

    @Test(expected= OutOfRangeException.class )
    public void test_throwExceptionWhenInputHasInvalidTimestamp() throws OutOfRangeException, FutureTimeException {
        Transaction txn = new Transaction(BigDecimal.valueOf(12.5), 123);
        transactionService.addTransaction(txn);
    }

    @Test
    public void test_emptyStoreWithInValidTime() {
        long time = System.currentTimeMillis();
        Transaction txn = new Transaction(BigDecimal.valueOf(12.5), 123);
        try {
            transactionService.addTransaction(txn);
        } catch (OutOfRangeException | FutureTimeException ignored) {}

        List<StoreImpl> storeList = transactionsManager.getValidStatisticsStore(time);

        assertNotNull(storeList);
        assertEquals(0, storeList.size());
    }

    @Test
    public void test_concurrentTransactions(){
        final ExecutorService executor = Executors.newFixedThreadPool(10);
        long time = System.currentTimeMillis();
        try{
            IntStream.range(0, 100).forEach(i-> {
                executor.execute(()->{
                    Transaction t = new Transaction(BigDecimal.valueOf(15L * i), time - (i + i * 100L) );
                    try {
                        Thread.sleep(1);
                        transactionService.addTransaction(t);
                    } catch (Exception ignored) {}
                });

            });

        }finally{
            executor.shutdown();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {}

        List<StoreImpl> storeList = transactionsManager.getValidStatisticsStore(time);

        assertNotNull(storeList);

        int sum = 0;
        for (StoreImpl agg : storeList){
            sum += agg.getStatistics().getCount();
        }
        assertEquals(100, sum);
    }

    @Test
    public void test_concurrentTransactionsAndThenDelete(){
        final ExecutorService executor = Executors.newFixedThreadPool(10);
        long time = System.currentTimeMillis();
        try{
            IntStream.range(0, 100).forEach(i-> {
                executor.execute(()->{
                    Transaction t = new Transaction(BigDecimal.valueOf(15L * i), time - (i + i * 100L) );
                    try {
                        Thread.sleep(1);
                        transactionService.addTransaction(t);
                    } catch (Exception ignored) {}
                });

            });

        }finally{
            executor.shutdown();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {}

        transactionService.deleteAllTransactions();

        List<StoreImpl> list = transactionsManager.getValidStatisticsStore(time);

        assertNotNull(list);
        assertEquals(0, list.size());
    }
}