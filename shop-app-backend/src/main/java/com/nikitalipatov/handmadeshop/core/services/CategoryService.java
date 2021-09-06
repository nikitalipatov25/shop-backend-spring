package com.nikitalipatov.handmadeshop.core.services;

import com.nikitalipatov.handmadeshop.core.models.Category;
import com.nikitalipatov.handmadeshop.core.repositories.AnimalRepository;
import com.nikitalipatov.handmadeshop.core.repositories.CategoryRepository;
import com.nikitalipatov.handmadeshop.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final AnimalRepository animalRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, AnimalRepository animalRepository) {
        this.categoryRepository = categoryRepository;
        this.animalRepository = animalRepository;
    }

    public Set<Category> getCategoriesSet() {
        return new HashSet<>(categoryRepository.findAll());
    }

    public Category addNewCategory(CategoryDTO categoryDTO) {
        Category newCategory = new Category();
        newCategory.setName(categoryDTO.getCategoryName());
        Set<Category> categories = new HashSet<>();
        categories.add(newCategory);
        for(int i = 0; i < categoryDTO.getAnimals().size(); i++) {
            var result = animalRepository.findByName(categoryDTO.getAnimals().get(i));
            result.get().setCategories(categories);
        }
        return categoryRepository.save(newCategory);
    }
}
