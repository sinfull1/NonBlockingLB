package com.example.loadbalancerl7.utils;

import com.example.loadbalancerl7.interfaces.LoadBalancingStrategy;
import com.example.loadbalancerl7.interfaces.Worker;
import com.example.loadbalancerl7.interfaces.WorkerPool;
import org.springframework.stereotype.Component;

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
