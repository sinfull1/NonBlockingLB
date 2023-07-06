package com.example.loadbalancerl7;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.IOException;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoadBalancerTests {

    @Autowired
    private WebTestClient webTestClient;

    ObjectMapper objectMapper = new ObjectMapper();
    @Test
    void shouldReturnThreeDefaultUsers() throws IOException {
        byte[] bytes = this.webTestClient
                .get()
                .uri("/get")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectHeader()
                .contentType("text/plain;charset=UTF-8")
                .expectBody().returnResult().getRequestBodyContent();


    }

}
