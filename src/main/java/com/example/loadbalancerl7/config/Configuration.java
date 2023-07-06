package com.example.loadbalancerl7.config;


import com.example.loadbalancerl7.entity.SWRateLimiter;
import com.example.loadbalancerl7.entity.WebClientWorker;
import com.example.loadbalancerl7.interfaces.WorkerPool;
import com.example.loadbalancerl7.entity.ConsistentHashRingPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Component
public class Configuration {
    @Value("#{'${backend.url}'.split(',')}")
    private List<String> backendUrl;
    @Value("${lb.rate.limit.requests}")
    private Integer rateLimiterRequests;

    @Value("${lb.rate.limit.interval.millis}")
    private Integer rateLimitIntervalMillis;
    @Bean
    public WorkerPool getConsistentHashRingPool() {
        // For this example we are using just one backend, in actual case you would want to use
        // A set of urls representing your backend, or some kind of service discovery mechanism

        ConsistentHashRingPool pool = new ConsistentHashRingPool(2);
        for (String urls: backendUrl) {
            pool.add(new WebClientWorker(UUID.randomUUID().toString(),
                    WebClientBuilder.createWebClient(urls),
                    new SWRateLimiter(rateLimiterRequests,
                            Duration.ofMillis(rateLimitIntervalMillis))));
        }
        return pool;
    }


}
