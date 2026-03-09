package com.khandekar.journalApp.services;

import com.khandekar.journalApp.entities.User;
import com.khandekar.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void saveEntry(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public Optional<User> getUserById(ObjectId id) {
        return userRepository.findById(id);
    }
    public void deleteUserById(ObjectId id) {
        userRepository.deleteById(id);
    }
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
