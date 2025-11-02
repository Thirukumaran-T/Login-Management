package com.example.LoginManagement.Service;

import com.example.LoginManagement.Entity.Booking;
import com.example.LoginManagement.Entity.User;
import com.example.LoginManagement.Repository.BookingRepository;
import com.example.LoginManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    // Create booking
    public Booking createBooking(Long userId, Booking booking) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean alreadyBooked = bookingRepository.existsByBookingDateAndBookingTimeAndUser(
                booking.getBookingDate(),
                booking.getBookingTime(),
                user
        );

        if (alreadyBooked) {
            throw new RuntimeException("Slot already booked for this time!");
        }

        booking.setUser(user);
        booking.setStatus("CONFIRMED");
        return bookingRepository.save(booking);
    }

    // Get bookings for a user
    public List<Booking> getBookingsForUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return bookingRepository.findByUser(user);
    }

    // Cancel booking
    public Booking cancelBooking(Long bookingId) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        if (booking.isEmpty()) throw new RuntimeException("Booking not found");

        Booking b = booking.get();
        b.setStatus("CANCELLED");
        return bookingRepository.save(b);
    }

    // Reschedule booking
    public Booking rescheduleBooking(Long bookingId, LocalDate newDate, String newTime) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setBookingDate(newDate);
        booking.setBookingTime(newTime);
        booking.setStatus("RESCHEDULED");
        return bookingRepository.save(booking);
    }

    // Get all bookings (admin/staff)
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
