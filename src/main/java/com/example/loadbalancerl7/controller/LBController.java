package com.example.loadbalancerl7.controller;

import com.example.loadbalancerl7.entity.Work;
import com.example.loadbalancerl7.entity.Worker;
import com.example.loadbalancerl7.utils.LoadBalancingStrategy;
import com.example.loadbalancerl7.utils.LoadBalancingStrategyImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
@RestController
@Slf4j
public class LBController {

    private List<String> users = Arrays.asList("User1","User2","User3","User4","User5","User6");
    private Random random = new Random();

    final LoadBalancingStrategy loadBalancingStrategy;

    public LBController(LoadBalancingStrategyImpl loadBalancingStrategy) {
        this.loadBalancingStrategy = loadBalancingStrategy;
    }

    @GetMapping("/get")
    public Mono<String> queryRoundRobin() throws InterruptedException {
        String user = users.get(random.nextInt(users.size()));
        Worker worker = loadBalancingStrategy.getWorker(user);
        Work work = new Work();
        work.setUri("/get");
        log.info("Processing for User:" + user + " one worker id " + worker.getId());
        return worker.doWork(work);
    }


}
