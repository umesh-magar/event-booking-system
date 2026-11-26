package com.umesh.eventbooking.users.mapper;

import com.umesh.eventbooking.users.dto.UserRegistrationRequest;
import com.umesh.eventbooking.users.dto.UserResponse;
import com.umesh.eventbooking.users.entity.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {
    public User toEntity(UserRegistrationRequest request,String encodedPassword){
        return  User.builder().
                username(request.fullName())
                .email(request.email())
                .passwordHash(encodedPassword)
                .phoneNumber(request.phoneNumber())
                .build();

    }
    public UserResponse toResponse(User user){
        return new UserResponse(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toSet()),
                user.getCreatedAt()
        );
    }
}
