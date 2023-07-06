package com.example.loadbalancerl7.config;


import com.example.loadbalancerl7.entity.WebClientWorker;
import com.example.loadbalancerl7.entity.WorkerPool;
import com.example.loadbalancerl7.entity.ConsistentHashRingPool;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Configuration {

    @Bean
    public WorkerPool getConsistentHashRingPool() {
        ConsistentHashRingPool pool = new ConsistentHashRingPool(2);
        for (int i = 0; i < 10; i++) {
            pool.add(new WebClientWorker(UUID.randomUUID().toString(), WebClientBuilder.createWebClient("http://httpbin.org/")));
        }
        return pool;
    }


}
