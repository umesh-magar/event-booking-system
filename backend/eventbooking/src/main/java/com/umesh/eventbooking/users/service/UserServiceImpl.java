package com.umesh.eventbooking.users.service;

import com.umesh.eventbooking.users.dto.UserRegistrationRequest;
import com.umesh.eventbooking.users.dto.UserResponse;
import com.umesh.eventbooking.users.entity.Role;
import com.umesh.eventbooking.users.entity.RoleName;
import com.umesh.eventbooking.users.entity.User;
import com.umesh.eventbooking.users.mapper.UserMapper;
import com.umesh.eventbooking.users.repository.RoleRepository;
import com.umesh.eventbooking.users.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

public class UserServiceImpl {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder,UserMapper userMapper){
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
        this.passwordEncoder=passwordEncoder;
        this.userMapper=userMapper;
    }
    public UserResponse register(UserRegistrationRequest request){
        String email=request.email().trim().toLowerCase();
        if(userRepository.existsByEmail(email)){
            throw new IllegalArgumentException("email already exists");
        }
        String encodedPassword =passwordEncoder.encode(request.password());
        Role customerRole=roleRepository.findByName(RoleName.CUSTOMER).orElseThrow(()->new IllegalStateException("CUSTOMER role not found"));
        User user=userMapper.toEntity(request,encodedPassword);
        user.addRole(customerRole);
        User savedUser=userRepository.save(user);
        return  userMapper.toResponse(savedUser);
    }
}
