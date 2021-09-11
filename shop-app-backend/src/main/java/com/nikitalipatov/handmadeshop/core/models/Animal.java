package com.nikitalipatov.handmadeshop.core.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "animals")
public class Animal {

    @Id
    private UUID id;
    private String imageURL;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "animals_categories",
            joinColumns = @JoinColumn(name = "animal_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    public Animal() {
    }
}
