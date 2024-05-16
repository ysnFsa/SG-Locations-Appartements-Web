/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.SGlocationappartements.rent.controller;

/**
 *
 * @author yassin
 */


import com.SGlocationappartements.rent.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<String> checkLogin(@RequestParam String username, @RequestParam String password) {
        if (adminService.checkLogin(username, password)) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Login failed");
        }
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestParam String username, @RequestParam String newPassword) {
        if (adminService.updatePassword(username, newPassword)) {
            return ResponseEntity.ok("Password updated successfully");
        } else {
            return ResponseEntity.status(400).body("Failed to update password");
        }
    }
}
