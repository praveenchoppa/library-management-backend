package com.library.management.service;

import org.springframework.stereotype.Service;

import com.library.management.entity.User;
import com.library.management.repository.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    private UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User addUser(User user){
        return userRepository.save(user);
    }
}
