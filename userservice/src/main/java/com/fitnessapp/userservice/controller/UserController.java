package com.fitnessapp.userservice.controller;

import com.fitnessapp.userservice.dto.RegisterRequest;
import com.fitnessapp.userservice.dto.UserResponse;
import com.fitnessapp.userservice.entity.User;
import com.fitnessapp.userservice.repository.UserRepo;
import com.fitnessapp.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {



    private UserService userService;

    //get by userid
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserProfileById(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUserProfile(userId));

    }



    //register
    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(userService.registerUser(registerRequest));
    }

    //validate using userid
    @GetMapping("/{userId}/validate")
    public ResponseEntity<Boolean> validateUser(@PathVariable String userId) {
        return ResponseEntity.ok(userService.existById(userId));

    }

    @GetMapping("/health-check")
    public ResponseEntity healthCheck() {
        System.out.println("user service ok");
        return ResponseEntity.ok("health check ok");
    }
}
