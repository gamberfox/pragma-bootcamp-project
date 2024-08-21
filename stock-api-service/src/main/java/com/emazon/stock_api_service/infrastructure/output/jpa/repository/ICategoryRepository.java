package com.emazon.stock_api_service.infrastructure.output.jpa.repository;

import com.emazon.stock_api_service.infrastructure.output.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

//we need to clarify the class and primary key type.
//JpaRepository= java persistency API.
public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(String name);
    Optional<CategoryEntity> findById(Long id);
    void deleteById(Long id);
    List<CategoryEntity> findAll();
}
