package com.example.LoginManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
@Repository
public interface AnalyticsRepository extends JpaRepository<com.example.LoginManagement.Entity.Booking, Long> {

    @Query("SELECT COUNT(b) FROM Booking b")
    long countTotalBookings();

    @Query("SELECT SUM(b.totalAmount) FROM Booking b WHERE b.status = 'COMPLETED'")
    BigDecimal getTotalRevenue();

    @Query("SELECT COUNT(b) FROM Booking b WHERE b.status = 'COMPLETED'")
    long countCompletedBookings();

    @Query("SELECT COUNT(b) FROM Booking b WHERE b.status = 'CANCELLED'")
    long countCancelledBookings();

    // Top staff by number of bookings
    @Query("SELECT b.staff.name, COUNT(b) as count FROM Booking b " +
            "GROUP BY b.staff.name ORDER BY count DESC LIMIT 1")
    List<Object[]> findTopStaff();

    // Daily revenue (for charts)
    @Query("SELECT DATE(b.bookingDate), SUM(b.totalAmount) FROM Booking b WHERE b.status='COMPLETED' GROUP BY DATE(b.bookingDate)")
    List<Object[]> getDailyRevenue();
}
