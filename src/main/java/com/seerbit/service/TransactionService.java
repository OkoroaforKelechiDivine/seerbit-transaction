package com.seerbit.service;

import com.seerbit.model.Transaction;
import com.seerbit.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction saveTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions(){
        return transactionRepository.getAllTransactions();
    }

    public Transaction getTransactionById(int id){
        return transactionRepository.findById(id);
    }

    public String deleteTransaction(int id){
        transactionRepository.delete(id);
        return "Transaction removed " + id;
    }
    public Transaction updateTransaction(Transaction transaction){
        return transactionRepository.update(transaction);
    }
}
