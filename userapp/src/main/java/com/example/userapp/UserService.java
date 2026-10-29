
package com.example.userapp.service;

import com.example.userapp.dto.UserMapper;
import com.example.userapp.dto.UserRequestDTO;
import com.example.userapp.dto.UserResponseDTO;
import com.example.userapp.UserNotFoundException;
import com.example.userapp.User;
import com.example.userapp.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get all users
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toResponseDTO)
                .toList();
    }

    // Get user by ID
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return UserMapper.toResponseDTO(user);
    }

    // Create user
    @Transactional
    public UserResponseDTO createUser(UserRequestDTO dto) {
        User user = UserMapper.toEntity(dto);
        User savedUser = userRepository.save(user);

        return UserMapper.toResponseDTO(savedUser);
    }

    // Update user
    @Transactional
    public UserResponseDTO updateUser(
            Long id, UserRequestDTO dto) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        existingUser.setName(dto.getName());
        existingUser.setEmail(dto.getEmail());

        User updatedUser = userRepository.save(existingUser);

        return UserMapper.toResponseDTO(updatedUser);
    }

    // Delete user
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        userRepository.delete(user);
    }

    // Search users by name
    public List<UserResponseDTO> searchUsersByName(String name) {
        return userRepository
                .findByNameContainingIgnoreCase(name)
                .stream()
                .map(UserMapper::toResponseDTO)
                .toList();
    }

    // Find users by email
    public List<UserResponseDTO> getUsersByEmail(String email) {
        return userRepository.findByEmail(email)
                .stream()
                .map(UserMapper::toResponseDTO)
                .toList();
    }


    public Page<UserResponseDTO> getUsersPaginated(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(UserMapper::toResponseDTO);
    }

}
