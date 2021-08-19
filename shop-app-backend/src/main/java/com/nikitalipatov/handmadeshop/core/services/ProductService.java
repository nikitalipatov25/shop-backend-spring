package com.nikitalipatov.handmadeshop.core.services;

import com.nikitalipatov.handmadeshop.core.repositories.ProductRepository;
import com.nikitalipatov.handmadeshop.helpers.SearchParameterAnalyzer;
import com.nikitalipatov.handmadeshop.core.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final FileService fileService;

    @Autowired
    public ProductService(ProductRepository productRepository, FileService fileService) {
        this.productRepository = productRepository;
        this.fileService = fileService;
    }

    public Page<Product> listAll(Pageable pageable) {
        Page<Product> result = productRepository.findAll(pageable);
        return result;
    }

    public Optional<Product> getById(UUID id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        Product newEntity = new Product();
        newEntity.setId(UUID.randomUUID());
        newEntity.setDescription(product.getDescription());
        newEntity.setAmount(product.getAmount());
        newEntity.setName(product.getName());

        newEntity.setAnimal(product.getAnimal());
        newEntity.setCategory(product.getCategory());

        var result = fileService.getFileByName(product.getImage());
        newEntity.setImage(result.getId());

        newEntity.setPrice(product.getPrice());
        return productRepository.save(newEntity);
    }

    public Optional<Product> editCatalog(UUID uuid, Product product) {
        Optional<Product> result = productRepository.findById(uuid);
        return result
                .map(entity -> {
                    entity.setAmount(product.getAmount());
                    if (product.getImage() != null) {
                        var file = fileService.getFileByName(product.getImage());
                        entity.setImage(file.getId());
                    }
                    entity.setPrice(product.getPrice());
                    entity.setName(product.getName());
                    entity.setDescription(product.getDescription());
                    entity.setComments(product.getComments());
                    entity.setAnimal(product.getAnimal());
                    entity.setCategory(product.getCategory());
                    return productRepository.save(entity);
                });

    }

    public Optional<Boolean> deleteById(UUID id) {
        Optional<Product> result = productRepository.findById(id);
        return result
                .map(catalog -> {
                    productRepository.deleteById(id);
                    return true;
                });
    }

    public Optional<Product> modifyKolInCatalog(UUID productID, int productKolInCart) {
        Optional<Product> result = productRepository.findById(productID);
        int nowInCatalog = result.get().getAmount();
        return result
                .map(modifyingEntity -> {
                    modifyingEntity.setAmount(nowInCatalog - productKolInCart);
                    return productRepository.save(modifyingEntity);
                });
    }

//    public void setPromotion(String promotionType, int promotionDiscount) {
//        var result = productRepository.findAllByCategory(promotionType);
//        for (int i = 0; i < result.size(); i++) {
//            result.get(i).setPromotion(promotionType);
//            double temp = result.get(i).getPrice() * promotionDiscount / 100;
//            result.get(i).setPromotionPrice(result.get(i).getPrice() - temp);
//        }
//
//    }

}
