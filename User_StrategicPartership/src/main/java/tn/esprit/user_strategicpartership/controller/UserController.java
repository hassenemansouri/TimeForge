package tn.esprit.user_strategicpartership.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.user_strategicpartership.entity.User;
import tn.esprit.user_strategicpartership.service.UserImpl;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class UserController {

    private UserImpl userService;

    @PostMapping
    public void addUser(User user) {
        userService.addUser(user);
    }
    @GetMapping
    public List<User> FindAllUsers() {
        return userService.findAllUsers();
    }

}
