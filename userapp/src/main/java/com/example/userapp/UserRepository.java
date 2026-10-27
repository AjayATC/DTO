package com.example.userapp;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    public String getUserName() {
        return "Ajay";
    }
}

//This class will return the user's name.