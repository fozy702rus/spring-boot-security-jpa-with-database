package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasRole('USER')")
public class UserRestController {

    private final UserService userService;

    @Autowired   
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/current")
    public User userPage(Principal principal) {

        String username = principal.getName();
        return userService.getUserByUsername(username);
    }
}