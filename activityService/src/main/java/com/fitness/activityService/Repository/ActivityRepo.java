package com.fitness.activityService.Repository;

import com.fitness.activityService.dto.ActivityResponse;
import com.fitness.activityService.entity.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepo extends MongoRepository<Activity, String> {
    List<Activity> findByUserId(String userId);
}
