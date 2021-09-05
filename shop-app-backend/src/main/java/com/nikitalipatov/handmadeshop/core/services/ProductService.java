package com.nikitalipatov.handmadeshop.core.services;

import com.nikitalipatov.handmadeshop.core.models.Sale;
import com.nikitalipatov.handmadeshop.core.repositories.ProductRepository;
import com.nikitalipatov.handmadeshop.core.models.Product;
import com.nikitalipatov.handmadeshop.dto.ModifyProductDTO;
import com.nikitalipatov.handmadeshop.dto.ProductFilterDTO;
import com.nikitalipatov.handmadeshop.dto.SaleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.nikitalipatov.handmadeshop.specifications.ProductSpecifications.*;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final FileService fileService;
    private final AnimalService animalService;
    private final CategoryService categoryService;

    @Autowired
    public ProductService(ProductRepository productRepository, FileService fileService, AnimalService animalService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.fileService = fileService;
        this.animalService = animalService;
        this.categoryService = categoryService;
    }

    public Page<Product> findAllWithFilters(ProductFilterDTO productFilterDTO) {
        Specification<Product> specification = where(
                equalAnimalName(productFilterDTO.getAnimal())
                        .and(inCategories(productFilterDTO.getCategories()))
                        .and(inPriceRange(productFilterDTO.getPriceFrom(), productFilterDTO.getPriceTo()))
                        .and(likeSearchText(productFilterDTO.getSearchText())));
        Pageable pageable = PageRequest.of(
                productFilterDTO.getPageNumber(),
                productFilterDTO.getPageSize(),
                Sort.by(Sort.Direction.valueOf(productFilterDTO.getSortDirection()), productFilterDTO.getSortBy()));
        Page<Product> result = productRepository.findAll(specification, pageable);
        return result;
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
        var image = fileService.getFileByName(product.getImage());
        newEntity.setImage(image.getId());
        newEntity.setPrice(product.getPrice());
        newEntity.setAnimal(product.getAnimal());
        newEntity.setCategory(product.getCategory());
        newEntity.setRating(0.0);
        return productRepository.save(newEntity);
    }

    public Optional<Product> editCatalog(UUID uuid, ModifyProductDTO modifyProductDTO) {
        Optional<Product> result = productRepository.findById(uuid);
        return result
                .map(entity -> {
                    entity.setAmount(modifyProductDTO.getAmount());
                    if (modifyProductDTO.getImage() != null) {
                        var file = fileService.getFileByName(modifyProductDTO.getImage());
                        entity.setImage(file.getId());
                    }
                    entity.setPrice(modifyProductDTO.getPrice());
                    entity.setName(modifyProductDTO.getName());
                    entity.setDescription(modifyProductDTO.getDescription());
                    entity.setAnimal(modifyProductDTO.getAnimal());
                    entity.setCategory(modifyProductDTO.getCategory());
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

    public void setSaleForAnimals(List<String> animals, Sale newSale) {
        var result = productRepository.findAllByAnimalIn(animals);
        for (int i = 0; i < result.size(); i++) {
            result.get(i).setSale(newSale);
        }
    }

    public void setSaleForCategories(List<String> categories, Sale newSale) {
        var result = productRepository.findAllByCategoryIn(categories);
        for (int i = 0; i < result.size(); i++) {
            result.get(i).setSale(newSale);
        }
    }

    public void setSaleForProducts(List<UUID> products, Sale newSale) {
        var result = productRepository.findAllByIdIn(products);
        for (int i = 0; i < result.size(); i++) {
            result.get(i).setSale(newSale);
        }
    }

    public void disableSales(List<Sale> sales) {
        for (int i = 0; i < sales.size(); i++) {
            List<Product> products = productRepository.findAllBySale(sales.get(i));
            for (int k = 0; k < products.size(); k++) {
                products.get(k).setSale(null);
            }
        }
    }

    public void disableSaleManually(Sale sale) {
        List<Product> products = productRepository.findAllBySale(sale);
        for (int i = 0; i < products.size(); i++) {
            products.get(i).setSale(null);
        }
    }
}
