package com.nikitalipatov.handmadeshop.helpers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class FilterDTO {
    private String animal;
    private String[] categories;
    private boolean isDeal;
    private int priceFrom;
    private int priceTo;
}
