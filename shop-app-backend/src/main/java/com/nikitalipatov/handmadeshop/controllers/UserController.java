package com.nikitalipatov.handmadeshop.controllers;

import com.nikitalipatov.handmadeshop.core.models.User;
import com.nikitalipatov.handmadeshop.core.services.UserService;
import com.nikitalipatov.handmadeshop.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Transactional
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<Optional<User>> getUser(HttpServletRequest request) {
        Optional<User> user = userService.findUser(request);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/modify")
    public ResponseEntity<User> modifyUser(HttpServletRequest request, @RequestBody UserDTO userDTO) {
        Optional<User> result = userService.modifyUser(request, userDTO);
        return result
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
