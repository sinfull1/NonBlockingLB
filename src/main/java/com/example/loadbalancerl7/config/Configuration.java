package com.example.loadbalancerl7.config;


import com.example.loadbalancerl7.entity.SWRateLimiter;
import com.example.loadbalancerl7.entity.WebClientWorker;
import com.example.loadbalancerl7.entity.WorkerPool;
import com.example.loadbalancerl7.entity.ConsistentHashRingPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.UUID;

@Component
public class Configuration {
    @Value("${backend.url}")
    private String backendUrl;
    @Value("${lb.rate.limit.requests}")
    private Integer rateLimiterRequests;

    @Value("${lb.rate.limit.interval.millis}")
    private Integer rateLimitIntervalMillis;
    @Bean
    public WorkerPool getConsistentHashRingPool() {
        // For this exmaple we are using just one backend, in actual case you would want to use
        // A set of urls representing your backend, or some kind of service discovery mechaism

        ConsistentHashRingPool pool = new ConsistentHashRingPool(2);
        for (int i = 0; i < 10; i++) {
            pool.add(new WebClientWorker(UUID.randomUUID().toString(),
                    WebClientBuilder.createWebClient(backendUrl),
                    new SWRateLimiter(rateLimiterRequests,
                            Duration.ofMillis(rateLimitIntervalMillis))));
        }
        return pool;
    }


}
