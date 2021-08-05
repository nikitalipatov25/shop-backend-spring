package com.nikitalipatov.handmadeshop.core.services;

import com.nikitalipatov.handmadeshop.core.models.Promotion;
import com.nikitalipatov.handmadeshop.core.repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class PromotionService {

    private final PromotionRepository promotionRepository;
    private final CatalogService catalogService;
    private final FileService fileService;

    @Autowired
    public PromotionService(PromotionRepository promotionRepository, CatalogService catalogService, FileService fileService) {
        this.promotionRepository = promotionRepository;
        this.catalogService = catalogService;
        this.fileService = fileService;
    }

    public Promotion create(Promotion promotion, String[] products) {
        Promotion newPromotion = new Promotion();
        newPromotion.setName(promotion.getName());
        newPromotion.setDescription(promotion.getDescription());
        newPromotion.setDate(promotion.getDate());
        newPromotion.setExpirationDate(promotion.getExpirationDate());
        newPromotion.setType(promotion.getType());
        newPromotion.setDiscount(promotion.getDiscount());

        var result = fileService.getFileByName(promotion.getImage());
        newPromotion.setImage(result.getId());

        if (products == null) {
            catalogService.setPromotion(promotion.getType(), promotion.getDiscount());
        }
        return promotionRepository.save(newPromotion);
    }

    public Page<Promotion> getPromotions(Pageable pageable) {
        Page<Promotion> result = promotionRepository.findAll(pageable);
        return result;
    }

}
