package com.example.LoginManagement.Repository;

import com.example.LoginManagement.Entity.AuditLog;
import com.example.LoginManagement.Entity.ApprovalRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByUserId(Long userId);
}