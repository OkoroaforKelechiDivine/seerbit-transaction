package com.seerbit.controller;

import com.seerbit.model.Statistics;
import com.seerbit.service.StatisticsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class StatisticsController {

    @Autowired
    private StatisticsServiceImpl statisticsService;

    @GetMapping("statistics")
    public ResponseEntity<Statistics> getStatistics(){
        return ResponseEntity.status(HttpStatus.OK).body(statisticsService.getStatistics());
    }
}
