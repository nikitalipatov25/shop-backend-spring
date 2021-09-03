package com.nikitalipatov.handmadeshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {
    private String name;
    private String description;
    private String image;
    private String date;
    private String expirationDate;
    private double discount;
    private String saleType;
    private List<UUID> products;
    private List<String> animals;
    private List<String> categories;
}
