package com.example.LoginManagement.Repository;

import com.example.LoginManagement.Entity.StaffLeave;
import com.example.LoginManagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StaffLeaveRepository extends JpaRepository<StaffLeave, Long> {
    List<StaffLeave> findByStaff(User staff);
    boolean existsByStaffAndLeaveDate(User staff, LocalDate date);
}
