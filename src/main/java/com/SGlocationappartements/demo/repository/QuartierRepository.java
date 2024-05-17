package com.SGlocationappartements.demo.repository;

import com.SGlocationappartements.demo.entity.Quartier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuartierRepository extends JpaRepository<Quartier, Long> {
    Optional<Quartier> findByName(String name);
}
