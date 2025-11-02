package com.example.LoginManagement.Service;

import com.example.LoginManagement.Entity.*;
import com.example.LoginManagement.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CalendarService {

    @Autowired
    private CalendarSyncSettingsRepository calendarSyncSettingsRepository;

    @Autowired
    private ExternalEventRepository externalEventRepository;

    @Autowired
    private UserRepository userRepository;

    // ======= SYNC SETTINGS =======
    public CalendarSyncSettings saveOrUpdateSettings(CalendarSyncSettings settings) {
        return calendarSyncSettingsRepository.save(settings);
    }

    public CalendarSyncSettings getSettingsByStaff(Long staffId) {
        User staff = userRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found"));
        return calendarSyncSettingsRepository.findByStaff(staff)
                .orElse(null);
    }

    // ======= EXTERNAL EVENTS =======
    public ExternalEvent saveExternalEvent(ExternalEvent event) {
        return externalEventRepository.save(event);
    }

    public List<ExternalEvent> getEvents(Long staffId, LocalDateTime start, LocalDateTime end) {
        User staff = userRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found"));
        return externalEventRepository.findByStaffAndStartTimeBetween(staff, start, end);
    }

    // ======= MOCK SYNC (placeholder for Google API) =======
    public String syncFromGoogle(Long staffId) {
        // Here you’d use Google API Client (Calendar API) to fetch events.
        // For now, simulate sync:
        return "Synced Google Calendar for staff ID " + staffId;
    }

    public String syncToGoogle(Long staffId) {
        // This would push local bookings to Google Calendar
        return "Pushed local events to Google Calendar for staff ID " + staffId;
    }
}
