package com.emazon.stock_api_service.domain.spi;

import com.emazon.stock_api_service.domain.model.ArticleCategory;

import java.util.List;

public interface IArticleCategoryPersistencePort {
    void createArticleCategory(ArticleCategory articleCategory);
    ArticleCategory getByBothIds(Long articleId,Long categoryId);
    List<ArticleCategory> getByArticleId(Long id);
    List<ArticleCategory> getByArticleName(String name);
    List<ArticleCategory> getByCategoryId(Long id);
    List<ArticleCategory> getByCategoryName(String name);
    Boolean articleIdExists(Long id);
    Boolean articleNameExists(String name);
    Boolean categoryIdExists(Long id);
    Boolean categoryNameExists(String name);
    Boolean articleCategoryExists(Long articleId,Long categoryId);
}
