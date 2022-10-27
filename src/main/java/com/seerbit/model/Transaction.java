package com.seerbit.model;

import com.seerbit.model.request.TransactionRequest;
import com.seerbit.Exception.FutureTimeException;
import com.seerbit.Exception.OutOfRangeException;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.Instant;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Slf4j
public class Transaction {

    @NonNull
    private BigDecimal amount;

    @NonNull
    private long timestamp;

    public static Transaction from(TransactionRequest transactionRequest) {
        Instant instant = Instant.parse(transactionRequest.getTimestamp());
        BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(transactionRequest.getAmount()));
        long timestamp = instant.toEpochMilli();
        return new Transaction(amount, timestamp);
    }

    public boolean isValid(long currentTimestamp) throws FutureTimeException, OutOfRangeException {
        if (currentTimestamp < getTimestamp()) {
            throw new FutureTimeException();
        }
        if (currentTimestamp - getTimestamp() >= Constant.TOTAL_WINDOW_SIZE_MILLIS) {
            throw new OutOfRangeException();
        }
        return true;
    }

    public int getIndex(long currentTimestamp) {
        long transactionTime = getTimestamp();
        return (int)((currentTimestamp - transactionTime) / Constant.WINDOW_SIZE_MILLIS) % (Constant.TOTAL_WINDOW_SIZE_MILLIS / Constant.WINDOW_SIZE_MILLIS);
    }
}