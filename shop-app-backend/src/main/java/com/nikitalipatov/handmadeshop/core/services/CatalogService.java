package com.nikitalipatov.handmadeshop.core.services;

import com.nikitalipatov.handmadeshop.supportingClasses.SearchParameterAnalyzer;
import com.nikitalipatov.handmadeshop.core.repos.CatalogRepository;
import com.nikitalipatov.handmadeshop.core.models.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CatalogService {

    SearchParameterAnalyzer searchParameterAnalyzer = new SearchParameterAnalyzer();

    private final CatalogRepository catalogRepository;
    private final FileService fileService;

    @Autowired
    public CatalogService(CatalogRepository catalogRepository, FileService fileService) {
        this.catalogRepository = catalogRepository;
        this.fileService = fileService;
    }

    public Page<Catalog> listAll(Pageable pageable) {
        Page<Catalog> result = catalogRepository.findAll(pageable);
//        searchParameterAnalyzer.analyzingSearchParameter(search);
//        String productName = searchParameterAnalyzer.getProductName();
//        double startPrice = searchParameterAnalyzer.getStartPrice();
//        double endPrice = searchParameterAnalyzer.getEndPrice();
//        try {
//            if (category.equals("empty") == false) {
//                if (productName != null && checkboxes.length != 0) {
//                    result = catalogRepository.findByNameLikeAndPriceBetweenAndAnimalAndCategoryIn
//                            ("%" + productName + "%", startPrice, endPrice, category, checkboxes, pageable);
//                } else if (productName != null && checkboxes.length == 0) {
//                    result = catalogRepository.findByNameLikeAndPriceBetweenAndAnimal
//                            ("%" + productName + "%", startPrice, endPrice, category, pageable);
//                } else if (productName == null && checkboxes.length != 0) {
//                    result = catalogRepository.findByPriceBetweenAndAnimalAndCategoryIn
//                            (startPrice, endPrice, category, checkboxes, pageable);
//                } else {
//                    result = catalogRepository.findByPriceBetweenAndAnimal
//                            (startPrice, endPrice, category, pageable);
//                }
//            }
//            if (category.equals("empty") == true) {
//                if (productName != null && checkboxes.length != 0) {
//                    result = catalogRepository.findByNameLikeAndPriceBetweenAndCategoryIn
//                            ("%" + productName + "%", startPrice, endPrice, checkboxes, pageable);
//                } else if (productName != null && checkboxes.length == 0) {
//                    result = catalogRepository.findByNameLikeAndPriceBetween
//                            ("%" + productName + "%", startPrice, endPrice, pageable);
//                } else if (productName == null && checkboxes.length != 0) {
//                    result = catalogRepository.findByPriceBetweenAndAndCategoryIn
//                            (startPrice, endPrice, checkboxes, pageable);
//                } else {
//                    result = catalogRepository.findByPriceBetween
//                            (startPrice, endPrice, pageable);
//                }
//            }
//        } catch (Exception e) {
//            e.getMessage();
//        }
        return result;
    }

    public Optional<Catalog> getById(UUID id) {
        return catalogRepository.findById(id);
    }

    public boolean validateEntity(Catalog catalog) {
        boolean result = true; // реализация метода
        return result;
    }

    public Catalog save(Catalog catalog) {
        Catalog newEntity = new Catalog();
        newEntity.setId(UUID.randomUUID());
        newEntity.setDescription(catalog.getDescription());
        newEntity.setQuantity(catalog.getQuantity());
        newEntity.setName(catalog.getName());

        newEntity.setAnimal(catalog.getAnimal());
        newEntity.setCategory(catalog.getCategory());

        var result = fileService.getFileByName(catalog.getPhoto());
        newEntity.setPhoto(result.getId());

        newEntity.setPrice(catalog.getPrice());
        newEntity.setPromotion("-");
        newEntity.setPromotionPrice(0.0);
        return catalogRepository.save(newEntity);
    }

    public Optional<Catalog> editCatalog(UUID uuid, Catalog catalog) {
        Optional<Catalog> result = catalogRepository.findById(uuid);
        return result
                .map(entity -> {
                    entity.setQuantity(catalog.getQuantity());
                    if (catalog.getPhoto() != null) {
                        var file = fileService.getFileByName(catalog.getPhoto());
                        entity.setPhoto(file.getId());
                    }
                    entity.setPrice(catalog.getPrice());
                    entity.setName(catalog.getName());
                    entity.setDescription(catalog.getDescription());
                    entity.setAnimal(catalog.getAnimal());
                    entity.setCategory(catalog.getCategory());
                    return catalogRepository.save(entity);
                });

    }

    public Optional<Boolean> deleteById(UUID id) {
        Optional<Catalog> result = catalogRepository.findById(id);
        return result
                .map(catalog -> {
                    catalogRepository.deleteById(id);
                    return true;
                });
    }

    public Optional<Catalog> modifyKolInCatalog(UUID productID, int productKolInCart) {
        Optional<Catalog> result = catalogRepository.findById(productID);
        int nowInCatalog = result.get().getQuantity();
        return result
                .map(modifyingEntity -> {
                    modifyingEntity.setQuantity(nowInCatalog - productKolInCart);
                    return catalogRepository.save(modifyingEntity);
                });
    }

    public void setPromotion(String promotionType, int promotionDiscount) {
        var result = catalogRepository.findAllByCategory(promotionType);
        for (int i = 0; i < result.size(); i++) {
            result.get(i).setPromotion(promotionType);
            double temp = result.get(i).getPrice() * promotionDiscount / 100;
            result.get(i).setPromotionPrice(result.get(i).getPrice() - temp);
        }

    }

}
