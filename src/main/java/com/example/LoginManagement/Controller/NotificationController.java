package com.example.LoginManagement.Controller;

import com.example.LoginManagement.Entity.NotificationSetting;
import com.example.LoginManagement.Entity.NotificationTemplate;
import com.example.LoginManagement.Repository.NotificationSettingRepository;
import com.example.LoginManagement.Repository.NotificationTemplateRepository;
import com.example.LoginManagement.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationTemplateRepository templateRepo;

    @Autowired
    private NotificationSettingRepository settingRepo;

    // ===== Send Notification (for event) =====
    @PostMapping("/send")
    public String sendNotification(
            @RequestParam Long userId,
            @RequestParam Long bookingId,
            @RequestParam String triggerEvent
    ) {
        return notificationService.sendNotification(userId, bookingId, triggerEvent);
    }

    // ===== CRUD for Templates =====
    @GetMapping("/templates")
    public List<NotificationTemplate> getAllTemplates() {
        return templateRepo.findAll();
    }

    @PostMapping("/templates")
    public NotificationTemplate addTemplate(@RequestBody NotificationTemplate template) {
        return templateRepo.save(template);
    }

    // ===== User Notification Settings =====
//    @GetMapping("/settings/{userId}")
//    public NotificationSetting getSettings(@PathVariable Long userId) {
//        return settingRepo.findByUserId(userId).orElse(null);
//    }

    @PostMapping("/settings")
    public NotificationSetting saveSettings(@RequestBody NotificationSetting setting) {
        return settingRepo.save(setting);
    }
}
