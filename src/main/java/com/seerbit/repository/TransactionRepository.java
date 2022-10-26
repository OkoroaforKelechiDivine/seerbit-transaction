package com.seerbit.repository;

import com.seerbit.model.Transaction;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TransactionRepository {

    private List<Transaction> database = new ArrayList<>();


    public void createTransaction(){
        database = List.of(
                new Transaction(1, "123.45", LocalDateTime.now()),
                new Transaction(2, "678.90", LocalDateTime.now()),
                new Transaction(3, "547.45", LocalDateTime.now()),
                new Transaction(4, "679.22", LocalDateTime.now())
        );
    }

    public List<Transaction> getAllTransactions(){
        return database;
    }

    public Transaction findById(int transactionId){
        for (int index = 0; index < database.size(); index++){
            if (database.get(index).getId() == (transactionId)){
                return database.get(index);
            }
        }
        return null;
    }

    public List<Transaction> searchTransactionAmount(String amount){
        return database.stream().filter(x -> x.getAmount().startsWith(amount)).collect(Collectors.toList());
    }

    public Transaction save(Transaction t){
        Transaction transaction = new Transaction();
        transaction.setId(t.getId());
        transaction.setAmount(t.getAmount());
        transaction.setTimestamp(t.getTimestamp());
        database.add(transaction); // add transaction to database after creation
        return transaction;
    }

    public String delete(int id){
        database.removeIf(x -> x.getId() == (id));
        return null;
    }

    public Transaction update(Transaction t){
        int i = 0;
        int id = 0;
        for (int index = 0; index < database.size(); index++){
            if (database.get(index).getId() == (t.getId())){
                id = t.getId();
                i = 1;
                break;
            }
        }

        Transaction transaction = new Transaction();
        transaction.setId(id);
        transaction.setAmount(t.getAmount());
        transaction.setTimestamp(LocalDateTime.now());
        database.set(i, transaction);
        return  transaction;
    }
}
