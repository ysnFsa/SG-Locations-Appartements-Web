package com.SGlocationappartements.demo.controller;


import com.SGlocationappartements.demo.entity.Appartement;
import com.SGlocationappartements.demo.entity.AppartementDTO;
import com.SGlocationappartements.demo.entity.Quartier;
import com.SGlocationappartements.demo.entity.Ville;
import com.SGlocationappartements.demo.service.AppartementService;
import com.SGlocationappartements.demo.service.QuartierService;
import com.SGlocationappartements.demo.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/appartements")
public class AppartementController {

    @Autowired
    private AppartementService appartementService;

    @Autowired
    private VilleService villeService;

    @Autowired
    private QuartierService quartierService;

    @GetMapping
    public String getAllAppartements(Model model) {
        List<Appartement> appartements = appartementService.getAllAppartements();
        model.addAttribute("appartements", appartements);
        return "appartements"; // Refers to appartements.html in src/main/resources/templates
    }

    @GetMapping("/{id}")
    public Optional<Appartement> getAppartementById(@PathVariable Long id) {
        return appartementService.getAppartementById(id);
    }

    @GetMapping("/{id}/json")
    @ResponseBody
    public ResponseEntity<AppartementDTO> getAppartementByIdAsJson(@PathVariable Long id) {
        Optional<Appartement> appartement = appartementService.getAppartementById(id);
        return appartement.map(app -> {
            AppartementDTO dto = new AppartementDTO();
            dto.setId(app.getId());
            dto.setType(app.getType());
            dto.setDescription(app.getDescription());
            dto.setSurface(app.getSurface());
            dto.setChambres(app.getChambres());
            dto.setDisponibilite(app.isDisponibilite());
            dto.setMeublee(app.isMeublee());
            dto.setPrix(app.getPrix());
            dto.setVilleName(app.getVille().getName());
            dto.setQuartierName(app.getQuartier().getName());
            dto.setPhoto(app.getPhoto());
            return ResponseEntity.ok(dto);
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/add")
    public String addAppartement(@RequestParam String ville,
                                 @RequestParam String quartier,
                                 @RequestParam float prix,
                                 @RequestParam float surface,
                                 @RequestParam String type,
                                 @RequestParam int chambre,
                                 @RequestParam String description,
                                 @RequestParam boolean furnished,
                                 @RequestParam boolean disponible,
                                 @RequestParam String photo) {
        Ville villeEntity = villeService.getVilleByName(ville)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ville name"));
        Quartier quartierEntity = quartierService.getQuartierByName(quartier)
                .orElseThrow(() -> new IllegalArgumentException("Invalid quartier name"));

        Appartement appartement = new Appartement();
        appartement.setVille(villeEntity);
        appartement.setQuartier(quartierEntity);
        appartement.setPrix(prix);
        appartement.setSurface(surface);
        appartement.setType(type);
        appartement.setChambres(chambre);
        appartement.setDescription(description);
        appartement.setMeublee(furnished);
        appartement.setDisponibilite(disponible);
        appartement.setPhoto(photo);

        appartementService.addAppartement(appartement);
        return "redirect:/appartements";
    }

    @GetMapping("/update")
    public String updateAppartement(@RequestParam Long id,
                                    @RequestParam String ville,
                                    @RequestParam String quartier,
                                    @RequestParam float prix,
                                    @RequestParam float surface,
                                    @RequestParam String type,
                                    @RequestParam int chambre,
                                    @RequestParam String description,
                                    @RequestParam boolean furnished,
                                    @RequestParam boolean disponible,
                                    @RequestParam String photo) {
        Ville villeEntity = villeService.getVilleByName(ville)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ville name"));
        Quartier quartierEntity = quartierService.getQuartierByName(quartier)
                .orElseThrow(() -> new IllegalArgumentException("Invalid quartier name"));

        Appartement appartement = appartementService.getAppartementById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid appartement ID"));
        
        appartement.setVille(villeEntity);
        appartement.setQuartier(quartierEntity);
        appartement.setPrix(prix);
        appartement.setSurface(surface);
        appartement.setType(type);
        appartement.setChambres(chambre);
        appartement.setDescription(description);
        appartement.setMeublee(furnished);
        appartement.setDisponibilite(disponible);
        appartement.setPhoto(photo);

        appartementService.updateAppartement(appartement);
        return "redirect:/appartements";
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteAppartement(@PathVariable Long id) {
        if (appartementService.deleteAppartement(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
