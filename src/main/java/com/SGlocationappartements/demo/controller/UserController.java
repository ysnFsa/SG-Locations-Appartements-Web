package com.SGlocationappartements.demo.controller;

import com.SGlocationappartements.demo.entity.User;
import com.SGlocationappartements.demo.entity.Appartement;
import com.SGlocationappartements.demo.entity.Contrat;
import com.SGlocationappartements.demo.service.UserService;
import com.SGlocationappartements.demo.service.AppartementService;
import com.SGlocationappartements.demo.service.ContratService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private AppartementService appartementService;

    @Autowired
    private ContratService contratService;

    @GetMapping
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        List<Appartement> appartements = appartementService.getAllAppartements();
        model.addAttribute("users", users);
        model.addAttribute("appartements", appartements);
        return "clients"; // Refers to clients.html in src/main/resources/templates
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable int id, Model model) {
        var user = userService.getUserById(id);
        user.ifPresent(value -> model.addAttribute("user", value));
        return user.map(value -> "userDetail").orElse("redirect:/clients");
    }

    @PostMapping
    public String addUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/clients";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable int id, @ModelAttribute User user) {
        user.setId(id);
        if (userService.updateUser(user)) {
            return "redirect:/clients";
        } else {
            return "redirect:/clients";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        if (userService.deleteUser(id)) {
            return "redirect:/clients";
        } else {
            return "redirect:/clients";
        }
    }

    @GetMapping("/add")
    @ResponseBody
    public String addUserViaGet(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("phone") String phone,
            @RequestParam("address") String address,
            @RequestParam("email") String email) {

        logParameters(firstName, lastName, phone, address, email);

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setTele(phone);
        user.setAddresse(address);
        user.setEmail(email);

        userService.addUser(user);

        return "User added successfully!";
    }

    @GetMapping("/assign")
    @ResponseBody
    public String assignAppartementToUser(
            @RequestParam("userId") int userId,
            @RequestParam("appartementId") int appartementId,
            @RequestParam("dateDebut") String dateDebut,
            @RequestParam("dateFin") String dateFin) {

        Contrat contrat = new Contrat();
        contrat.setIdClient(userId);
        contrat.setIdAppartement(appartementId);
        contrat.setDateDebut(dateDebut);
        contrat.setDateFin(dateFin);

        contratService.addContrat(contrat);

        return "Appartement assigned to user successfully!";
    }

    private void logParameters(String firstName, String lastName, String phone, String address, String email) {
        System.out.println("Received parameters:");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Phone: " + phone);
        System.out.println("Address: " + address);
        System.out.println("Email: " + email);
    }
}
