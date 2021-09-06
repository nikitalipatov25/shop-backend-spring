package com.nikitalipatov.handmadeshop.core.services;

import com.nikitalipatov.handmadeshop.core.models.Animal;
import com.nikitalipatov.handmadeshop.core.models.Category;
import com.nikitalipatov.handmadeshop.core.repositories.AnimalRepository;
import com.nikitalipatov.handmadeshop.core.repositories.CategoryRepository;
import com.nikitalipatov.handmadeshop.dto.AnimalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public AnimalService(AnimalRepository animalRepository, CategoryRepository categoryRepository) {
        this.animalRepository = animalRepository;
        this.categoryRepository = categoryRepository;
    }

    public Set<Animal> getAnimalsSet() {
        return new HashSet<>(animalRepository.findAll());
    }

    public Optional<Animal> getAnimal(String name) {
        return animalRepository.findByName(name);
    }

    public Animal addNewAnimal(AnimalDTO animalDTO) {
    Animal newAnimal = new Animal();
    newAnimal.setName(animalDTO.getAnimalName());
    Set<Category> categorySet = new HashSet<>();
    for (int i = 0; i < animalDTO.getCategories().size(); i++) {
        if (categoryRepository.findByName(animalDTO.getCategories().get(i)).isEmpty()) {
            Category category = new Category();
            category.setName(animalDTO.getCategories().get(i));
            categoryRepository.save(category);
        }
        String name = animalDTO.getCategories().get(i);
        var category = categoryRepository.findByName(name).get();
        categorySet.add(category);
    }
    newAnimal.setCategories(categorySet);
    return animalRepository.save(newAnimal);
}
}
