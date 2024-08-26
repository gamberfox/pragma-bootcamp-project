package com.emazon.stock_api_service.infrastructure.output.jpa.mapper;

import com.emazon.stock_api_service.domain.model.ArticleCategory;
import com.emazon.stock_api_service.infrastructure.output.jpa.entity.ArticleCategoryEntity;

public interface IArticleCategoryEntityMapper {
    ArticleCategoryEntity toArticleCategoryEntity(ArticleCategory articleCategory);
    ArticleCategory toArticleCategory(ArticleCategoryEntity articleCategoryEntity);
}
