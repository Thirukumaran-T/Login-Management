package com.example.LoginManagement.Repository;

import com.example.LoginManagement.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface AnalyticsRepository extends JpaRepository<Booking, Long> {

    // ✅ Top customers by number of bookings (using user email)
    @Query("SELECT b.user.email, COUNT(b) AS bookingCount " +
            "FROM Booking b " +
            "GROUP BY b.user.email " +
            "ORDER BY bookingCount DESC")
    List<Object[]> findTopCustomers();

    // ✅ Top staff by number of bookings
    // Works only if Booking has a staff field (User staff)
    @Query("SELECT b.staff.email, COUNT(b) AS bookingCount " +
            "FROM Booking b " +
            "GROUP BY b.staff.email " +
            "ORDER BY bookingCount DESC")
    List<Object[]> findTopStaff();

    // ✅ Total bookings
    @Query("SELECT COUNT(b) FROM Booking b")
    long countTotalBookings();

    // ✅ Completed bookings
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.status = 'COMPLETED'")
    long countCompletedBookings();

    // ✅ Cancelled bookings
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.status = 'CANCELLED'")
    long countCancelledBookings();

    // ✅ Total revenue from successful payments
    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM Payment p WHERE p.paymentStatus = 'SUCCESS'")
    BigDecimal getTotalRevenue();

    // ✅ Daily revenue (for charts)
    @Query("SELECT p.createdAt, SUM(p.amount) " +
            "FROM Payment p " +
            "WHERE p.paymentStatus = 'SUCCESS' " +
            "GROUP BY p.createdAt " +
            "ORDER BY p.createdAt ASC")
    List<Object[]> getDailyRevenue();

    // ✅ Monthly revenue (for charts)
    @Query("SELECT FUNCTION('DATE_TRUNC', 'month', p.createdAt), SUM(p.amount) " +
            "FROM Payment p " +
            "WHERE p.paymentStatus = 'SUCCESS' " +
            "GROUP BY FUNCTION('DATE_TRUNC', 'month', p.createdAt) " +
            "ORDER BY FUNCTION('DATE_TRUNC', 'month', p.createdAt) ASC")
    List<Object[]> getMonthlyRevenue();
}

