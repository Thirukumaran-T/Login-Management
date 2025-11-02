package com.example.LoginManagement.Repository;

import com.example.LoginManagement.Entity.Refund;
import com.example.LoginManagement.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefundRepository extends JpaRepository<Refund, Long> {
    Refund findByPayment(Payment payment);
}