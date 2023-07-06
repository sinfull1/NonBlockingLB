package com.example.loadbalancerl7;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;


import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoadBalancerTests {

    @Autowired
    private WebTestClient webTestClient;


    @Test
    void shouldReturnThreeDefaultUsers() throws IOException {
            this.webTestClient
                .get()
                .uri("/get").exchange().expectStatus().is2xxSuccessful();


    }

}
