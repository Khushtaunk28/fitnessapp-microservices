package com.fitness.activityService.controller;

import com.fitness.activityService.dto.ActivityRequest;
import com.fitness.activityService.dto.ActivityResponse;
import com.fitness.activityService.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;
    //get all useractivities
    @GetMapping
    public ResponseEntity<List<ActivityResponse>> getAllActivities(@RequestHeader ("X-User-ID") String userId){
    return ResponseEntity.ok(activityService.getActivitybyUserId(userId));
    }


    //get activity by activityid
    @GetMapping("/{activityId}")
    public ResponseEntity<ActivityResponse> getActivityById(@PathVariable("activityId") String activityId){
        return ResponseEntity.ok(activityService.getActivityByActivityId(activityId));
    }

    //trackactivity
    @PostMapping
    public ResponseEntity<ActivityResponse> registeractivity(@RequestBody ActivityRequest request,@RequestHeader("X-User-ID") String userId) {
        System.out.println("i was called");
            request.setUserId(userId);
        return ResponseEntity.ok(activityService.trackActivity(request));
    }



}
