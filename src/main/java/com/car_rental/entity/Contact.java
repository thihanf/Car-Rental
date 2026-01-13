package com.car_rental.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String subject;
    private String message;

    private LocalDateTime contactCreatedAt = LocalDateTime.now();

    public Contact() {}

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public User getUser() {
        return user;
    }
 
    public void setUser(User user) {
        this.user = user;
    }
 
    public String getSubject() {
        return subject;
    }
 
    public void setSubject(String subject) {
        this.subject = subject;
    }
 
    public String getMessage() {
        return message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }
 
    public LocalDateTime getContactCreatedAt() {
        return contactCreatedAt;
    }
 
    public void setContactCreatedAt(LocalDateTime contactCreatedAt) {
        this.contactCreatedAt = contactCreatedAt;
    }
}
