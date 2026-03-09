package com.khandekar.journalApp.controller;

import com.khandekar.journalApp.entities.User;
import com.khandekar.journalApp.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if(users != null) {
            return new ResponseEntity<>(users,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable ObjectId id) {
        Optional<User> user = userService.getUserById(id);
        if(user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        try {
            userService.saveEntry(newUser);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{username}")
    public ResponseEntity<User> updateUserById(@PathVariable String username,  @RequestBody User newUser) {
        User oldUser = userService.getUserByUsername(username);
        if(oldUser != null) {
            oldUser.setUsername(newUser.getUsername());
            oldUser.setPassword(newUser.getPassword());
            userService.saveEntry(oldUser);
        }
        return new ResponseEntity<>(newUser, HttpStatus.NO_CONTENT);
    }
}
