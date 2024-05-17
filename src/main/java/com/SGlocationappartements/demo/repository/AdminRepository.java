/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.SGlocationappartements.demo.repository;

/**
 *
 * @author yassin
 */
import com.SGlocationappartements.demo.entity.Admin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Admin admin) {
        entityManager.persist(admin);
    }

    public Admin findById(Long id) {
        return entityManager.find(Admin.class, id);
    }

    public Admin findByName(String name) {
        List<Admin> admins = entityManager.createQuery("SELECT a FROM Admin a WHERE a.name = :name", Admin.class)
                                          .setParameter("name", name)
                                          .getResultList();
        return admins.isEmpty() ? null : admins.get(0);
    }

    public long count() {
        return entityManager.createQuery("SELECT COUNT(a) FROM Admin a", Long.class).getSingleResult();
    }

    @Transactional
    public void update(Admin admin) {
        entityManager.merge(admin);
    }
}