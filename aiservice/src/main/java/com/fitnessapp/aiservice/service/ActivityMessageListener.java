package com.fitnessapp.aiservice.service;

import com.fitnessapp.aiservice.entity.Activity;
import com.fitnessapp.aiservice.entity.Recommendation;
import com.fitnessapp.aiservice.repository.RecommendationRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityMessageListener {

    private final ActivityAiService activityAiService;
    private final RecommendationRepo recommendationRepo;

    @Value("${rabbitmq.queue.name}")
    private String queue;

    @RabbitListener(queues = "activity.queue")
    public void processActiviy(Activity activity){
        log.info("activity is being listened {}",activity.getId());
        Recommendation recommendation = activityAiService.generateRecommendation(activity);
        recommendationRepo.save(recommendation);
        log.info("recommendation saved to repository");
    }
}
