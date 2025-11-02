package com.example.LoginManagement.Service;

import com.example.LoginManagement.Entity.*;
import com.example.LoginManagement.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private RefundRepository refundRepository;

    @Autowired
    private BookingRepository bookingRepository;

    // ===== Process Payment =====
    public Payment processPayment(Long bookingId, double amount, String method, String gateway) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(amount);
        payment.setPaymentMethod(method);
        payment.setGatewayName(gateway);
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setPaymentStatus("SUCCESS");

        Payment saved = paymentRepository.save(payment);

        generateInvoice(saved);
        return saved;
    }

    // ===== Generate Invoice =====
    private Invoice generateInvoice(Payment payment) {
        double tax = payment.getAmount() * 0.10;
        double finalAmount = payment.getAmount() + tax;

        Invoice invoice = new Invoice();
        invoice.setPayment(payment);
        invoice.setInvoiceNumber("INV-" + UUID.randomUUID().toString().substring(0, 8));
        invoice.setTotalAmount(payment.getAmount());
        invoice.setTax(tax);
        invoice.setFinalAmount(finalAmount);
        invoice.setPdfUrl("/invoices/" + invoice.getInvoiceNumber() + ".pdf");

        return invoiceRepository.save(invoice);
    }

    // ===== Get Invoice =====
    public Invoice getInvoiceByPayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        return invoiceRepository.findByPayment(payment)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
    }

    // ===== Refund Processing =====
    public Refund processRefund(Long paymentId, String reason) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        Refund refund = new Refund();
        refund.setPayment(payment);
        refund.setRefundAmount(payment.getAmount());
        refund.setReason(reason);
        refund.setRefundStatus("INITIATED");

        payment.setPaymentStatus("REFUNDED");
        paymentRepository.save(payment);

        return refundRepository.save(refund);
    }
}