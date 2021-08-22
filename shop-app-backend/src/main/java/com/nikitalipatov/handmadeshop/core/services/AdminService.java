package com.nikitalipatov.handmadeshop.core.services;

import com.nikitalipatov.handmadeshop.core.models.Animal;
import com.nikitalipatov.handmadeshop.core.repositories.AnimalRepository;
import com.nikitalipatov.handmadeshop.core.models.Category;
import com.nikitalipatov.handmadeshop.core.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminService {

    private final AnimalRepository animalRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public AdminService(AnimalRepository animalRepository, CategoryRepository categoryRepository) {
        this.animalRepository = animalRepository;
        this.categoryRepository = categoryRepository;
    }

    public Set<Animal> getAnimalsSet() {
        return new HashSet<>(animalRepository.findAll());
    }

    public Optional<Animal> getAnimal(String name) {
        return animalRepository.findByName(name);
    }

    public List<Animal> getAnimalsListByName(String name) {
        return animalRepository.findAllByName(name);
    }

//    public Animal addAnimal(String animal, String[] categoryArray) {
//        Animal newAnimal = new Animal();
//        newAnimal.setName(animal);
//        Set<Category> categorySet = new HashSet<>();
//        for (int i = 0; i < categoryArray.length; i++) {
//            if (categoryRepository.findByName(categoryArray[i]) == null) {
//                Category category = new Category();
//                category.setName(categoryArray[i]);
//                categoryRepository.save(category);
//            }
//            categorySet.add(categoryRepository.findByName(categoryArray[i]));
//        }
//        newAnimal.setCategories(categorySet);
//        return animalRepository.save(newAnimal);
//    }

    public List<Category> getCategoriesList() {
        return categoryRepository.findAll();
    }

    public List<Category> getCategoriesListByName(String name) {
        return categoryRepository.findAllByName(name);
    }

    public Category addCategory(String[] animalArray, Category category) {
        for(int i = 0; i < animalArray.length; i++) {
            animalRepository.findByName(animalArray[i]);
        }
        Category newCategory = new Category();
        return categoryRepository.save(newCategory);
    }
}
