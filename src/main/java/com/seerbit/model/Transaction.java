package com.seerbit.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @NonNull
    private int id;

    @NonNull
    private String amount;

    private LocalDateTime timestamp;

}
