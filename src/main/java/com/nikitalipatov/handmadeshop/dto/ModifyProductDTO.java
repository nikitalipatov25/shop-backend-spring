package com.nikitalipatov.handmadeshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ModifyProductDTO {
    private String name;
    private String description;
    private double price;
    private String image;
    private String animal;
    private String category;
    private int amount;
    private double rating;
}
