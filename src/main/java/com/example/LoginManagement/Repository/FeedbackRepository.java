package com.example.LoginManagement.Repository;

import com.example.LoginManagement.Entity.Feedback;
import com.example.LoginManagement.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByCustomer(Customer customer);
}
