package com.seerbit.controller;

import com.seerbit.DTO.ResponseDetails;
import com.seerbit.Exception.StatusDetails;
import com.seerbit.model.Statical;
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

//    called every time a transaction is made
    @PostMapping("transactions")
    public ResponseEntity<?> createTransaction(@Valid @RequestBody Transaction transaction) {
        StatusDetails statusDetails = new StatusDetails("The fields might not be parsable or transaction date is in the future.", LocalDateTime.now(), HttpStatus.BAD_REQUEST.toString());
        if (transaction.getAmount().isEmpty()) {
            return ResponseEntity.status(422).body(statusDetails);
        }
        transactionService.createTransaction(transaction);
        ResponseDetails responseDetails = new ResponseDetails(LocalDateTime.now(), "Transaction created successfully.", HttpStatus.CREATED.toString());
        return ResponseEntity.status(201).body(responseDetails);
    }

//     returns the statistic based on the transactions of the last 30 seconds
    @GetMapping("/statistics")
    public Statical getStatical(@Valid @RequestBody Statical statical){
        return transactionService.getStatistics(statical);
    }
}
