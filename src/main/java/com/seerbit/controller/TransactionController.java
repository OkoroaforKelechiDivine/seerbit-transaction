package com.seerbit.controller;

import com.seerbit.model.request.TransactionRequest;
import com.seerbit.model.Transaction;
import com.seerbit.service.TransactionServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class TransactionController {

    @Autowired
    TransactionServiceImpl transactionService;

    @PostMapping("transactions")
    @SneakyThrows
    public ResponseEntity<?> addTransaction(@RequestBody TransactionRequest transactionRequest) {
        Transaction transaction = Transaction.from(transactionRequest);
        transactionService.addTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @DeleteMapping("transactions")
    public ResponseEntity<?> deleteAll() {
        transactionService.deleteAllTransactions();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
