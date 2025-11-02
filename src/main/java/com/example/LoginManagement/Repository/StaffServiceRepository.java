package com.example.LoginManagement.Repository;

import com.example.LoginManagement.Entity.StaffService;
import com.example.LoginManagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffServiceRepository extends JpaRepository<StaffService, Long> {
    List<StaffService> findByStaff(User staff);
}
