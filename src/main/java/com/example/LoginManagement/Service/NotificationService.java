package com.example.LoginManagement.Service;

import com.example.LoginManagement.Entity.*;
import com.example.LoginManagement.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {

    @Autowired
    private NotificationTemplateRepository templateRepo;

    @Autowired
    private NotificationLogRepository logRepo;

    @Autowired
    private NotificationSettingRepository settingRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BookingRepository bookingRepo;

    // Send notification for event (booking confirmed, reminder, etc.)
    public String sendNotification(Long userId, Long bookingId, String triggerEvent) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        NotificationTemplate template = templateRepo.findByTriggerEvent(triggerEvent)
                .orElseThrow(() -> new RuntimeException("Template not found for " + triggerEvent));

        NotificationSetting setting = settingRepo.findByUser(user)
                .orElse(new NotificationSetting(null, user, true, false, 24));

        // Create the message dynamically
        String personalizedMessage = template.getMessage()
                .replace("{name}", user.getEmail())
                .replace("{date}", booking.getBookingDate().toString())
                .replace("{time}", booking.getBookingTime());

        // Simulated sending logic (mock)
        if (setting.isEmailEnabled()) {
            System.out.println("📧 Sending Email: " + personalizedMessage);
            saveLog(user, booking, "EMAIL", "SENT", personalizedMessage);
        }

        if (setting.isSmsEnabled()) {
            System.out.println("📱 Sending SMS: " + personalizedMessage);
            saveLog(user, booking, "SMS", "SENT", personalizedMessage);
        }

        return "Notification sent successfully for event: " + triggerEvent;
    }

    private void saveLog(User user, Booking booking, String channel, String status, String message) {
        NotificationLog log = new NotificationLog();
        log.setUser(user);
        log.setBooking(booking);
        log.setChannel(channel);
        log.setStatus(status);
        log.setMessage(message);
        log.setSentAt(LocalDateTime.now());
        logRepo.save(log);
    }
}
