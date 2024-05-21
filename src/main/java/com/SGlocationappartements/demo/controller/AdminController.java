package com.SGlocationappartements.demo.controller;

import com.SGlocationappartements.demo.entity.Admin;
import com.SGlocationappartements.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static class PasswordUpdateRequest {
        private String username;
        private String currentPassword;
        private String newPassword;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getCurrentPassword() {
            return currentPassword;
        }

        public void setCurrentPassword(String currentPassword) {
            this.currentPassword = currentPassword;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody PasswordUpdateRequest passwordUpdateRequest) {
        try {
            Admin existingAdmin = adminService.findByName(passwordUpdateRequest.getUsername())
                    .orElseThrow(() -> new RuntimeException("Admin not found"));

            if (!passwordEncoder.matches(passwordUpdateRequest.getCurrentPassword(), existingAdmin.getPasswd())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Current password is incorrect");
            }

            existingAdmin.setPasswd(passwordEncoder.encode(passwordUpdateRequest.getNewPassword()));
            adminService.save(existingAdmin);

            return ResponseEntity.ok("Password updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update password");
        }
    }
}
