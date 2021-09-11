package com.nikitalipatov.handmadeshop.controllers;

import com.nikitalipatov.handmadeshop.core.models.Animal;
import com.nikitalipatov.handmadeshop.core.services.AnimalService;
import com.nikitalipatov.handmadeshop.dto.AnimalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

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

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Animal>> getAnimal(@PathVariable(name = "id")UUID id) {
        Optional<Animal> result = animalService.getAnimal(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add")
    public ResponseEntity<Animal> addNewAnimal(@RequestBody AnimalDTO animalDTO) {
        return ResponseEntity.ok(animalService.addNewAnimal(animalDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAnimal(@PathVariable(name = "id")UUID id) {
        Optional<Boolean> result = animalService.deleteAnimal(id);
        return result
                .map(e -> ResponseEntity.noContent().build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<Animal> editAnimal(@PathVariable(name = "id")UUID id, @RequestBody AnimalDTO animalDTO) {
        Optional<Animal> result = animalService.editAnimal(id, animalDTO);
        return result
                .map(e -> ResponseEntity.ok(e))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
