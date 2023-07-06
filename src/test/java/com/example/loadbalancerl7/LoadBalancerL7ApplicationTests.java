package com.example.loadbalancerl7;

import com.example.loadbalancerl7.entity.SlidingWindowRateLimiter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;


class LoadBalancerL7ApplicationTests {

    ExecutorService executorService = Executors.newFixedThreadPool(10);
    AtomicLong patomicLong = new AtomicLong();
    AtomicLong atomicLong = new AtomicLong();
    Random random = new Random();
    @Test
    void contextLoads() throws InterruptedException {
        long start = System.currentTimeMillis();
        SlidingWindowRateLimiter slidingWindowRateLimiter = new SlidingWindowRateLimiter(2, Duration.ofMillis(1000));
        while (true) {
            Thread.sleep(random.nextInt(80)+100);
            executorService.submit(()->{
                System.out.println("request number" + atomicLong.incrementAndGet() +" on "+  Thread.currentThread());
                if(slidingWindowRateLimiter.isAllowed(atomicLong.get())){
                    System.out.println("procees");
                    patomicLong.addAndGet(1L);
                } else{
                    System.out.println("-----------");
                    System.out.println("Processed"+ patomicLong.get());
                    System.out.println("start"+ start);
                    System.out.println("end"+ System.currentTimeMillis());
                    System.out.println(patomicLong.get()*1000/(System.currentTimeMillis()-start));
                    System.out.println("-----------");
                }
            });
        }
    }

}
