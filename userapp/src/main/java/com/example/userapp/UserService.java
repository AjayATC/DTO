
package com.example.userapp;

import com.example.userapp.User;
import com.example.userapp.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User createUser(User user) {
        user.setId(null);
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        userRepository.delete(user);
    }

    public List<User> searchUsersByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }

    public List<User> getUsersByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
