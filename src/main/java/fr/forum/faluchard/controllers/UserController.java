package fr.forum.faluchard.controllers;

import fr.forum.faluchard.model.User;
import fr.forum.faluchard.services.UserService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class UserController {

    @Autowired
    private UserService userService;
    
    @PostMapping("/users/signup")
    public ResponseEntity<?> singUp(
        @RequestParam String username, 
        @RequestParam String password
    ) {
        try {
            User user = userService.create(username, password);
            return ResponseEntity.ok(user);
        } catch(RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
