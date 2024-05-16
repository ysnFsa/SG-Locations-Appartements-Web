/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.SGlocationappartements.rent.service;

/**
 *
 * @author yassin
 */


import com.SGlocationappartements.rent.entity.Admin;
import com.SGlocationappartements.rent.repository.AdminRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @PostConstruct
    public void initializeAdminProfile() {
        if (adminRepository.count() == 0) {
            Admin admin = new Admin();
            admin.setName("admin");
            admin.setPasswd("admin");
            adminRepository.save(admin);
        }
    }

    public boolean checkLogin(String username, String password) {
        Admin admin = adminRepository.findByName(username);
        return admin != null && admin.getPasswd().equals(password);
    }

    public boolean updatePassword(String username, String newPassword) {
        Admin admin = adminRepository.findByName(username);
        if (admin != null) {
            admin.setPasswd(newPassword);
            adminRepository.save(admin);
            return true;
        }
        return false;
    }
}
