package com.emazon.stock_api_service.domain.spi;

import com.emazon.stock_api_service.domain.model.ArticleCategory;

import java.util.List;

public interface IArticleCategoryPersistencePort {
    void createArticleCategory(ArticleCategory articleCategory);
    List<ArticleCategory> getByArticleId(Long id);
    List<ArticleCategory> getByCategoryId(Long id);
}
