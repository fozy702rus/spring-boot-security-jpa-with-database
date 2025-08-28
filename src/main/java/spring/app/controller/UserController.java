package spring.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.app.model.User;
import spring.app.service.UserService;
import java.security.Principal;

@Controller
@RequestMapping("/profile")
@PreAuthorize("hasRole('USER')")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userPage(Model model, Principal principal) {

        String username = principal.getName();
        User user = userService.getUserByUsername(username);
        model.addAttribute("user", user);

        return "/user/profile";
    }
}