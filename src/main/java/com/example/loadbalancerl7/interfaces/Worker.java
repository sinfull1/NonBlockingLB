package com.example.loadbalancerl7.interfaces;

import com.example.loadbalancerl7.entity.Work;
import reactor.core.publisher.Mono;

public interface Worker<T> {


    public String getId();

    T doWork(Work work) throws InterruptedException;

}
