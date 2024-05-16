package com.SGlocationappartements.rent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import com.SGlocationappartements.rent.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String getAllUserIds(Model model) {
        List<Long> userIds = userService.getAllUserIds();
        model.addAttribute("userIds", userIds);
        return "users"; // This corresponds to src/main/resources/templates/users.html
    }
}
