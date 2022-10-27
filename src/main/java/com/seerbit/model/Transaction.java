package com.seerbit.model;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private String amount;

    private LocalDateTime timestamp;

}
