package tn.esprit.user_strategicpartership.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.user_strategicpartership.entity.User;
import tn.esprit.user_strategicpartership.service.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;  // Injection avec @Autowired

    @Operation(summary = "Ajouter un utilisateur")
    @PostMapping("/add")
    public void addUser(@RequestBody User user) {  // Ajout de @RequestBody pour Swagger
        userService.addUser(user);
    }

    @Operation(summary = "Lister tous les utilisateurs")
    @GetMapping
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }
}
