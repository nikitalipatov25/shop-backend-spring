package com.nikitalipatov.handmadeshop.core.services;

import com.nikitalipatov.handmadeshop.core.models.Deal;
import com.nikitalipatov.handmadeshop.core.repositories.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class DealService {

    private final DealRepository dealRepository;
    private final ProductService productService;
    private final FileService fileService;

    @Autowired
    public DealService(DealRepository dealRepository, ProductService productService, FileService fileService) {
        this.dealRepository = dealRepository;
        this.productService = productService;
        this.fileService = fileService;
    }

    public Deal create(Deal deal, String[] products) {
        Deal newDeal = new Deal();
        newDeal.setName(deal.getName());
        newDeal.setDescription(deal.getDescription());
        newDeal.setDate(deal.getDate());
        newDeal.setExpirationDate(deal.getExpirationDate());
        newDeal.setType(deal.getType());
        newDeal.setDiscount(deal.getDiscount());

        var result = fileService.getFileByName(deal.getImage());
        newDeal.setImage(result.getId());

//        if (products == null) {
//            productService.setPromotion(deal.getType(), deal.getDiscount());
//        }
        return dealRepository.save(newDeal);
    }

    public Page<Deal> getPromotions(Pageable pageable) {
        Page<Deal> result = dealRepository.findAll(pageable);
        return result;
    }

}
