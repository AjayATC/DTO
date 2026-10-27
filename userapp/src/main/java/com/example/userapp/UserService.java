package com.example.userapp;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    //constructor-based Dependency Injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUserName() {
        return userRepository.getUserName();
    }
}

//The service will use the repository to get the user's name.