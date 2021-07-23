package com.nikitalipatov.handmadeshop.core.services;

import com.nikitalipatov.handmadeshop.core.models.Promotion;
import com.nikitalipatov.handmadeshop.core.repos.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class PromotionService {

    private final PromotionRepository promotionRepository;
    private final CatalogService catalogService;

    @Autowired
    public PromotionService(PromotionRepository promotionRepository, CatalogService catalogService) {
        this.promotionRepository = promotionRepository;
        this.catalogService = catalogService;
    }

    public Promotion create(Promotion promotion, String[] products) {
        Promotion newPromotion = new Promotion();
        newPromotion.setName(promotion.getName());
        newPromotion.setExpirationDate(promotion.getExpirationDate());
        newPromotion.setType(promotion.getType());
        newPromotion.setDiscount(promotion.getDiscount());
        if (products == null) {
            catalogService.setPromotion(promotion.getType(), promotion.getDiscount());
        }
        return promotionRepository.save(newPromotion);
    }

}
