package com.nikitalipatov.handmadeshop.core.repos;

import com.nikitalipatov.handmadeshop.core.models.SaleReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SaleReportRepository extends JpaRepository<SaleReport, UUID> {
    Optional<SaleReport> findByProductId(UUID uuid);
}
