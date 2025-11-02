package com.example.LoginManagement.Controller;

import com.example.LoginManagement.Entity.Customer;
import com.example.LoginManagement.Entity.Feedback;
import com.example.LoginManagement.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Add or update profile
    @PostMapping("/save/{userId}")
    public Customer saveCustomer(@PathVariable Long userId, @RequestBody Customer customer) {
        return customerService.saveCustomer(userId, customer);
    }

    // Get profile
    @GetMapping("/profile/{userId}")
    public Customer getProfile(@PathVariable Long userId) {
        return customerService.getCustomerProfile(userId);
    }

    // Get all customers (Admin view)
    @GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // Add feedback
    @PostMapping("/{customerId}/feedback/{bookingId}")
    public Feedback saveFeedback(
            @PathVariable Long customerId,
            @PathVariable Long bookingId,
            @RequestBody Feedback feedback) {
        return customerService.saveFeedback(customerId, bookingId, feedback);
    }

    // View feedback history
    @GetMapping("/{customerId}/feedbacks")
    public List<Feedback> getFeedbacks(@PathVariable Long customerId) {
        return customerService.getFeedbacksByCustomer(customerId);
    }
}
