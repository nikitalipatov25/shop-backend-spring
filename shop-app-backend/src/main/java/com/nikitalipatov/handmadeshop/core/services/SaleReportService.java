package com.nikitalipatov.handmadeshop.core.services;

import com.nikitalipatov.handmadeshop.core.models.Cart;
import com.nikitalipatov.handmadeshop.core.models.SaleReport;
import com.nikitalipatov.handmadeshop.core.repos.SaleReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class SaleReportService {

    private final SaleReportRepository saleReportRepository;
    private final CartService cartService;

    @Autowired
    public SaleReportService(SaleReportRepository saleReportRepository, CartService cartService) {
        this.saleReportRepository = saleReportRepository;
        this.cartService = cartService;
    }
    /*
    проверить, есть ли репорт;
    создать, если нет, и записать данные;
    если есть, обновить данные;
    */
    public void checkReport(List<Cart> cartList){
        for (int i = 0; i < cartList.size(); i++) {
            //существует ли запись об этом продукте
            Optional<SaleReport> product = saleReportRepository.findByProductId(cartList.get(i).getProductId());
            if (product.isEmpty()) {
                // если ничего нет - создать запись
                generateReport(cartList.get(i));
            } else {
                // иначе обновить поля сушествующей записи
                modifyReport(product, cartList.get(i));
            }

        };
    }

    public SaleReport generateReport(Cart product){
        SaleReport newReport = new SaleReport();
        newReport.setProductId(product.getProductId());
        newReport.setPrice(product.getProductCost());
        newReport.setQuantitySold(product.getSelectedProductKol());
        newReport.setDateLastSale(calculateReportDate());
        return saleReportRepository.save(newReport);
    }

    public Optional<SaleReport> modifyReport(Optional<SaleReport> saleReport, Cart product){
        return saleReport
                .map(entity -> {
                    entity.setPrice(product.getProductCost() + entity.getPrice());
                    entity.setQuantitySold(product.getSelectedProductKol() + entity.getQuantitySold());
                    entity.setDateLastSale(calculateReportDate());
                    return saleReportRepository.save(entity);
                });
    }

    // копия метода из OrdersService
    public String calculateReportDate() {
        Date currentDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return simpleDateFormat.format(currentDate);
    }

}
