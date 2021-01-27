package com.example.demo.core.repos;

import com.example.demo.core.models.CatalogEntity;
import org.hibernate.boot.model.source.spi.Sortable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CatalogRepository extends JpaRepository<CatalogEntity, UUID> {
}
