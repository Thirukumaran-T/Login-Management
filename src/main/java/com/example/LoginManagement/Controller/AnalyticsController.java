package com.example.LoginManagement.Controller;

import com.example.LoginManagement.DTO.ReportSummary;
import com.example.LoginManagement.Service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin(origins = "*")
public class AnalyticsController {

//    @Autowired
//    private AnalyticsService analyticsService;
//
//    // Dashboard Summary API
//    @GetMapping("/summary")
//    public ReportSummary getSummary() {
//        return analyticsService.getDashboardSummary();
//    }
//
//    // Daily Revenue Chart Data
//    @GetMapping("/revenue/daily")
//    public List<Map<String, Object>> getDailyRevenue() {
//        return analyticsService.getDailyRevenueReport();
//    }
}
