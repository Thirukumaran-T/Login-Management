package com.example.LoginManagement.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notification_templates")
public class NotificationTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String subject;
    private String message;
    private String type; // EMAIL / SMS
    private String triggerEvent; // BOOKING_CONFIRMED / REMINDER / CANCELLED
    private LocalDateTime createdAt = LocalDateTime.now();

    // ✅ Default constructor
    public NotificationTemplate() {}

    // ✅ Full constructor
    public NotificationTemplate(Long id, String name, String subject, String message,
                                String type, String triggerEvent, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.message = message;
        this.type = type;
        this.triggerEvent = triggerEvent;
        this.createdAt = createdAt;
    }

    // ✅ Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getTriggerEvent() { return triggerEvent; }
    public void setTriggerEvent(String triggerEvent) { this.triggerEvent = triggerEvent; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
