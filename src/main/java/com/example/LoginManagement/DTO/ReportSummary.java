package com.example.LoginManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

public class ReportSummary {
    private long totalBookings;
    private BigDecimal totalRevenue;
    private long completedBookings;
    private long cancelledBookings;
    private String topStaffName;
    private long topStaffBookings;

    // No-arg constructor
    public ReportSummary() {}

    // All-args constructor
    public ReportSummary(long totalBookings, BigDecimal totalRevenue,
                         long completedBookings, long cancelledBookings,
                         String topStaffName, long topStaffBookings) {
        this.totalBookings = totalBookings;
        this.totalRevenue = totalRevenue;
        this.completedBookings = completedBookings;
        this.cancelledBookings = cancelledBookings;
        this.topStaffName = topStaffName;
        this.topStaffBookings = topStaffBookings;
    }

    // Getters & Setters
    public long getTotalBookings() { return totalBookings; }
    public void setTotalBookings(long totalBookings) { this.totalBookings = totalBookings; }

    public BigDecimal getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(BigDecimal totalRevenue) { this.totalRevenue = totalRevenue; }

    public long getCompletedBookings() { return completedBookings; }
    public void setCompletedBookings(long completedBookings) { this.completedBookings = completedBookings; }

    public long getCancelledBookings() { return cancelledBookings; }
    public void setCancelledBookings(long cancelledBookings) { this.cancelledBookings = cancelledBookings; }

    public String getTopStaffName() { return topStaffName; }
    public void setTopStaffName(String topStaffName) { this.topStaffName = topStaffName; }

    public long getTopStaffBookings() { return topStaffBookings; }
    public void setTopStaffBookings(long topStaffBookings) { this.topStaffBookings = topStaffBookings; }
}
