package com.seerbit.repository;

import com.seerbit.model.Statical;
import com.seerbit.model.Transaction;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TransactionRepository {

    private List<Transaction> database = new ArrayList<>();

    public Transaction findByAmount(String id){
        for (Transaction transaction : database) {
            if (transaction.getAmount().equals(id)) {
                return transaction;
            }
        }
        return null;
    }

    public void save(Transaction t){
        Transaction transaction = new Transaction();
        transaction.setAmount(t.getAmount());
        transaction.setTimestamp(LocalDate.now());
        database.add(transaction); // add transaction to database after creation
    }

    public Statical getStatistics(Statical statical){
        return statical;
    }
}
