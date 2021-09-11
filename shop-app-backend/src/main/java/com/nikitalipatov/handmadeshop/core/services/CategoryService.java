package com.nikitalipatov.handmadeshop.core.services;

import com.nikitalipatov.handmadeshop.core.models.Animal;
import com.nikitalipatov.handmadeshop.core.models.Category;
import com.nikitalipatov.handmadeshop.core.models.Product;
import com.nikitalipatov.handmadeshop.core.repositories.AnimalRepository;
import com.nikitalipatov.handmadeshop.core.repositories.CategoryRepository;
import com.nikitalipatov.handmadeshop.core.repositories.ProductRepository;
import com.nikitalipatov.handmadeshop.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final AnimalRepository animalRepository;
    private final ProductRepository productRepository;
    private final FileService fileService;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, AnimalRepository animalRepository, ProductRepository productRepository, FileService fileService) {
        this.categoryRepository = categoryRepository;
        this.animalRepository = animalRepository;
        this.productRepository = productRepository;
        this.fileService = fileService;
    }

    public Set<Category> getCategoriesSet() {
        return new HashSet<>(categoryRepository.findAll());
    }

    public Optional<Category> getCategory(UUID id) {
        return categoryRepository.findById(id);
    }

    public Category addNewCategory(CategoryDTO categoryDTO) {
        Category newCategory = new Category();
        newCategory.setId(UUID.randomUUID());
        newCategory.setName(categoryDTO.getCategoryName());
        newCategory.setImageURL(fileService.getFileByName(categoryDTO.getImageURL()).getId());
        Set<Category> categories = new HashSet<>();
        categories.add(newCategory);
        return categoryRepository.save(newCategory);
    }

    public void addNewCategoryToAnimals(CategoryDTO categoryDTO) {
        for(int i = 0; i < categoryDTO.getAnimals().size(); i++) {
            var result = animalRepository.findByName(categoryDTO.getAnimals().get(i));
            result.get().setCategories(Collections.singleton(categoryRepository.findByName(categoryDTO.getCategoryName()).get()));
        }
    }

    public Optional<Boolean> deleteCategory(UUID id) {
        Optional<Category> result = categoryRepository.findById(id);
        return result.
                map(e -> {
                    List<Product> products = productRepository.findAllByCategoryIn(Collections.singletonList(result.get().getName()));
                    for (int i = 0; i < products.size(); i++) {
                        products.get(i).setCategory("Категория удалена");
                        productRepository.save(products.get(i));
                    }
                    List<Animal> animals = animalRepository.findAllByCategories(result.get());
                    for (int i = 0; i < animals.size(); i++) {
                        animals.get(i).getCategories().remove(result.get());
                        animalRepository.save(animals.get(i));
                    }
                    categoryRepository.deleteById(id);
                    return true;
                });
    }

    public Optional<Category> editCategory(UUID id, CategoryDTO categoryDTO) {
        Optional<Category> result = categoryRepository.findById(id);
        if (!result.get().getName().equals(categoryDTO.getCategoryName())) {
            List<Product> products = productRepository.findAllByCategoryIn(Collections.singletonList(result.get().getName()));
            for (int i = 0; i < products.size(); i++) {
                products.get(i).setCategory(categoryDTO.getCategoryName());
                productRepository.save(products.get(i));
            }
        }
        return result
                .map(e -> {
                    e.setName(categoryDTO.getCategoryName());
                    e.setImageURL(fileService.getFileByName(categoryDTO.getImageURL()).getId());
                    return categoryRepository.save(e);
                });
    }
}
