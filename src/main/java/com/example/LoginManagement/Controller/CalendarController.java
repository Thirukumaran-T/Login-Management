package com.example.LoginManagement.Controller;

import com.example.LoginManagement.Entity.*;
import com.example.LoginManagement.Service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/calendar")
@CrossOrigin(origins = "*")
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    // ===== Settings =====
    @PostMapping("/settings")
    public CalendarSyncSettings saveSettings(@RequestBody CalendarSyncSettings settings) {
        return calendarService.saveOrUpdateSettings(settings);
    }

    @GetMapping("/{staffId}/settings")
    public CalendarSyncSettings getSettings(@PathVariable Long staffId) {
        return calendarService.getSettingsByStaff(staffId);
    }

    // ===== External Events =====
    @PostMapping("/external")
    public ExternalEvent saveExternalEvent(@RequestBody ExternalEvent event) {
        return calendarService.saveExternalEvent(event);
    }

    @GetMapping("/{staffId}/events")
    public List<ExternalEvent> getEvents(
            @PathVariable Long staffId,
            @RequestParam String start,
            @RequestParam String end
    ) {
        return calendarService.getEvents(staffId, LocalDateTime.parse(start), LocalDateTime.parse(end));
    }

    // ===== Sync Actions =====
    @PostMapping("/{staffId}/sync/google/fetch")
    public String syncFromGoogle(@PathVariable Long staffId) {
        return calendarService.syncFromGoogle(staffId);
    }

    @PostMapping("/{staffId}/sync/google/push")
    public String syncToGoogle(@PathVariable Long staffId) {
        return calendarService.syncToGoogle(staffId);
    }
}
