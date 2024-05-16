/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.SGlocationappartements.rent.controller;

/**
 *
 * @author yassin
 */




import com.SGlocationappartements.rent.entity.Appartement;
import com.SGlocationappartements.rent.entity.Photos;
import com.SGlocationappartements.rent.service.AppartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/appartements")
public class AppartementController {

    @Autowired
    private AppartementService appartementService;

    @GetMapping
    public String getAllAppartements(Model model) {
        List<Appartement> appartements = appartementService.getAllAppartements();
        model.addAttribute("appartements", appartements);
        return "appartements";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appartement> getAppartementById(@PathVariable Long id) {
        return appartementService.getAppartementById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Appartement addAppartement(@RequestBody Appartement appartement) {
        return appartementService.addAppartement(appartement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appartement> updateAppartement(@PathVariable Long id, @RequestBody Appartement appartement) {
        if (appartementService.updateAppartement(appartement)) {
            return ResponseEntity.ok(appartement);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppartement(@PathVariable Long id) {
        if (appartementService.deleteAppartement(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/photos")
    public Photos addPhoto(@PathVariable Long id, @RequestBody String path) {
        return appartementService.addPhoto(id, path);
    }

    @GetMapping("/{id}/photos")
    public List<Photos> getPhotosByAppartementId(@PathVariable Long id) {
        return appartementService.getPhotosByAppartementId(id);
    }
}
