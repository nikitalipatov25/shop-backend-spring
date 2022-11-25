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
@Table(name = "category")
public class Category {

    @Id
    private UUID id;
    private String image;
    private String name;
//    @OneToMany
//    @JoinTable(name = "product_category",
//            joinColumns = @JoinColumn(name = "category_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id"))
//    private Set<Product> products;

    public Category() {
    }
}
