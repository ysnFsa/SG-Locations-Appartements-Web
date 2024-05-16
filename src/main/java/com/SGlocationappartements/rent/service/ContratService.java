/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.SGlocationappartements.rent.service;

/**
 *
 * @author yassin
 */


import com.SGlocationappartements.rent.entity.Contrat;
import com.SGlocationappartements.rent.repository.ContratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContratService {

    @Autowired
    private ContratRepository contratRepository;

    public void createContrat(Contrat contrat) {
        contratRepository.save(contrat);
    }

    public Optional<Contrat> getContratById(Long id) {
        return contratRepository.findById(id);
    }

    public List<Contrat> getAllContrats() {
        return contratRepository.findAll();
    }

    public void deleteContrat(Long id) {
        contratRepository.deleteById(id);
    }

    public void endContrat(Long id) {
        Optional<Contrat> contratOptional = contratRepository.findById(id);
        if (contratOptional.isPresent()) {
            Contrat contrat = contratOptional.get();
            contrat.setDateFin(LocalDate.now());
            contratRepository.save(contrat);
        }
    }

    public boolean isAppartementAvailableFromTo(int appartementId, LocalDate from, LocalDate to) {
        List<Contrat> overlappingContrats = contratRepository.findByIdAppartementAndDateDebutLessThanEqualAndDateFinGreaterThanEqual(appartementId, to, from);
        return overlappingContrats.isEmpty();
    }

    public List<Contrat> getContratsFilteredByMonths(int months) {
        LocalDate dateDebutFilter = LocalDate.now().minusMonths(months);
        return contratRepository.findByDateDebutBefore(dateDebutFilter);
    }

    public List<Contrat> getActiveContrats() {
        LocalDate today = LocalDate.now();
        return contratRepository.findByDateFinAfter(today);
    }
}
