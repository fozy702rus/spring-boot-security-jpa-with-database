package spring.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.app.dto.UserDTO;
import spring.app.dto.UserMapper;
import spring.app.model.User;
import spring.app.service.UserService;
import java.security.Principal;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/current")
    public UserDTO getCurrentUser(Principal principal) {

        String username = principal.getName();
        User user = userService.getUserByUsername(username);
        return UserMapper.toDTO(user);
    }
}