package com.nikitalipatov.handmadeshop.core.repos;

import com.nikitalipatov.handmadeshop.core.models.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface FileRepository extends JpaRepository<File, String> {

    Optional<File> getFileByName(String name);

}
