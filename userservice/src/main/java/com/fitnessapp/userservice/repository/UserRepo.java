package com.fitnessapp.userservice.repository;

import com.fitnessapp.userservice.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {

    boolean existsByEmail( String email);
}
