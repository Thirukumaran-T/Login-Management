package com.example.LoginManagement.Entity;

import jakarta.persistence.*;
import lombok.*;


import jakarta.persistence.*;

@Entity
@Table(name = "notification_settings")
public class NotificationSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean emailEnabled = true;
    private boolean smsEnabled = false;
    private int reminderBeforeHours = 24;

    // ✅ Default constructor
    public NotificationSetting() {}

    // ✅ Full constructor
    public NotificationSetting(Long id, User user, boolean emailEnabled, boolean smsEnabled, int reminderBeforeHours) {
        this.id = id;
        this.user = user;
        this.emailEnabled = emailEnabled;
        this.smsEnabled = smsEnabled;
        this.reminderBeforeHours = reminderBeforeHours;
    }

    // ✅ Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public boolean isEmailEnabled() { return emailEnabled; }
    public void setEmailEnabled(boolean emailEnabled) { this.emailEnabled = emailEnabled; }

    public boolean isSmsEnabled() { return smsEnabled; }
    public void setSmsEnabled(boolean smsEnabled) { this.smsEnabled = smsEnabled; }

    public int getReminderBeforeHours() { return reminderBeforeHours; }
    public void setReminderBeforeHours(int reminderBeforeHours) { this.reminderBeforeHours = reminderBeforeHours; }
}
