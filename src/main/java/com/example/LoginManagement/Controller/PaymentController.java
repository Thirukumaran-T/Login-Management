package com.example.LoginManagement.Controller;


import com.example.LoginManagement.Entity.*;
import com.example.LoginManagement.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // ===== Process Payment =====
    @PostMapping("/process")
    public Payment processPayment(
            @RequestParam Long bookingId,
            @RequestParam double amount,
            @RequestParam String method,
            @RequestParam String gateway
    ) {
        return paymentService.processPayment(bookingId, amount, method, gateway);
    }

    // ===== Get Invoice =====
    @GetMapping("/{paymentId}/invoice")
    public Invoice getInvoice(@PathVariable Long paymentId) {
        return paymentService.getInvoiceByPayment(paymentId);
    }

    // ===== Process Refund =====
    @PostMapping("/{paymentId}/refund")
    public Refund processRefund(
            @PathVariable Long paymentId,
            @RequestParam String reason
    ) {
        return paymentService.processRefund(paymentId, reason);
    }
}
