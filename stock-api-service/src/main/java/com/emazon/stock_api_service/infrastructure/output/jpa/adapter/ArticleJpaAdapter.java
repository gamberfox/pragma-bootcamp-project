package com.emazon.stock_api_service.infrastructure.output.jpa.adapter;

import com.emazon.stock_api_service.domain.model.Article;
import com.emazon.stock_api_service.domain.spi.IArticlePersistencePort;
import com.emazon.stock_api_service.infrastructure.output.jpa.entity.ArticleEntity;
import com.emazon.stock_api_service.infrastructure.output.jpa.mapper.IArticleEntityMapper;
import com.emazon.stock_api_service.infrastructure.output.jpa.mapper.ICategoryEntityMapper;
import com.emazon.stock_api_service.infrastructure.output.jpa.repository.IArticleRepository;
import com.emazon.stock_api_service.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArticleJpaAdapter implements IArticlePersistencePort {
    private final IArticleRepository articleRepository;
    private final IArticleEntityMapper articleEntityMapper;
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    @Override
    public void createArticle(Article article) {
        ArticleEntity articleEntity = articleEntityMapper.toArticleEntity(article);
        articleRepository.save(articleEntity);
    }

    @Override
    public Article getArticleById(Long id) {
        ArticleEntity articleEntity= articleRepository.findById(id).get();
        return articleEntityMapper.toArticle(articleEntity);
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
