package com.SGlocationappartements.demo.repository;

import com.SGlocationappartements.demo.entity.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VilleRepository extends JpaRepository<Ville, Long> {
    Optional<Ville> findByName(String name);
}
