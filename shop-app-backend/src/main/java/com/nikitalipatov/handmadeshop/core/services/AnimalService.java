package com.nikitalipatov.handmadeshop.core.services;

import com.nikitalipatov.handmadeshop.core.models.Animal;
import com.nikitalipatov.handmadeshop.core.models.Category;
import com.nikitalipatov.handmadeshop.core.models.Product;
import com.nikitalipatov.handmadeshop.core.repositories.AnimalRepository;
import com.nikitalipatov.handmadeshop.core.repositories.CategoryRepository;
import com.nikitalipatov.handmadeshop.core.repositories.ProductRepository;
import com.nikitalipatov.handmadeshop.dto.AnimalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final FileService fileService;

    @Autowired
    public AnimalService(AnimalRepository animalRepository, CategoryRepository categoryRepository, ProductRepository productRepository, FileService fileService) {
        this.animalRepository = animalRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.fileService = fileService;
    }

    public Set<Animal> getAnimalsSet() {
        return new HashSet<>(animalRepository.findAll());
    }

    public Optional<Animal> getAnimal(UUID id) {
        return animalRepository.findById(id);
    }

    public Animal addNewAnimal(AnimalDTO animalDTO) {
        Animal newAnimal = new Animal();
        newAnimal.setId(UUID.randomUUID());
        newAnimal.setName(animalDTO.getAnimalName());
        newAnimal.setImageURL(fileService.getFileByName(animalDTO.getImageURL()).getId());
        Set<Category> categorySet = new HashSet<>();
        for (int i = 0; i < animalDTO.getCategories().size(); i++) {
            if (categoryRepository.findByName(animalDTO.getCategories().get(i)).isEmpty()) {
                Category category = new Category();
                category.setId(UUID.randomUUID());
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

    public Optional<Boolean> deleteAnimal(UUID id) {
        Optional<Animal> result = animalRepository.findById(id);
        return result
                .map(e -> {
                    List<Product> products = productRepository.findAllByAnimalIn(Collections.singletonList(result.get().getName()));
                    for (int i = 0; i < products.size(); i++) {
                        products.get(i).setAnimal("Животное удалено");
                        productRepository.save(products.get(i));
                    }
                    animalRepository.deleteById(id);
                   return true;
                });
    }

    public Optional<Animal> editAnimal(UUID id, AnimalDTO animalDTO) {
        Optional<Animal> result = animalRepository.findById(id);
        if (!result.get().getName().equals(animalDTO.getAnimalName())) {
            List<Product> products = productRepository.findAllByAnimalIn(Collections.singletonList(result.get().getName()));
            for (int i = 0; i < products.size(); i++) {
                products.get(i).setAnimal(animalDTO.getAnimalName());
                productRepository.save(products.get(i));
            }
        }
        return result
                .map(e -> {
                    e.setName(animalDTO.getAnimalName());
                    e.setImageURL(fileService.getFileByName(animalDTO.getImageURL()).getId());
                    Set<Category> categories = result.get().getCategories();
                    for (int i = 0; i < animalDTO.getCategories().size(); i++) {
                        Optional<Category> category = categoryRepository.findByName(animalDTO.getCategories().get(i));
                        if (!category.isPresent()) {
                            Category newCategory = new Category();
                            newCategory.setId(UUID.randomUUID());
                            newCategory.setName(animalDTO.getCategories().get(i));
                            categoryRepository.save(newCategory);
                            categories.add(newCategory);
                        } else if (!categories.contains(category.get())) {
                            categories.add(category.get());
                        }
                    }
                    e.setCategories(categories);
                    return animalRepository.save(e);
                });
    }
}
