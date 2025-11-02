package com.example.LoginManagement.Repository;

import com.example.LoginManagement.Entity.ExternalEvent;
import com.example.LoginManagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExternalEventRepository extends JpaRepository<ExternalEvent, Long> {
    List<ExternalEvent> findByStaffAndStartTimeBetween(User staff, LocalDateTime start, LocalDateTime end);
}
