package com.example.loadbalancerl7.utils;

import com.example.loadbalancerl7.entity.Worker;


public interface LoadBalancingStrategy {
    Worker getWorker(String identifier);

}
