package com.SGlocationappartements.demo.repository;

import com.SGlocationappartements.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
