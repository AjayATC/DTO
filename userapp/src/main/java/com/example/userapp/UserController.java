
package com.example.userapp;

import com.example.userapp.User;
import com.example.userapp.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(
            @PathVariable Long id) {

        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // Create a user
    @PostMapping
    public ResponseEntity<User> createUser(
            @Valid @RequestBody User user) {

        User savedUser = userService.createUser(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedUser);
    }


    // Update a user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody User user) {

        User updatedUser = userService.updateUser(id, user);

        return ResponseEntity.ok(updatedUser);
    }

    // Delete a user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long id) {

        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<User> searchUsers(
            @RequestParam String name) {

        return userService.searchUsersByName(name);
    }

    @GetMapping("/email")
    public List<User> getUsersByEmail(
            @RequestParam String email) {

        return userService.getUsersByEmail(email);
    }

}
