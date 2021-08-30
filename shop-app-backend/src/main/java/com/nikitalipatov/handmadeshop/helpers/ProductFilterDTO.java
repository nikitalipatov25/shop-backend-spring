package com.nikitalipatov.handmadeshop.helpers;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductFilterDTO {
    private String animal;
    private String[] categories;
    private boolean isDeal;
    private int priceFrom = 1;
    private int priceTo = Integer.MAX_VALUE;

    private int pageNumber = 0;
    private int pageSize = 4;
    private String sortBy = "price";
    private String sortDirection = "ASC";
    private String searchText;
}
