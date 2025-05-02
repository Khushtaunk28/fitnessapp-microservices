package com.fitnessapp.aiservice.service;

import com.fitnessapp.aiservice.entity.Recommendation;
import com.fitnessapp.aiservice.repository.RecommendationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RecommendationRepo recommendationRepo;

    public Recommendation getActivityRec(String activityId) {
        try {
            return recommendationRepo.findByActivityId(activityId);
        } catch (Throwable e) {
            throw new RuntimeException("Activity not found"+activityId);
        }
    }

    public List<Recommendation> getUserRec(String userId) {
        try {
            return recommendationRepo.findByUserId(userId);
        } catch (NullPointerException e) {
            throw new RuntimeException("no Recommendations found");
        }
    }
}
