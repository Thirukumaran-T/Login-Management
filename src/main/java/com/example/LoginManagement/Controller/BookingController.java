package com.example.LoginManagement.Controller;

import com.example.LoginManagement.Entity.Booking;
import com.example.LoginManagement.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // Create booking
    @PostMapping("/create/{userId}")
    public Booking createBooking(@PathVariable Long userId, @RequestBody Booking booking) {
        return bookingService.createBooking(userId, booking);
    }

    // Get user bookings
    @GetMapping("/user/{userId}")
    public List<Booking> getUserBookings(@PathVariable Long userId) {
        return bookingService.getBookingsForUser(userId);
    }

    // Cancel booking
    @PutMapping("/cancel/{bookingId}")
    public Booking cancelBooking(@PathVariable Long bookingId) {
        return bookingService.cancelBooking(bookingId);
    }

    // Reschedule booking
    @PutMapping("/reschedule/{bookingId}")
    public Booking rescheduleBooking(@PathVariable Long bookingId, @RequestBody Map<String, String> payload) {
        LocalDate newDate = LocalDate.parse(payload.get("newDate"));
        String newTime = payload.get("newTime");
        return bookingService.rescheduleBooking(bookingId, newDate, newTime);
    }

    // Admin: Get all bookings
    @GetMapping("/all")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }
}
