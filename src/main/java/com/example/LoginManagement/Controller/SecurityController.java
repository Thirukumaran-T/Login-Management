package com.example.LoginManagement.Controller;

import com.example.LoginManagement.Entity.ApprovalRequest;
import com.example.LoginManagement.Entity.AuditLog;
import com.example.LoginManagement.Service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/security")
@CrossOrigin(origins = "*")
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    // 🧾 Log action manually (optional testing)
    @PostMapping("/log")
    public String logAction(@RequestParam Long userId,
                            @RequestParam String action,
                            @RequestParam String details,
                            @RequestParam(required = false) String ip) {
        securityService.logAction(userId, action, details, ip);
        return "Action logged successfully.";
    }

    // 🔐 Request admin approval
    @PostMapping("/approval/request")
    public ApprovalRequest requestApproval(@RequestParam Long userId,
                                           @RequestParam String actionType,
                                           @RequestParam Long targetId) {
        return securityService.requestApproval(userId, actionType, targetId);
    }

    // ✅ Approve / Reject
    @PostMapping("/approval/decision")
    public ApprovalRequest decide(@RequestParam Long requestId,
                                  @RequestParam Long adminId,
                                  @RequestParam boolean approve) {
        return securityService.approveRequest(requestId, adminId, approve);
    }

    // 📋 Pending approvals list
    @GetMapping("/approval/pending")
    public List<ApprovalRequest> getPending() {
        return securityService.getPendingApprovals();
    }
}
