/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.SGlocationappartements.rent.repository;

/**
 *
 * @author yassin
 */


import com.SGlocationappartements.rent.entity.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ContratRepository extends JpaRepository<Contrat, Long> {
    List<Contrat> findByIdAppartementAndDateDebutLessThanEqualAndDateFinGreaterThanEqual(int idAppartement, LocalDate from, LocalDate to);
    List<Contrat> findByDateDebutBefore(LocalDate date);
    List<Contrat> findByDateFinAfter(LocalDate date);
}
