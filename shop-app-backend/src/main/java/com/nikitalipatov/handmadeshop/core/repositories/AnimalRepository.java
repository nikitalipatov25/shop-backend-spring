package com.nikitalipatov.handmadeshop.core.repositories;

import com.nikitalipatov.handmadeshop.core.models.Animal;
import com.nikitalipatov.handmadeshop.core.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, UUID> {
    List<Animal> findAllByName(String name);
    Optional<Animal> findByName(String name);
    List<Animal> findAllByCategories(Category category);

}
