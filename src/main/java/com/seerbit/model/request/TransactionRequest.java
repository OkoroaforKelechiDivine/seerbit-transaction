package com.seerbit.model.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class TransactionRequest {

    @NonNull
    private String amount;

    @NonNull
    private String timestamp;
}