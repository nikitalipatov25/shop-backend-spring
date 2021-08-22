package com.nikitalipatov.handmadeshop.controllers;

import com.nikitalipatov.handmadeshop.core.models.Animal;
import com.nikitalipatov.handmadeshop.core.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Transactional
@RequestMapping(value = "/api/animal")
public class AnimalController {

    private final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/get")
    public ResponseEntity<Set<Animal>> getAnimalsSet() {
        Set<Animal> result = animalService.getAnimalsSet();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<Optional<Animal>> getAnimal(@PathVariable(name = "name") String name) {
        Optional<Animal> result = animalService.getAnimal(name);
        return ResponseEntity.ok(result);
    }
}
