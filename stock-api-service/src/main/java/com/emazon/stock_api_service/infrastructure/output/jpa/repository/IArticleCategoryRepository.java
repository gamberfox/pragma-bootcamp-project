package com.emazon.stock_api_service.infrastructure.output.jpa.repository;

import com.emazon.stock_api_service.infrastructure.output.jpa.entity.ArticleCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IArticleCategoryRepository extends JpaRepository {

    List<ArticleCategoryEntity> findByArticleId(Long categoryId);
}
