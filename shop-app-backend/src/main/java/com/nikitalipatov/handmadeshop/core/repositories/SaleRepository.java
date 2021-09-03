package com.nikitalipatov.handmadeshop.core.repositories;

import com.nikitalipatov.handmadeshop.core.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
