package com.example.toDo.rest;

import com.example.toDo.entity.User;
import com.example.toDo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/security")
public class SecurityController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public void registerUser(@RequestBody User user){
        userRepository.save(user);
    }

    @GetMapping("/getUsers")
    public List<User> getUser() {
        return userRepository.findAll();
    }
}
