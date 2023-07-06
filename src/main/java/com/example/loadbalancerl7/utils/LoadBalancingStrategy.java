package com.example.loadbalancerl7.utils;

import com.example.loadbalancerl7.entity.Worker;
import com.example.loadbalancerl7.entity.WorkerPool;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class LoadBalancingStrategy {


    final WorkerPool workerPool;

    AtomicLong atomicLong = new AtomicLong();

    public LoadBalancingStrategy(WorkerPool workerPool) {
        this.workerPool = workerPool;
    }

    public Worker getRoundRobin() {
        atomicLong.addAndGet(1);
        String identifier = (String.valueOf(atomicLong.get() % workerPool.size()));
        return workerPool.getWorker(identifier);
    }

    public Worker getLeastRequested() {
        atomicLong.addAndGet(1);
        String identifier = (String.valueOf(atomicLong.get() % workerPool.size()));
        return workerPool.getWorker(identifier);
    }

    public Worker getHashRing(String identifier) {
        return workerPool.getWorker(identifier);
    }

}
