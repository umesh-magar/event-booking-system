package com.umesh.eventbooking.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegistrationRequest (
        @NotBlank(message = "full name is required")
        @Size(max=100,message = "Full name cannot exceed 100 character")
        String fullName,
        @NotBlank(message = "password is required")
        @Size(min=8,max = 72, message = "password must contain between 8 and 72 character")
        String password,
        @NotBlank(message = "email us required")
        @Email(message = "please provide the valid email")
        @Size(max = 150)
        String email,
        @NotBlank(message = "phone number is required")
        @Size(max=20)
        String phoneNumber
)
{ }
