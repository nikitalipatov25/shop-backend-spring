package com.example.demo.core.services;

import com.example.demo.core.models.UserEntity;
import com.example.demo.core.repos.oldUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final oldUserRepository oldUserRepository;

    @Autowired
    public UserService (oldUserRepository oldUserRepository) {
        this.oldUserRepository = oldUserRepository;
    }

    public Optional<UserEntity> getUserById(UUID id) {
        return oldUserRepository.findById(id);
    }
}
