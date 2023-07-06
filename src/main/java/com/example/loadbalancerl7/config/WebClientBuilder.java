package com.example.loadbalancerl7.config;

import org.springframework.web.reactive.function.client.WebClient;

public class WebClientBuilder {

    public static WebClient createWebClient(String url) {
        return WebClient.builder()
                .baseUrl(url)
                .build();
    }
}
