package com.example.loadbalancerl7.interfaces;


public interface LoadBalancingStrategy {
    Worker getWorker(String identifier);

}
