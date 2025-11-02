package com.example.LoginManagement.Service;

import com.example.LoginManagement.DTO.ReportSummary;
import com.example.LoginManagement.Repository.AnalyticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnalyticsService {

    @Autowired
    private AnalyticsRepository analyticsRepository;

    public ReportSummary getDashboardSummary() {
        long totalBookings = analyticsRepository.countTotalBookings();
        BigDecimal totalRevenue = analyticsRepository.getTotalRevenue();
        long completed = analyticsRepository.countCompletedBookings();
        long cancelled = analyticsRepository.countCancelledBookings();

        List<Object[]> topStaffList = analyticsRepository.findTopStaff();
        String topStaffName = topStaffList.isEmpty() ? "N/A" : (String) topStaffList.get(0)[0];
        long topStaffBookings = topStaffList.isEmpty() ? 0 : (long) topStaffList.get(0)[1];

        return new ReportSummary(
                totalBookings,
                totalRevenue != null ? totalRevenue : BigDecimal.ZERO,
                completed,
                cancelled,
                topStaffName,
                topStaffBookings
        );
    }

    public List<Map<String, Object>> getDailyRevenueReport() {
        List<Object[]> results = analyticsRepository.getDailyRevenue();
        return results.stream()
                .map(r -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("date", r[0]);
                    map.put("revenue", r[1]);
                    return map;
                })
                .toList();
    }
}
