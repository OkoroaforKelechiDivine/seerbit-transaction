package com.seerbit.model;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private String amount;

    @NotEmpty
    private LocalDate timestamp;

}
