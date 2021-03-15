package com.example.demo.api.rest;

import com.example.demo.core.models.UserEntity;
import com.example.demo.core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@CrossOrigin(allowedHeaders = {
        "Access-Control-Allow-Origin",
        "Access-Control-Allow-Headers",
        "Access-Control-Allow-Methods",
        "Content-Type"
},
        methods = { RequestMethod.POST,RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT })
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getSingleUser(@PathVariable(name = "id") UUID id) {
        Optional<UserEntity> result = userService.getUserById(id);
        return result
                .map(userEntity -> ResponseEntity.ok(userEntity))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
