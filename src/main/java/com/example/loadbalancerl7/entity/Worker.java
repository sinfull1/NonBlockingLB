package com.example.loadbalancerl7.entity;

import reactor.core.publisher.Mono;

public interface Worker {


    public String getId();

    Mono<String> doWork(Work work) throws InterruptedException;

}
