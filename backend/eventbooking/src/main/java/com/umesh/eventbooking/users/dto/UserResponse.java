package com.umesh.eventbooking.users.dto;

import com.umesh.eventbooking.users.entity.RoleName;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.Set;
@Builder
public record UserResponse(
        Long id,
        String username,
        String email,
        String phoneNumber,
        Set<RoleName> roles,
        Instant createdAt
) {
}
