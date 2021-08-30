package com.nikitalipatov.handmadeshop.helpers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ProductDTO {
    private UUID id;
    private String name;
    private String description;
    private double price;
    private String image;
    private int amount;
    private double rating;
}
