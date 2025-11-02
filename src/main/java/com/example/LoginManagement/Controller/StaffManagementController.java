package com.example.LoginManagement.Controller;

import com.example.LoginManagement.Entity.*;
import com.example.LoginManagement.Service.StaffManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/staff")
@CrossOrigin(origins = "*")
public class StaffManagementController {

    @Autowired
    private StaffManagementService staffManagementService;

    // ===== Schedule =====
    @PostMapping("/schedule")
    public StaffSchedule addOrUpdateSchedule(@RequestBody StaffSchedule schedule) {
        return staffManagementService.addOrUpdateSchedule(schedule);
    }

    @GetMapping("/{staffId}/schedule")
    public List<StaffSchedule> getSchedule(@PathVariable Long staffId) {
        return staffManagementService.getScheduleByStaff(staffId);
    }

    // ===== Leave =====
    @PostMapping("/{staffId}/leave")
    public StaffLeave applyLeave(@PathVariable Long staffId, @RequestParam String date, @RequestParam String reason) {
        return staffManagementService.applyLeave(staffId, LocalDate.parse(date), reason);
    }

    @GetMapping("/{staffId}/leaves")
    public List<StaffLeave> getLeaves(@PathVariable Long staffId) {
        return staffManagementService.getLeaves(staffId);
    }

    // ===== Performance =====
    @GetMapping("/{staffId}/performance")
    public StaffPerformance getPerformance(@PathVariable Long staffId) {
        return staffManagementService.getPerformance(staffId);
    }
}
