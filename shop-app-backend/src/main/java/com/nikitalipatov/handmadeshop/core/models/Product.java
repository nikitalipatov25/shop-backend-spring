package com.nikitalipatov.handmadeshop.core.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "products",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")})
@Getter
@Setter
@AllArgsConstructor
public class Product {

    @Id
    private UUID id;
    private String name;
    private String description;
    private double price;
    private String image;
    private int amount;
    private String animal;
    private String category;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Set<Comment> comments;

    public Product() {
    }
}
