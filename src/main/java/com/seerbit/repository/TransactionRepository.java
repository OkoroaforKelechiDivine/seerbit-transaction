package com.seerbit.repository;

import com.seerbit.model.Transaction;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TransactionRepository {

    private final List<Transaction> database = new ArrayList<>();

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
        transaction.setTimestamp(LocalDateTime.now());
        database.add(transaction); // add transaction to database after creation
    }
}
