package com.fitnessapp.userservice.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    private String firstName;
    private String lastName;


    @NotBlank(message = "Email cant be blank")
    @Email(message = "enter valid email")
    private String email;

    @NotBlank(message = "password cant be blank")
    @Size(min = 8,message = "password should be min 6 digit")
    private String password;
}
