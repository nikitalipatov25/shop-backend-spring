package com.nikitalipatov.handmadeshop.core.services;

import com.nikitalipatov.handmadeshop.core.models.Sale;
import com.nikitalipatov.handmadeshop.core.repositories.SaleRepository;
import com.nikitalipatov.handmadeshop.dto.SaleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.UUID;


@Service
@Transactional
public class SaleService {

    private final SaleRepository saleRepository;
    private final ProductService productService;
    private final FileService fileService;


    @Autowired
    public SaleService(SaleRepository saleRepository, ProductService productService, FileService fileService) {
        this.saleRepository = saleRepository;
        this.productService = productService;
        this.fileService = fileService;
    }

    public Sale createSale(SaleDTO saleDTO) {
        Sale newSale = new Sale();
        newSale.setId(UUID.randomUUID());
        newSale.setName(saleDTO.getName());
        newSale.setDescription(saleDTO.getDescription());
        newSale.setImage(newSale.getImage());
        newSale.setDiscount(saleDTO.getDiscount());
        return  saleRepository.save(newSale);
    }

    public Page<Sale> getPromotions(Pageable pageable) {
        Page<Sale> result = saleRepository.findAll(pageable);
        return result;
    }

    public void addSaleToProduct(Sale newSale, SaleDTO saleDTO) {
        switch (saleDTO.getSaleType()) {
            case "Скидка на тип животных": productService.setSaleForAnimals(saleDTO.getAnimals(), newSale);
                break;
            case "Скидка на категории": productService.setSaleForCategories(saleDTO.getCategories(), newSale);
                break;
            case "Скидка на продукты": productService.setSaleForProducts(saleDTO.getProducts(), newSale);
                break;
        }
    }
}
