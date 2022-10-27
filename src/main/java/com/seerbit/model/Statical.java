package com.seerbit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statical {

    private BigDecimal sum;

    private BigDecimal average;

    private BigDecimal max;

    private BigDecimal min;

    private Long count;
}
