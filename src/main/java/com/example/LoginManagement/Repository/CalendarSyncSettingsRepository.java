package com.example.LoginManagement.Repository;

import com.example.LoginManagement.Entity.CalendarSyncSettings;
import com.example.LoginManagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CalendarSyncSettingsRepository extends JpaRepository<CalendarSyncSettings, Long> {
    Optional<CalendarSyncSettings> findByStaff(User staff);
}
