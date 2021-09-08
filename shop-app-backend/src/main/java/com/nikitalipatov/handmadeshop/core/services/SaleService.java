package com.nikitalipatov.handmadeshop.core.services;

import com.nikitalipatov.handmadeshop.core.models.Sale;
import com.nikitalipatov.handmadeshop.core.repositories.SaleRepository;
import com.nikitalipatov.handmadeshop.dto.SaleDTO;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Transactional
@EnableScheduling
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

    @Scheduled(cron = "0 1 0 * * ?")
    public void deleteByScheduler() {
        List<Sale> sales = saleRepository.findAllByExpirationDateBefore(new Date());
        productService.disableSales(sales);
        saleRepository.deleteAllByExpirationDateBefore(new Date());
    }

    @SneakyThrows
    public Sale createSale(SaleDTO saleDTO) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Sale newSale = new Sale();
        newSale.setId(UUID.randomUUID());
        newSale.setName(saleDTO.getName());
        newSale.setDescription(saleDTO.getDescription());
        var image = fileService.getFileByName(saleDTO.getImage());
        newSale.setImage(image.getId());
        newSale.setDate(formatter.parse(saleDTO.getDate()));
        newSale.setExpirationDate(formatter.parse(saleDTO.getExpirationDate()));
        newSale.setDiscount(saleDTO.getDiscount());
        return  saleRepository.save(newSale);
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

    public Page<Sale> getSales() {
        Pageable pageable = PageRequest.of(0, 4, Sort.by(Sort.Direction.ASC, "expirationDate"));
        return saleRepository.findAll(pageable);
    }

    public Optional<Boolean> deleteSaleManually(UUID saleId) {
        Optional<Sale> result = saleRepository.findById(saleId);
        return result
                .map(e -> {
                    productService.disableSaleManually(result.get());
                    saleRepository.deleteById(saleId);
                    return true;
                });
    }

    public Optional<Sale> modifySale(SaleDTO saleDTO) {
        Optional<Sale> result = saleRepository.findById(saleDTO.getId());
        return result
                .map(e -> {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    e.setName(saleDTO.getName());
                    e.setDescription(saleDTO.getDescription());
                    e.setImage(saleDTO.getImage());
                    try {
                        e.setDate(formatter.parse(saleDTO.getDate()));
                        e.setExpirationDate(formatter.parse(saleDTO.getExpirationDate()));
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }
                    e.setDiscount(saleDTO.getDiscount());
                    return saleRepository.save(e);
                });
    }

    public Optional<Sale> getSale(UUID id) {
        return saleRepository.findById(id);
    }
}
