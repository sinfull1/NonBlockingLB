package com.example.loadbalancerl7.entity;
import java.time.Duration;
import java.time.Instant;
import java.util.Queue;
import java.util.concurrent.*;

public class SWRateLimiter {
    private int limit;
    private Duration interval;
    private Queue<Instant> requestQueue;
    private ScheduledExecutorService executorService;
    private ScheduledFuture<?> refillTask;

    public SWRateLimiter(int limit, Duration interval) {
        this.limit = limit;
        this.interval = interval;
        this.requestQueue = new LinkedBlockingQueue<>(limit);
        this.executorService = Executors.newSingleThreadScheduledExecutor();
        this.refillTask = executorService.scheduleAtFixedRate(this::refillQueue, interval.toMillis(), interval.toMillis(), TimeUnit.MILLISECONDS);
    }

    public boolean isAllowed(Long atomicLong) {
        Instant currentTime = Instant.now();


        while (true) {
            Instant peeked = requestQueue.peek();
            if (peeked == null || !peeked.isBefore(currentTime.minus(interval))) {
                break;
            }
            System.out.println("polling number:" + atomicLong +" on "+  Thread.currentThread());
            requestQueue.poll();
        }

        System.out.println("offering:" + atomicLong +" on "+  Thread.currentThread() + " queue size " + requestQueue.size());
        if (requestQueue.size() < limit) {
            requestQueue.offer(currentTime);
            return true;
        }

        return false;
    }

    private void refillQueue() {
        requestQueue.clear();
    }

    public void shutdown() {
        refillTask.cancel(true);
        executorService.shutdown();
    }
}