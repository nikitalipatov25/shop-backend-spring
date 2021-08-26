package com.nikitalipatov.handmadeshop.specifications;

import com.nikitalipatov.handmadeshop.core.models.Product;
import com.nikitalipatov.handmadeshop.core.models.Product_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductSpecifications {

    public static Specification<Product> equalsAnimal(String animalName) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(Product_.ANIMAL), animalName);
        });
    }

    public static Specification<Product> priceFilter(int priceFrom, int priceTo) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.between(root.get(Product_.PRICE), priceFrom, priceTo);
        });
    }

    public static Specification<Product> inCategories(String[] category) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(Product_.CATEGORY), category);
        });
    }
}
