package com.car_rental.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.car_rental.enums.Role;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String userEmail;
    private String password;
    private String phoneNo;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime userCreatedAt = LocalDateTime.now();

    public User() {}

    public Long getUserId() {
        return userId;
    }
 
    public void setUserId(Long userId) {
        this.userId = userId;
    }
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getUserEmail() {
        return userEmail;
    }
 
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getPhoneNo() {
        return phoneNo;
    }
 
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
 
    public Role getRole() {
        return role;
    }
 
    public void setRole(Role role) {
        this.role = role;
    }
 
    public LocalDateTime getUserCreatedAt() {
        return userCreatedAt;
    }
 
    public void setUserCreatedAt(LocalDateTime userCreatedAt) {
        this.userCreatedAt = userCreatedAt;
    }
}
