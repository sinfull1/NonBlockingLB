package com.example.loadbalancerl7.utils;

import com.example.loadbalancerl7.entity.Worker;
import com.example.loadbalancerl7.entity.WorkerPool;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class LoadBalancingStrategyImpl implements LoadBalancingStrategy {


    final WorkerPool workerPool;


    public LoadBalancingStrategyImpl(WorkerPool workerPool) {
        this.workerPool = workerPool;
    }
    @Override
    public Worker getWorker(String identifier) {
        return workerPool.getWorker(identifier);
    }
}
