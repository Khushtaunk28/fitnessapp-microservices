package com.fitness.activityService.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserValidation {

    @Autowired
    private final WebClient webClient;

    public boolean validateUser(String userId){
        try {
            log.info("Validating user using web client");
            return webClient.get()
                    .uri("api/users/{userId}/validate",userId)
                    //.uri("/api/users/health-check")
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
        } catch (WebClientResponseException e) {
            log.error("Validation failed with status {} and body: {}", e.getStatusCode(), e.getResponseBodyAsString());
            if(e.getStatusCode()== HttpStatus.NOT_FOUND)
                throw new RuntimeException("User not found");
            else if(e.getStatusCode()== HttpStatus.BAD_REQUEST)
                throw new RuntimeException("Invalid user");
        }
        return false;
    }
}