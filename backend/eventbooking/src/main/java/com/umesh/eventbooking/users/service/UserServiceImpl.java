package com.umesh.eventbooking.users.service;

import com.umesh.eventbooking.users.dto.UserRegistrationRequest;
import com.umesh.eventbooking.users.dto.UserResponse;
import com.umesh.eventbooking.users.entity.Role;
import com.umesh.eventbooking.users.entity.RoleName;
import com.umesh.eventbooking.users.entity.User;
import com.umesh.eventbooking.users.repository.RoleRepository;
import com.umesh.eventbooking.users.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

public class UserServiceImpl {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
        this.passwordEncoder=passwordEncoder;
    }
    public UserResponse register(UserRegistrationRequest request){
        String email=request.email().trim().toLowerCase();
        if(userRepository.existsByEmail(email)){
            throw new IllegalArgumentException("email already exists");
        }
        String encodedPassword =passwordEncoder.encode(request.password());
        Role customerRole=roleRepository.findByName(RoleName.CUSTOMER).orElseThrow(()->new IllegalStateException("CUSTOMER role not found"));
        User user=new User(request.fullName(),email,encodedPassword,request.phoneNumber());
        user.addRole(customerRole);
        User savedUser=userRepository.save(user);
        return  new UserResponse(
                savedUser.getUserId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getPhoneNumber(),
                Set.of(RoleName.CUSTOMER),
                savedUser.getCreatedAt()
        );
    }
}
