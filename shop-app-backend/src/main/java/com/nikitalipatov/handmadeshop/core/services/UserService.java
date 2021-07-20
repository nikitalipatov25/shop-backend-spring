package com.nikitalipatov.handmadeshop.core.services;

import com.nikitalipatov.handmadeshop.core.models.User;
import com.nikitalipatov.handmadeshop.core.repos.UserRepository;
import com.nikitalipatov.handmadeshop.helpers.AuthHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUser(HttpServletRequest request) {
        String username = AuthHelper.getUsernameFromToken(request);
        return userRepository.findByUsername(username);
    }
}
