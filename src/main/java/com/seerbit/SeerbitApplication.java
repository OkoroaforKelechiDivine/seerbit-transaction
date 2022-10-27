package com.seerbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Slf4j
@EnableScheduling
public class SeerbitApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeerbitApplication.class, args);
        log.info("Application started on port 8080");
    }

}
