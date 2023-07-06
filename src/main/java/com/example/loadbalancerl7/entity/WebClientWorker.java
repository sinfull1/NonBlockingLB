package com.example.loadbalancerl7.entity;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


public class WebClientWorker implements Worker {

    @Override
    public String getId() {
        return id;
    }

    private String id;
    WebClient webClient;

    public WebClientWorker() {
    }

    public WebClientWorker(String id, WebClient webClient) {
        this.id = id;
        this.webClient = webClient;
    }


    @Override
    public Mono<String> doWork(Work work) throws InterruptedException {
        Thread.sleep(1000);
        return webClient.get()
                .uri(work.uri)
                .retrieve()
                .bodyToMono(String.class);
    }
}
