package com.example.LoginManagement.Service;

import com.example.LoginManagement.Entity.*;
import com.example.LoginManagement.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    // Add or update customer profile
    public Customer saveCustomer(Long userId, Customer customer) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        customer.setUser(user);
        return customerRepository.save(customer);
    }

    // Get customer profile
    public Customer getCustomerProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return customerRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    // Get all customers (for admin view)
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Save feedback
    public Feedback saveFeedback(Long customerId, Long bookingId, Feedback feedback) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        feedback.setCustomer(customer);
        feedback.setBooking(booking);
        return feedbackRepository.save(feedback);
    }

    // View feedback history
    public List<Feedback> getFeedbacksByCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return feedbackRepository.findByCustomer(customer);
    }
}
