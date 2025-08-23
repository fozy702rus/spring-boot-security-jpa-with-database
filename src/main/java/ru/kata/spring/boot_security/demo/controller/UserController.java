package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String showUsers(Model model) {

        List<User> users = userService.getUsers();
        model.addAttribute("users", users);

        return "users";
    }

    @GetMapping("/new-user")
    @PreAuthorize("hasRole('ADMIN')")
    public String showAddUserForm(Model model) {

        model.addAttribute("user", new User());

        return "add_user";
    }

    @PostMapping("/new-user/save")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveUser(User user) {

        userService.addUser(user);

        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String showUpdateUserForm(@PathVariable int id, Model model) {

        User user = userService.getUserById(id);

        model.addAttribute("user", user);

        return "update_user";
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateUser(@PathVariable int id, User user) {

        user.setId(id);
        userService.updateUser(user);

        return "redirect:/users";
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(@PathVariable int id) {

        userService.deleteUser(id);

        return "redirect:/users";
    }

    @GetMapping("/back")
    public String showBack() {
        return "redirect:/users";
    }
}
