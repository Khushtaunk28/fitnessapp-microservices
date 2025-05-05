package com.fitnessapp.userservice.service;
import com.fitnessapp.userservice.dto.RegisterRequest;
import com.fitnessapp.userservice.dto.UserResponse;
import com.fitnessapp.userservice.entity.User;
import com.fitnessapp.userservice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;



    public UserResponse getUserProfile(String userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not Found by ID"));

        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setPassword(user.getPassword());
        userResponse.setCreatedAt(user.getCreated());
        userResponse.setUpdatedAt(user.getModified());
        return userResponse;
    }

    public UserResponse registerUser(RegisterRequest registerRequest) {
        if(userRepo.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Email Already Exists");
        }
        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        User savedUser = userRepo.save(user);

        UserResponse userResponse = new UserResponse();
        userResponse.setId(savedUser.getId());
        userResponse.setEmail(savedUser.getEmail());
        userResponse.setFirstName(savedUser.getFirstName());
        userResponse.setLastName(savedUser.getLastName());
        userResponse.setPassword(savedUser.getPassword());
        userResponse.setCreatedAt(savedUser.getCreated());
        userResponse.setUpdatedAt(savedUser.getModified());
        return userResponse;
    }

    public Boolean existById(String userId) {
        return userRepo.existsById(userId);
    }
}
