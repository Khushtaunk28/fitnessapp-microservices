package com.fitnessapp.aiservice.repository;

import com.fitnessapp.aiservice.entity.Recommendation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RecommendationRepo extends MongoRepository<Recommendation, String> {

    List<Recommendation> findByUserId(String userId);
    Recommendation findByActivityId(String activityId);
}
