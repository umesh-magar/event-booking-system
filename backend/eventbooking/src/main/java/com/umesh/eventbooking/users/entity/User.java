package com.umesh.eventbooking.users.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @Column(nullable = false,length = 20)
    private String username;
    @Column(name = "password_hash", nullable = false,length = 20)
    private String passwordHash;
    @Column(nullable = false,length = 20)
    private String email;
    @Column(name="phone_number",length = 20)
    private String phoneNumber;
    @Column(name="enabled",nullable = false)
    private Boolean enabled=true;
    private RoleName role;
    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "updated_at")
    private Instant updatedAt;
    @PrePersist
     void onCreated(){
        this.createdAt=Instant.now();
    }
    @PreUpdate
    void onUpdated(){
        this.updatedAt=Instant.now();
    }
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="user_roles",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id")
    )
    private Set<Role> roles=new HashSet<>();
    @Builder
    public User(String username, String passwordHash,String email, String phoneNumber){
        this.username=username;
        this.passwordHash=passwordHash;
        this.email=email;
        this.phoneNumber=phoneNumber;
    }
    public void addRole(Role role){
        this.roles.add(role);
    }
}
