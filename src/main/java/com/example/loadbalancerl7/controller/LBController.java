package com.example.loadbalancerl7.controller;

import com.example.loadbalancerl7.entity.Work;
import com.example.loadbalancerl7.entity.Worker;
import com.example.loadbalancerl7.utils.LoadBalancingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
@RestController
public class LBController {

    private List<String> users = Arrays.asList("Sid", "As", "o", "dsfgujhgsdfkh", "sdfkujhedfbvl");
    private Random random = new Random();
    @Autowired
    LoadBalancingStrategy loadBalancingStrategy;

    @GetMapping("/get")
    public Mono<String> queryRoundRobin(String request) throws InterruptedException {
        String user = users.get(random.nextInt(5));
        System.out.println("user:" + user);
        Worker worker = loadBalancingStrategy.getHashRing(user);
        Work work = new Work();
        work.setUri("/get");
        System.out.println("Executing on" + worker.getId());
        return worker.doWork(work);
    }


}
