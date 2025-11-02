package com.example.LoginManagement.Repository;

import com.example.LoginManagement.Entity.StaffPerformance;
import com.example.LoginManagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffPerformanceRepository extends JpaRepository<StaffPerformance, Long> {
    Optional<StaffPerformance> findByStaff(User staff);
}
