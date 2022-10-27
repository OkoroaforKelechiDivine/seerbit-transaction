package com.seerbit.service;

import com.seerbit.model.Transaction;
import com.seerbit.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    public void saveTransaction(Transaction transaction){
        BigDecimal bigDecimal = new BigDecimal(transaction.getAmount()); // will convert the string amount to big decimal
        log.info("Converted String amount to BigDecimal " + bigDecimal);
        repository.save(transaction);
    }

    public Transaction findByAmount(String id) {
        return repository.findByAmount(id);
    }
}
