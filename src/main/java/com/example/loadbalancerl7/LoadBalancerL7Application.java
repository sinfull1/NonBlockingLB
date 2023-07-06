package com.example.loadbalancerl7;

import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



@SpringBootApplication
public class LoadBalancerL7Application {


    public static void main(String[] args) {
        SpringApplication.run(LoadBalancerL7Application.class, args);
    }
}