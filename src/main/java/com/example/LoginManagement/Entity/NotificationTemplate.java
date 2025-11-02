package com.example.LoginManagement.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
