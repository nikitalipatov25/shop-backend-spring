package com.nikitalipatov.handmadeshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductEditingDTO {
    private String name;
    private String description;
    private double price;
    private String image;
    private int amount;
}
