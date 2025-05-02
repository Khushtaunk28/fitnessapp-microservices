package com.fitness.activityService.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Webconfig {

    // 1️⃣ For talking to Eureka‐registered services
    @Bean @LoadBalanced
    public WebClient.Builder lbWebClientBuilder() {
        return WebClient.builder();
    }

    // 2️⃣ For calling arbitrary external URLs
    @Bean
    public WebClient plainWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl("http://USER-SERVICE")
                .build();
    }
}

