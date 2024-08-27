package com.emazon.stock_api_service.infrastructure.output.jpa.adapter;

import com.emazon.stock_api_service.domain.model.Article;
import com.emazon.stock_api_service.domain.model.ArticleCategory;
import com.emazon.stock_api_service.domain.model.Category;
import com.emazon.stock_api_service.domain.spi.IArticlePersistencePort;
import com.emazon.stock_api_service.infrastructure.output.jpa.entity.ArticleCategoryEntity;
import com.emazon.stock_api_service.infrastructure.output.jpa.entity.ArticleEntity;
import com.emazon.stock_api_service.infrastructure.output.jpa.mapper.IArticleCategoryEntityMapper;
import com.emazon.stock_api_service.infrastructure.output.jpa.mapper.IArticleEntityMapper;
import com.emazon.stock_api_service.infrastructure.output.jpa.repository.IArticleCategoryRepository;
import com.emazon.stock_api_service.infrastructure.output.jpa.repository.IArticleRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ArticleJpaAdapter implements IArticlePersistencePort {
    private final IArticleRepository articleRepository;
    private final IArticleEntityMapper articleEntityMapper;
    private final IArticleCategoryRepository articleCategoryRepository;
    private final IArticleCategoryEntityMapper articleCategoryEntityMapper;
    @Override
    public void createArticle(Article article) {
        ArticleEntity articleEntity = articleEntityMapper.toArticleEntity(article);
        articleRepository.save(articleEntity);
        for(Long categoryId:article.getCategoryIds()){
            articleCategoryRepository
                    .save(articleCategoryEntityMapper
                            .toArticleCategoryEntity(new ArticleCategory(article.getId(), categoryId)));
        }
    }

    @Override
    public Article getArticleById(Long id) {
        ArticleEntity articleEntity= articleRepository.findById(id).get();
        List<Long> categoryIdentities = articleCategoryRepository.findCategoryIdsByArticleId(id);
        return articleEntityMapper.toArticle(articleEntity,categoryIdentities);
    }

    @Override
    public Boolean articleIdExists(Long id) {
        return articleRepository.findById(id).isPresent();
    }

    @Override
    public Boolean articleNameExists(String name) {
        return articleRepository.findByName(name).isPresent();
    }
}
