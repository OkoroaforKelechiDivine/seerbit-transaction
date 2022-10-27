package com.seerbit.controller;

import com.seerbit.DTO.ResponseDetails;
import com.seerbit.Exception.StatusDetails;
import com.seerbit.model.Transaction;
import com.seerbit.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("transactions")
    public ResponseEntity<?> createTransaction(@Valid @RequestBody Transaction transaction) {
        StatusDetails statusDetails = new StatusDetails("Json format is invalid.", LocalDateTime.now(), HttpStatus.BAD_REQUEST.toString());
        if (transaction.getAmount().isEmpty()){
            return ResponseEntity.status(400).body(statusDetails);
        }
        transactionService.saveTransaction(transaction);
        ResponseDetails responseDetails = new ResponseDetails(LocalDateTime.now(), "Transaction created successfully.", HttpStatus.CREATED.toString());
        return ResponseEntity.status(201).body(responseDetails);
    }

    @GetMapping("/statistics/{id}")
    public Transaction findTransactionById(@PathVariable String id){
        return transactionService.findByAmount(id);
    }

}
