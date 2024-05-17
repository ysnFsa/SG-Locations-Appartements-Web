package com.SGlocationappartements.demo.controller;

import com.SGlocationappartements.demo.entity.Appartement;
import com.SGlocationappartements.demo.entity.Quartier;
import com.SGlocationappartements.demo.entity.Ville;
import com.SGlocationappartements.demo.service.AppartementService;
import com.SGlocationappartements.demo.service.QuartierService;
import com.SGlocationappartements.demo.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PutMapping("/{id}")
    public String updateAppartement(@PathVariable Long id, @ModelAttribute Appartement appartement) {
        appartement.setId(id);
        if (appartementService.updateAppartement(appartement)) {
            return "redirect:/appartements";
        } else {
            return "redirect:/appartements";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteAppartement(@PathVariable Long id) {
        if (appartementService.deleteAppartement(id)) {
            return "redirect:/appartements";
        } else {
            return "redirect:/appartements";
        }
    }
}
