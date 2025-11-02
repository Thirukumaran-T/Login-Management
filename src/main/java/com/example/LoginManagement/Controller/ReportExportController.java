package com.example.LoginManagement.Controller;

import com.example.LoginManagement.Service.AnalyticsService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "*")
public class ReportExportController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/export/excel")
    public ResponseEntity<byte[]> exportRevenueExcel() throws Exception {
        List<Map<String, Object>> data = analyticsService.getDailyRevenueReport();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Daily Revenue");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Date");
        header.createCell(1).setCellValue("Revenue");

        int rowNum = 1;
        for (Map<String, Object> row : data) {
            Row r = sheet.createRow(rowNum++);
            r.createCell(0).setCellValue(row.get("date").toString());
            r.createCell(1).setCellValue(Double.parseDouble(row.get("revenue").toString()));
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=daily_revenue.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(out.toByteArray());
    }
}
