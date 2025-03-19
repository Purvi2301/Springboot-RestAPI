package com.test.test.services;

import com.test.test.entities.User;
import com.test.test.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;
    public void createUser(User user) {
        userRepo.save(user);
    }

//    public void login(User user) {
//
//    }
}
