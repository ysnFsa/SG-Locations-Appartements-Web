/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.SGlocationappartements.rent.controller;

/**
 *
 * @author yassin
 */


import com.SGlocationappartements.rent.entity.Contrat;
import com.SGlocationappartements.rent.service.ContratService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contrats")
public class ContratController {

    @Autowired
    private ContratService contratService;

    @PostMapping
    public Contrat createContrat(@RequestBody Contrat contrat) {
        contratService.createContrat(contrat);
        return contrat;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contrat> getContratById(@PathVariable Long id) {
        Optional<Contrat> contratOptional = contratService.getContratById(id);
        return contratOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Contrat> getAllContrats() {
        return contratService.getAllContrats();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContrat(@PathVariable Long id) {
        contratService.deleteContrat(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/end/{id}")
    public ResponseEntity<Void> endContrat(@PathVariable Long id) {
        contratService.endContrat(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/availability")
    public boolean isAppartementAvailableFromTo(@RequestParam int appartementId, @RequestParam String from, @RequestParam String to) {
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);
        return contratService.isAppartementAvailableFromTo(appartementId, fromDate, toDate);
    }

    @GetMapping("/filtered")
    public List<Contrat> getContratsFilteredByMonths(@RequestParam int months) {
        return contratService.getContratsFilteredByMonths(months);
    }

    @GetMapping("/active")
    public List<Contrat> getActiveContrats() {
        return contratService.getActiveContrats();
    }
}
