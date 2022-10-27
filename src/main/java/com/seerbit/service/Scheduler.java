package com.seerbit.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class Scheduler {

    @Scheduled(fixedDelay = 10000)
    public void scheduler() {
        LocalDateTime current = LocalDateTime.now();
        log.info("Scheduler time " + current);
    }
}
