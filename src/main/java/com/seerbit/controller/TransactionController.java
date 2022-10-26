package com.seerbit.controller;

import com.seerbit.model.Transaction;
import com.seerbit.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction){
        return transactionService.saveTransaction(transaction);
    }


    @GetMapping({"id"})
    public Transaction findTransactionById(@PathVariable int id){
        return transactionService.getTransactionById(id);
    }

    @PutMapping
    public Transaction updateTransaction(@RequestBody Transaction transaction){
        return transactionService.updateTransaction(transaction);
    }

    @DeleteMapping({"id"})
    public String deleteTransaction(@PathVariable int id){
        return transactionService.deleteTransaction(id);
    }
}
