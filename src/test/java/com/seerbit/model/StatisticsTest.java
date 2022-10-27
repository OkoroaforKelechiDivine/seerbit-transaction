package com.seerbit.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class StatisticsTest {


    @Test
    @DisplayName("Add statistics")
    public void testA_addStatistics() {
        Statistics statistics1 = new Statistics();
        statistics1.setSum(BigDecimal.ONE);
        statistics1.setCount(1);
        statistics1.setMax(BigDecimal.ONE);
        statistics1.setMin(BigDecimal.ONE);
        statistics1.setAvg(BigDecimal.ONE);
        log.info("Created first statistics --> {}",statistics1);

        Statistics statistics2 = new Statistics();
        statistics2.setSum(BigDecimal.valueOf(11));
        statistics2.setCount(2);
        statistics2.setMax(BigDecimal.TEN);
        statistics2.setMin(BigDecimal.ONE);
        statistics2.setAvg(BigDecimal.valueOf(5.5));
        log.info("Created second statistics --> {}",statistics2);


        Statistics result = new Statistics();
        result.addStatistics(statistics1);
        result.addStatistics(statistics2);
        log.info("Final result --> {}",result);


        assertEquals(3, result.getCount());
        assertEquals("12.00", result.getSum().toString());
        assertEquals("4.00", result.getAvg().toString());
        assertEquals("10.00", result.getMax().toString());
        assertEquals("1.00", result.getMin().toString());
    }

}