package com.example.loadbalancerl7.controller;


import com.example.loadbalancerl7.config.WebClientBuilder;
import com.example.loadbalancerl7.entity.SWRateLimiter;
import com.example.loadbalancerl7.entity.WebClientWorker;
import com.example.loadbalancerl7.entity.WorkerPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.UUID;

@Component
@RestController
public class WorkerPoolController {

    @Value("${backend.url}")
    private String backendUrl;
    @Value("${lb.rate.limit.requests}")
    private Integer rateLimiterRequests;

    @Value("${lb.rate.limit.interval.millis}")
    private Integer rateLimitIntervalMillis;

    final WorkerPool workerPool;

    public WorkerPoolController(WorkerPool workerPool) {
        this.workerPool = workerPool;
    }

    @GetMapping("/add")
    public void add() {
        workerPool.add(new WebClientWorker(UUID.randomUUID().toString(),
                WebClientBuilder.createWebClient(backendUrl)
        , new SWRateLimiter(rateLimiterRequests,
                Duration.ofMillis(rateLimitIntervalMillis))));
    }

    @GetMapping("/remove")
    public void remove() throws InterruptedException {
        //TODO: implement remove by getting a particular id
    }


}
