package com.example.LoginManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportSummary {
    private long totalBookings;
    private BigDecimal totalRevenue;
    private long completedBookings;
    private long cancelledBookings;
    private String topStaffName;
    private long topStaffBookings;
}
