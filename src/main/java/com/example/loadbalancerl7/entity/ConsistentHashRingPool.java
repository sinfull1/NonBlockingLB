package com.example.loadbalancerl7.entity;

import com.example.loadbalancerl7.entity.Worker;
import com.example.loadbalancerl7.entity.WorkerPool;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;


public class ConsistentHashRingPool implements WorkerPool {
    private SortedMap<Integer, Worker> ring;
    private Map<String, Boolean> nodes;
    private int numReplicas;

    public ConsistentHashRingPool(int numReplicas) {
        this.ring = new TreeMap<>();
        this.nodes = new HashMap<>();
        this.numReplicas = numReplicas;
    }

    @Override
    public void add(Worker worker) {
        for (int i = 0; i < numReplicas; i++) {
            String virtualNode = worker.getId() + "-V" + i;
            nodes.put(virtualNode, true);
            ring.put(hash(virtualNode), worker);
        }
    }

    @Override
    public void remove(Worker worker) {
        Worker workers = null;
        for (Map.Entry<Integer, Worker> entry : ring.entrySet()) {
            if (entry != null) {
                workers = entry.getValue();
                break;
            }
        }
        for (int i = 0; i < numReplicas; i++) {
            String virtualNode = workers.getId() + "-V" + i;
            nodes.remove(virtualNode);
            ring.remove(hash(virtualNode));
        }
    }

    @Override
    public int size() {
        return nodes.size();
    }

    @Override
    public Worker getWorker(String key) {
        if (ring.isEmpty()) {
            return null;
        }
        int hashKey = hash(key);
        SortedMap<Integer, Worker> tailMap = ring.tailMap(hashKey);
        int nodeHash = tailMap.isEmpty() ? ring.firstKey() : tailMap.firstKey();
        System.out.println("Queued on:" + nodeHash);
        return ring.get(nodeHash);
    }


    private int hash(String key) {
        // Implement your hash function here
        // You can use built-in hash functions like hashCode() or a different algorithm
        // For simplicity, let's assume the hashCode() function is used
        return key.hashCode();
    }
}
