//package ru.kata.spring.boot_security.demo.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//import ru.kata.spring.boot_security.demo.model.User;
//import ru.kata.spring.boot_security.demo.service.UserService;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/admin")
//@PreAuthorize("hasRole('ADMIN')")
//public class AdminRestController {
//
//    private final UserService userService;
//
//    @Autowired
//    public AdminRestController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping("/users")
//    public List<User> userPage() {
//
//        return userService.getUsers();
//    }
//
//    @PostMapping("/users")
//    public User addUser(@RequestBody User user) {
//
//        userService.addUser(user);
//        return user;
//    }
//
//    @GetMapping("/users/{id}")
//    public User getUserById(@PathVariable int id) {
//
//        return userService.getUserById(id);
//    }
//
//    @PutMapping("/users/{id}")
//    public User updateUser(@PathVariable int id, @RequestBody User user) {
//
//        user.setId(id);
//        userService.updateUser(user);
//
//        return user;
//    }
//
//    @DeleteMapping("/users/{id}")
//    public void deleteUser(@PathVariable int id) {
//
//         userService.deleteUser(id);
//    }
//}