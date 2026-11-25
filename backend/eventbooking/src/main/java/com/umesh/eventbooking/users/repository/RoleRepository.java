package com.umesh.eventbooking.users.repository;

import com.umesh.eventbooking.users.entity.Role;
import com.umesh.eventbooking.users.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleName roleName);
}
