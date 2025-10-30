package com.example.LoginManagement.Controller;


import com.example.LoginManagement.Entity.User;
import com.example.LoginManagement.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        return authService.signup(user);
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        return authService.login(email, password);
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email, @RequestParam String newPassword) {
        return authService.forgotPassword(email, newPassword);
    }

    // ✅ New API: Role Selection
    @PostMapping("/select-role")
    public String selectRole(@RequestParam String email, @RequestParam String role) {
        return authService.updateUserRole(email, role);
    }
}
