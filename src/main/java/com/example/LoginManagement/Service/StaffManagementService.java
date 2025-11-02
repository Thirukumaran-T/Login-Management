package com.example.LoginManagement.Service;

import com.example.LoginManagement.Entity.*;
import com.example.LoginManagement.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StaffManagementService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StaffScheduleRepository staffScheduleRepository;

    @Autowired
    private StaffLeaveRepository staffLeaveRepository;

    @Autowired
    private StaffPerformanceRepository staffPerformanceRepository;

    // ===== Schedule Management =====
    public StaffSchedule addOrUpdateSchedule(StaffSchedule schedule) {
        return staffScheduleRepository.save(schedule);
    }

    public List<StaffSchedule> getScheduleByStaff(Long staffId) {
        User staff = userRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found"));
        return staffScheduleRepository.findByStaff(staff);
    }

    // ===== Leave Management =====
    public StaffLeave applyLeave(Long staffId, LocalDate date, String reason) {
        User staff = userRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        if (staffLeaveRepository.existsByStaffAndLeaveDate(staff, date)) {
            throw new RuntimeException("Leave already applied for this date");
        }

        StaffLeave leave = new StaffLeave();
        leave.setStaff(staff);
        leave.setLeaveDate(date);
        leave.setReason(reason);
        return staffLeaveRepository.save(leave);
    }

    public List<StaffLeave> getLeaves(Long staffId) {
        User staff = userRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found"));
        return staffLeaveRepository.findByStaff(staff);
    }

    // ===== Performance Tracking =====
    public void incrementBookingCount(Long staffId) {
        User staff = userRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        StaffPerformance performance = staffPerformanceRepository.findByStaff(staff)
                .orElseGet(() -> {
                    StaffPerformance sp = new StaffPerformance();
                    sp.setStaff(staff);
                    return staffPerformanceRepository.save(sp);
                });

        performance.setTotalBookings(performance.getTotalBookings() + 1);
        staffPerformanceRepository.save(performance);
    }

    public StaffPerformance getPerformance(Long staffId) {
        User staff = userRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found"));
        return staffPerformanceRepository.findByStaff(staff)
                .orElse(new StaffPerformance(null, staff, 0, 0));
    }
}
