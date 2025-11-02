package com.example.LoginManagement.Repository;

import com.example.LoginManagement.Entity.Booking;
import com.example.LoginManagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUser(User user);

    List<Booking> findByBookingDate(LocalDate date);

    boolean existsByBookingDateAndBookingTimeAndUser(LocalDate date, String time, User user);
}
