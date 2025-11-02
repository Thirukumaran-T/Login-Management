package com.example.LoginManagement.Repository;

import com.example.LoginManagement.Entity.Invoice;
import com.example.LoginManagement.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Optional<Invoice> findByPayment(Payment payment);
}