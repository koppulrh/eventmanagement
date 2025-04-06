// UserController.java (Controller Layer)
package com.example.eventmanagement.controller;

import com.example.eventmanagement.model.User;
import com.example.eventmanagement.service.UserService;
import com.example.eventmanagement.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if (userService.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists.");
        }
        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        Optional<User> existingUser = userService.getUserByUsername(user.getUsername());

        if (existingUser.isPresent() && userService.getPasswordEncoder().matches(user.getPassword(), existingUser.get().getPassword())) {
            String token = jwtUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Invalid credentials.");
    }
}
