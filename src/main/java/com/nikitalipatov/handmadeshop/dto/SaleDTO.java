package com.nikitalipatov.handmadeshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {
    private String name;
    private MultipartFile image;
    private String date;
    private String expirationDate;
    private double discount;
    private String[] products;
}
