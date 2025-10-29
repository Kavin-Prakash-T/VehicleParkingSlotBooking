package com.example.ParkingSlotBooking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ParkingSlotBooking.entity.User;
import com.example.ParkingSlotBooking.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String registerUser(User user) {
        List<User> existing = userRepository.findAll();
        for (User u : existing) {
            if (u.getEmail().equalsIgnoreCase(user.getEmail())) {
                return "User already exists with this email!";
            }
        }
        userRepository.save(user);
        return "User registration done";
    }

    public String loginUser(User loginUser) {
        List<User> users = userRepository.findAll();

        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(loginUser.getEmail()) &&
                u.getPassword().equals(loginUser.getPassword())) {
                return "Login successful: " + u.getName();
            }
        }
        return "Invalid email or password!";
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
