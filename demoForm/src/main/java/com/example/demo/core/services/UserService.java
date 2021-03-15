package com.example.demo.core.services;

import com.example.demo.core.models.UserEntity;
import com.example.demo.core.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserEntity> getUserById(UUID id) {
        return userRepository.findById(id);
    }
}
