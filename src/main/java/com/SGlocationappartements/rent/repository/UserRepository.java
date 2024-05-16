package com.SGlocationappartements.rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.SGlocationappartements.rent.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
