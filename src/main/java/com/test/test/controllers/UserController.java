package com.test.test.controllers;

import com.test.test.entities.User;
import com.test.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @PostMapping("/register")
    public ResponseEntity<Void> createUser(@RequestBody User user){
        user.setPassword(encoder.encode(user.getPassword()));
        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
//    @PostMapping("/login")
//    public ResponseEntity<Void> login(@RequestBody User user){
//        userService.login(user);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }


}
