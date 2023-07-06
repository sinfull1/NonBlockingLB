package com.example.loadbalancerl7.controller;


import com.example.loadbalancerl7.config.WebClientBuilder;
import com.example.loadbalancerl7.entity.WebClientWorker;
import com.example.loadbalancerl7.entity.WorkerPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Component
@RestController
public class WorkerPoolController {

    @Autowired
    WorkerPool workerPool;

    @GetMapping("/add")
    public void add() throws InterruptedException {
        workerPool.add(new WebClientWorker(UUID.randomUUID().toString(), WebClientBuilder.createWebClient("http://httpbin.org/")));
    }

    @GetMapping("/remove")
    public void removew() throws InterruptedException {
        workerPool.remove(new WebClientWorker());
    }


}
