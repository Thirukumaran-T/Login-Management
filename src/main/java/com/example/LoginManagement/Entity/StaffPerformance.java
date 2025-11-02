package com.example.LoginManagement.Entity;

import jakarta.persistence.*;
@Entity
@Table(name = "staff_performance")
public class StaffPerformance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private User staff;

    private int totalBookings = 0;
    private double rating = 0.0;

    // --- Default Constructor ---
    public StaffPerformance() {
    }

    // --- Custom Constructor (matches your usage) ---
    public StaffPerformance(Long id, User staff, int totalBookings, double rating) {
        this.id = id;
        this.staff = staff;
        this.totalBookings = totalBookings;
        this.rating = rating;
    }

    // --- Getters and Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getStaff() {
        return staff;
    }

    public void setStaff(User staff) {
        this.staff = staff;
    }

    public int getTotalBookings() {
        return totalBookings;
    }

    public void setTotalBookings(int totalBookings) {
        this.totalBookings = totalBookings;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
