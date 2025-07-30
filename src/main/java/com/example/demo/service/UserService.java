package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.User;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            System.err.println("Error creating user: " + e.getMessage());
            e.printStackTrace();
            return null; 
        }
    }

    public List<User> createUser(List<User> users) {
        try {
            return userRepository.saveAll(users);
        } catch (Exception e) {
            System.err.println("Error creating users: " + e.getMessage());
            e.printStackTrace();
            return null; 
        }
    }

    public Optional<User> getUserById(int id) {
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            System.err.println("Error fetching user by ID: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<User> getUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            System.err.println("Error fetching users: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }

    public User updateUser(User user) {
        try {
            Optional<User> optionalUser = userRepository.findById(user.getId());
            if (optionalUser.isPresent()) {
                User oldUser = optionalUser.get();
                oldUser.setName(user.getName());
                oldUser.setAddress(user.getAddress());
                return userRepository.save(oldUser);
            } else {
                System.out.println("User not found with ID: " + user.getId());
                return new User();
            }
        } catch (Exception e) {
            System.err.println("Error updating user: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public String deleteUserById(int id) {
        try {
            userRepository.deleteById(id);
            return "User deleted with ID: " + id;
        } catch (Exception e) {
            System.err.println("Error deleting user with ID " + id + ": " + e.getMessage());
            e.printStackTrace();
            return "Error deleting user with ID: " + id;
        }
    }
}
