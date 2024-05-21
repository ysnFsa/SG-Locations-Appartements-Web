/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.SGlocationappartements.demo.service;

/**
 *
 * @author yassin
 */

import com.SGlocationappartements.demo.entity.Admin;
import com.SGlocationappartements.demo.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Optional<Admin> findByName(String name) {
        return adminRepository.findByName(name);
    }

    public void save(Admin admin) {
        adminRepository.save(admin);
    }
}
