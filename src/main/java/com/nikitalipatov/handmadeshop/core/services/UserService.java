package com.nikitalipatov.handmadeshop.core.services;

import com.nikitalipatov.handmadeshop.core.models.ERole;
import com.nikitalipatov.handmadeshop.core.models.Role;
import com.nikitalipatov.handmadeshop.core.models.User;
import com.nikitalipatov.handmadeshop.core.repositories.RoleRepository;
import com.nikitalipatov.handmadeshop.core.repositories.UserRepository;
import com.nikitalipatov.handmadeshop.dto.UserDTO;
import com.nikitalipatov.handmadeshop.helpers.AuthHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository =roleRepository;
    }

    public Optional<User> findUser(HttpServletRequest request) {
        String username = AuthHelper.getUsernameFromToken(request);
        return userRepository.findByUsername(username);
    }

    public Optional<User> modifyUser(HttpServletRequest request, UserDTO userDTO) {
        Optional<User> result = userRepository.findByUsername(AuthHelper.getUsernameFromToken(request));
        return result
                .map(e -> {
                    e.setName(userDTO.getName());
                    e.setSurname(userDTO.getSurname());
                    e.setSecondName(userDTO.getSecondName());
                    e.setAddress(userDTO.getAddress());
                    e.setPhoneNumber(userDTO.getPhoneNumber());
                    return userRepository.save(e);
                });
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    public Optional<User> promoteToAdmin(Long id) {
        Optional<User> result = userRepository.findById(id);
        return result
                .map(e -> {
                    Set<Role> roles = new HashSet<>();
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Роль не найдена."));
                    roles.add(adminRole);
                    e.setRoles(roles);
                    return userRepository.save(e);
                });
    }

    public Optional<User> demoteToUser(Long id) {
        Optional<User> result = userRepository.findById(id);
        return result
                .map(e -> {
                    Set<Role> roles = new HashSet<>();
                    Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Роль не найдена."));
                    roles.add(userRole);
                    e.setRoles(roles);
                    return userRepository.save(e);
                });
    }
}
