package com.nikitalipatov.handmadeshop.specifications;

import com.nikitalipatov.handmadeshop.core.models.Product;
import com.nikitalipatov.handmadeshop.core.models.Product_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ProductSpecifications {

    public static Specification<Product> isDeal(boolean isDeal) {
            return ((root, criteriaQuery, criteriaBuilder) -> {
                if (!isDeal) {
                    return criteriaBuilder.conjunction();
                } else return criteriaBuilder.greaterThan(root.get(Product_.DISCOUNT_PRICE), 0.0);
            });
    }

    public static Specification<Product> inPriceRange(int priceFrom, int priceTo) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.between(root.get(Product_.PRICE), priceFrom, priceTo));
    }

    public static Specification<Product> inCategories(String[] categories) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            if (categories == null | (categories != null ? categories.length : 0) == 0) {
                return criteriaBuilder.conjunction();
            }
            return root.get(Product_.CATEGORY).in(Arrays.asList(categories));
        });
    }

    public static Specification<Product> likeSearchText(String searchText) {
        return ((root, query, criteriaBuilder) -> {
            if (searchText == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get(Product_.NAME), "%" + searchText + "%");
        });
    }
}
