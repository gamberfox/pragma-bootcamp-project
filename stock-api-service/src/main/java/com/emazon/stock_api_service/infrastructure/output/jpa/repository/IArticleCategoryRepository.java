package com.emazon.stock_api_service.infrastructure.output.jpa.repository;

import com.emazon.stock_api_service.infrastructure.output.jpa.entity.ArticleCategoryEntity;
import com.emazon.stock_api_service.infrastructure.output.jpa.entity.ArticleCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IArticleCategoryRepository extends JpaRepository<ArticleCategoryEntity, Long> {
    //ac is an alias
    //@Query("SELECT ac.id.category FROM ArticleCategoryEntity ac WHERE ac.id.article = :articleId")
    @Query("SELECT ac.categoryId FROM ArticleCategoryEntity ac WHERE ac.articleId = :articleId")
    List<Long> findCategoryIdsByArticleId(@Param("articleId") Long articleId);

    @Query("INSERT INTO ")
    void saveCategoryArticle(@Param("articleId") Long articleId, @Param("categoryId") Long categoryId);

}
