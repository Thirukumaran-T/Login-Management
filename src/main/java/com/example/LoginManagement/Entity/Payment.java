package com.example.LoginManagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    private double amount;
    private String paymentMethod;  // CARD / WALLET / PAYPAL / STRIPE / RAZORPAY
    private String paymentStatus;  // PENDING / SUCCESS / FAILED / REFUNDED
    private String transactionId;
    private String gatewayName;

    private LocalDateTime createdAt = LocalDateTime.now();
}