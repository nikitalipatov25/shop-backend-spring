package com.nikitalipatov.handmadeshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreationDTO {
    private String name;
    private String description;
    private int amount;
    private MultipartFile image;
    private double price;
    private String category;
}
