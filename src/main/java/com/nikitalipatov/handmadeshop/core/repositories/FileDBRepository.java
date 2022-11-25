package com.nikitalipatov.handmadeshop.core.repositories;

import com.nikitalipatov.handmadeshop.core.models.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {
    FileDB findByName(String name);

}
