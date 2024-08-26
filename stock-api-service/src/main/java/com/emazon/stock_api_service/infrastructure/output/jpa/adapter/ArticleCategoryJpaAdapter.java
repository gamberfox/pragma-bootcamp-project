package com.emazon.stock_api_service.infrastructure.output.jpa.adapter;

import com.emazon.stock_api_service.domain.model.ArticleCategory;
import com.emazon.stock_api_service.domain.spi.IArticleCategoryPersistencePort;
import com.emazon.stock_api_service.infrastructure.output.jpa.entity.ArticleCategoryEntity;
import com.emazon.stock_api_service.infrastructure.output.jpa.mapper.IArticleCategoryEntityMapper;
import com.emazon.stock_api_service.infrastructure.output.jpa.repository.IArticleCategoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ArticleCategoryJpaAdapter implements IArticleCategoryPersistencePort {
    private final IArticleCategoryRepository articleCategoryRepository;
    private final IArticleCategoryEntityMapper articleCategoryEntityMapper;

    @Override
    public void createArticleCategory(ArticleCategory articleCategory) {
        ArticleCategoryEntity articleCategoryEntity = articleCategoryEntityMapper.toArticleCategoryEntity(articleCategory);
        articleCategoryRepository.save(articleCategoryEntity);
    }

    @Override
    public List<ArticleCategory> getByArticleId(Long id) {
        return List.of();
    }

    @Override
    public List<ArticleCategory> getByCategoryId(Long id) {
        return List.of();
    }
}
