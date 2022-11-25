package com.nikitalipatov.handmadeshop.core.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "products")
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
    private double rating;
    private String category;
    private String sale;
    private double discountPrice;

    public Product() {
    }
}
