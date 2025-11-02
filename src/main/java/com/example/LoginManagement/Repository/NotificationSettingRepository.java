package com.example.LoginManagement.Repository;

import com.example.LoginManagement.Entity.NotificationSetting;
import com.example.LoginManagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationSettingRepository extends JpaRepository<NotificationSetting, Long> {
    Optional<NotificationSetting> findByUser(User user);
}
