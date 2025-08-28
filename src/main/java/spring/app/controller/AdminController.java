package spring.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.app.model.User;
import spring.app.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String usersInfo(Model model) {

        List<User> users = userService.getUsers();
        model.addAttribute("users", users);

        return "admin/main_page";
    }

    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable("id") Integer id, Model model) {

        User user = userService.getUserById(id);
        model.addAttribute("userById", user);

        return "admin/user_details";
    }

    @GetMapping("/new-user")
    public String addUser(Model model) {

        model.addAttribute("user", new User());

        return "admin/add_user";
    }

    @PostMapping("/new-user")
    public String saveUser(@ModelAttribute User user) {

        userService.addUser(user);

        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable int id, Model model) {

        User user = userService.getUserById(id);

        model.addAttribute("user", user);

        return "admin/update_user";
    }

    @PutMapping("/edit/{id}")
    public String updateUser(@PathVariable int id, @ModelAttribute User user) {

        user.setId(id);
        userService.updateUser(user);

        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {

        userService.deleteUser(id);

        return "redirect:/admin";
    }
}
