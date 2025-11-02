package com.example.LoginManagement.Service;

import com.example.LoginManagement.Entity.AuditLog;
import com.example.LoginManagement.Entity.ApprovalRequest;
import com.example.LoginManagement.Repository.AuditLogRepository;
import com.example.LoginManagement.Repository.ApprovalRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SecurityService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Autowired
    private ApprovalRequestRepository approvalRequestRepository;

    // 🧾 Log any user action
    public void logAction(Long userId, String action, String details, String ipAddress) {
        AuditLog log = new AuditLog();
        log.setUserId(userId);
        log.setAction(action);
        log.setDetails(details);
        log.setIpAddress(ipAddress);
        log.setTimestamp(LocalDateTime.now());
        auditLogRepository.save(log);
    }

    // 🔐 Create approval request
    public ApprovalRequest requestApproval(Long userId, String actionType, Long targetId) {
        ApprovalRequest req = new ApprovalRequest();
        req.setRequestedBy(userId);
        req.setActionType(actionType);
        req.setTargetId(targetId);
        return approvalRequestRepository.save(req);
    }

    // ✅ Approve or reject
    public ApprovalRequest approveRequest(Long id, Long adminId, boolean approve) {
        ApprovalRequest req = approvalRequestRepository.findById(id).orElseThrow();
        req.setStatus(approve ? "APPROVED" : "REJECTED");
        req.setApprovedBy(adminId);
        req.setApprovedAt(LocalDateTime.now());
        return approvalRequestRepository.save(req);
    }

    public List<ApprovalRequest> getPendingApprovals() {
        return approvalRequestRepository.findByStatus("PENDING");
    }
}