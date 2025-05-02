package com.fitness.activityService.service;

import com.fitness.activityService.Repository.ActivityRepo;
import com.fitness.activityService.dto.ActivityRequest;
import com.fitness.activityService.dto.ActivityResponse;
import com.fitness.activityService.entity.Activity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
@Slf4j
public class ActivityService {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Autowired
    private UserValidation userValidation;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    private ActivityRepo activityRepo;

    public ActivityResponse trackActivity(ActivityRequest request) {
        //user validation
        boolean isValidUser = userValidation.validateUser(request.getUserId());
        if (!isValidUser) {
            throw new RuntimeException("Invalid User: " + request.getUserId());
        }

        Activity activity = Activity.builder()
                .userId(request.getUserId())
                .type(request.getType())
                .duration(request.getDuration())
                .caloriesBurned(request.getCaloriesBurned())
                .startTime(request.getStartTime())
                .additionalMetrics(request.getAdditionalMetrics())
                .build();
        Activity savedactivity=activityRepo.save(activity);

        //publish to rabbitmq for ai processing
        try {
            rabbitTemplate.convertAndSend(exchange,routingKey,savedactivity);
        }catch (Exception e) {
            log.error("Failed to publish to Rabbit mQ"+e.getMessage());
        }

        return mapToResponse(savedactivity);

    }
    private ActivityResponse mapToResponse(Activity activity) {
        ActivityResponse response = new ActivityResponse();
        response.setUserId(activity.getUserId());
        response.setId(activity.getId());
        response.setType(activity.getType());
        response.setDuration(activity.getDuration());
        response.setCaloriesBurned(activity.getCaloriesBurned());
        response.setStartTime(activity.getStartTime());
        response.setAdditionalMetrics(activity.getAdditionalMetrics());
        response.setCreatedAt(activity.getCreatedAt());
        response.setUpdatedAt(activity.getUpdatedAt());
        return response;
    }

    public List<ActivityResponse> getActivitybyUserId(String userId) {
        List<Activity> activity = activityRepo.findByUserId(userId);
        return activity.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ActivityResponse getActivityByActivityId(String activityId) {
        return activityRepo.findById(activityId).map(this::mapToResponse)
                .orElseThrow(()-> new RuntimeException("Activity not found"));

    }
}

