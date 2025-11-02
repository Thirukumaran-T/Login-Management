package com.example.LoginManagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link booking to the customer
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String serviceName;
    private LocalDate bookingDate;
    private String bookingTime;

    private String status = "PENDING"; // PENDING / CONFIRMED / CANCELLED
    private String paymentStatus = "UNPAID"; // UNPAID / PAID
    private LocalDateTime createdAt = LocalDateTime.now();
}
