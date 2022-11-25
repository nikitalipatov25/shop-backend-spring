package com.nikitalipatov.handmadeshop.core.repositories;

import com.nikitalipatov.handmadeshop.core.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SaleRepository extends JpaRepository<Sale, UUID> {
    Optional<Sale> deleteAllByExpirationDateBefore(Date date);
    List<Sale> findAllByExpirationDateBefore(Date date);
    Optional<Sale> findByName(String name);
}
