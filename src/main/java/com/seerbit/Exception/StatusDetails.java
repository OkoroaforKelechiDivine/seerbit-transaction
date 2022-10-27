package com.seerbit.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusDetails {

    private String message;

    private LocalDateTime timestamp;

    private String status;
}
