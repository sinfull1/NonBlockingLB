package com.example.loadbalancerl7.entity;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


public class WebClientWorker implements Worker {


    final String id;
    final WebClient webClient;

    final SWRateLimiter swRateLimiter;

    public WebClientWorker(String id, WebClient webClient, SWRateLimiter swRateLimiter) {
        this.id = id;
        this.webClient = webClient;
        this.swRateLimiter = swRateLimiter;
    }


    @Override
    public Mono<String> doWork(Work work) throws InterruptedException {
        // swRateLimiter.isAllowed()
        return webClient.get()
                .uri(work.uri)
                .retrieve()
                .bodyToMono(String.class);
    }

    @Override
    public String getId() {
        return id;
    }

}
