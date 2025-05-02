package com.fitnessapp.aiservice.controller;

import com.fitnessapp.aiservice.entity.Recommendation;
import com.fitnessapp.aiservice.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {


    @Autowired
    private RecommendationService recommendationService;

    //getUserRecommendation (list)
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendation>> getUserRecommendations(@PathVariable String userId) {
        return ResponseEntity.ok(recommendationService.getUserRec(userId));
    }


    //getActivityRecommendation (one)
    @GetMapping("/activity/{activityId}")
    public ResponseEntity<Recommendation> getActivityRecommendations(@PathVariable String activityId) {
        return ResponseEntity.ok(recommendationService.getActivityRec(activityId));
    }


}
