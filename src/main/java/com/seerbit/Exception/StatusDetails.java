package com.seerbit.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusDetails {

    private String message;

    @NotEmpty
    private LocalDateTime timestamp;

    private String status;
}
